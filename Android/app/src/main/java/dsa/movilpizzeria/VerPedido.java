package dsa.movilpizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dsa.movilpizzeria.entidad.Detallespedido;
import dsa.movilpizzeria.entidad.Items;
import dsa.movilpizzeria.entidad.Pedidos;
import dsa.movilpizzeria.entidad.Tipousuarios;
import dsa.movilpizzeria.entidad.Usuarios;

public class VerPedido extends AppCompatActivity implements iClaseLlamada{

    private Usuarios usuario;
    private TextView lblPedido;
    private TextView lblUsuario;
    private TextView lblFechaPedido;
    private TextView lblTotalProductos;
    private TextView lblTot;
    private ListView lvListaPedidos;
    private TextView lblProds;
    private Button btnHacerPedido;
    private Button btnCancelarPedido;
    private ArrayAdapter adaptador;
    private Menu menu = new Menu();
    private Date currentTime = Calendar.getInstance().getTime();
    private Usuarios us = new Usuarios();
    private Pedidos pedidoDet = new Pedidos();
    private boolean detalleHecho = false;
    private boolean idEncontrado = false;
    private boolean mensajeMostrado = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedido);

        this.lblPedido = super.findViewById(R.id.lblPedido);
        this.lblUsuario = super.findViewById(R.id.lblUsuario);
        this.lblFechaPedido = super.findViewById(R.id.lblFechaPedido);
        this.lblTotalProductos = super.findViewById(R.id.lblTotalProductos);
        this.lblTot = super.findViewById(R.id.lblTot);
        this.lblProds = super.findViewById(R.id.lblProds);
        this.lvListaPedidos = super.findViewById(R.id.lvListaPedidos);
        this.btnHacerPedido = super.findViewById(R.id.btnHacerPedido);
        this.btnCancelarPedido = super.findViewById(R.id.btnCancelarPedido);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.lblFechaPedido.setText(formatter.format(currentTime));

        Intent it = getIntent();
        if(it.hasExtra("usuario")) {
            //Mostramos
            usuario = (Usuarios) getIntent().getSerializableExtra("usuario");
            this.lblUsuario.setText(usuario.getNombreCliente());
            us = usuario;
        }
        if(menu.listaProductos.isEmpty()){
            esconder();
        } else {
            llenarLista();
        }

        this.btnCancelarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelarPedido();
            }
        });

        this.btnHacerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hacerPedido();
            }
        });

    }

    private void llenarLista(){
        adaptador = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1 ,menu.listaProductos);
        lvListaPedidos.setAdapter(adaptador);
        float total = 0;
        for(int i=0; i<menu.listaProductos.size(); i++){
            total += menu.listaProductos.get(i).getPrecio();
        }
        this.lblTotalProductos.setText("$"+String.valueOf(total));
    }

    private void esconder(){
        this.lblPedido.setText("No ha agregado productos");
        this.lblUsuario.setVisibility(View.GONE);
        this.lblProds.setVisibility(View.GONE);
        this.btnHacerPedido.setVisibility(View.GONE);
        this.btnCancelarPedido.setVisibility(View.GONE);
        this.lblFechaPedido.setVisibility(View.GONE);
        this.lblTotalProductos.setVisibility(View.GONE);
        this.lblTot.setVisibility(View.GONE);
    }

    private void cancelarPedido(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cancelar Pedido");
        builder.setMessage("¿Está seguro que desea cancelar y borrar el pedido?");
        builder.setCancelable(false);
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Se ha cancelado el pedido", Toast.LENGTH_SHORT).show();
                menu.listaProductos.clear();
                esconder();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }

    private void hacerPedido(){
        //Pedido Principal
        Pedidos pedido = new Pedidos();
        pedido.setUsuarios(us);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String fecha = formatter.format(currentTime);
        pedido.setFecha(fecha);
        pedidoDet = pedido;
        String urlPedido = "http://192.168.1.14:8080/WsPizzeriaDSA/webresources/WsPedidos";
        ServicioVolley volleyPedido = new ServicioVolley(this,this.getApplicationContext());
        volleyPedido.guardarPedido(urlPedido, pedido);
    }

    @Override
    public void resultadoOk(String tipo, JSONObject obj) {
        try {
            String resultado = obj.get("status").toString();
            if (resultado.equals("fail")) {
                Toast.makeText(getApplicationContext(), "Hubo un error al crear el pedido", Toast.LENGTH_SHORT).show();
            } else {
                if (obj.get("status").equals("okPedido")) {
                    detalleHecho = true;
                    guardarDetalle();
                }
                if (obj.get("status").equals("okPost")) {
                    JSONArray objListPedido = (JSONArray) obj.get("listaPedidos");
                    if (objListPedido != null) {
                        JSONObject objPedido =(JSONObject) objListPedido.get(objListPedido.length()-1);
                        pedidoDet.setIdPedido(objPedido.getInt("idPedido"));
                        idEncontrado = true;
                        guardarDetalle();
                    }
                }else if (obj.get("status").equals("okDetalle")) {
                    if(!mensajeMostrado){
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Pedido Realizado!");
                        builder.setMessage("Se ha relizado su pedido, puedes pasar por él en una hora");
                        builder.setCancelable(true);
                        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Gracias", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();
                        Toast.makeText(getApplicationContext(), "Felicidades por su pedido. ", Toast.LENGTH_SHORT).show();
                        menu.listaProductos.clear();
                        esconder();
                        mensajeMostrado=true;
                    }
                }
            }
        } catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void resultadoError(String tipo, VolleyError e) {

    }

    private boolean guardarDetalle(){
        boolean completo = false;
        if(detalleHecho){
            //DetallePedido
            buscarIdPedido();
            if(idEncontrado){
                for(int i=0; i<menu.listaProductos.size(); i++){
                    Items item = new Items();
                    item = menu.listaProductos.get(i);
                    Detallespedido dp = new Detallespedido();
                    dp.setItems(item);
                    dp.setCantidad(1);
                    dp.setPedidos(pedidoDet);
                    String urlDetalle = "http://192.168.1.14:8080/WsPizzeriaDSA/webresources/WsDetallesPedido";
                    ServicioVolley volleyDetalle = new ServicioVolley(this,this.getApplicationContext());

                    volleyDetalle.guardarDetallesPedido(urlDetalle, dp);
                }
                detalleHecho = false;
                completo = true;
            }

        }
        return completo;
    }

    private void buscarIdPedido(){
        String url = "http://192.168.1.14:8080/WsPizzeriaDSA/webresources/WsPedidos/listaPedidos";
        ServicioVolley volley = new ServicioVolley(this,this.getApplicationContext());
        volley.llamadaPost("",url);

    }
}
