package dsa.movilpizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dsa.movilpizzeria.entidad.Items;
import dsa.movilpizzeria.entidad.Tipoitems;

public class HacerPedido extends AppCompatActivity implements iClaseLlamada{

    private List<Items> lstProductos = new ArrayList<Items>();
    private ListView lvProductos;
    private ArrayAdapter adaptador;
    private Button btnVolverMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacer_pedido);
        this.btnVolverMenu = super.findViewById(R.id.btnVolverMenu);
        this.lvProductos = super.findViewById(R.id.lvProductos);
        llenarLista();

        this.btnVolverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverMenu();
            }
        });

        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                abrirProductoDetalle(lstProductos.get(position));
            }
        });
    }

    private void llenarLista(){
        String url = "http://192.168.1.14:8080/WsPizzeriaDSA/webresources/WsItems/listaItems";
        ServicioVolley volley = new ServicioVolley(this,this.getApplicationContext());
        volley.llamadaPost("",url);
    }

    @Override
    public void resultadoOk(String tipo, JSONObject obj) {
        try{
            String resultado = obj.get("status").toString();
            if(resultado.equals("fail")){
                Toast.makeText(getApplicationContext(), "Usuario incorrecto", Toast.LENGTH_SHORT).show();
            } else {
                if(obj.get("status").equals("okPost")){
                    JSONArray objArray = (JSONArray) obj.get("listaItems");
                    if (objArray != null) {
                        for (int i = 0; i < objArray.length(); i++) {
                            try {
                                Items item = new Items();
                                JSONObject objItem= (JSONObject) objArray.get(i);
                                item.setIdItem(objItem.getInt("idItem"));
                                item.setNombreItem(objItem.getString("nombreItem"));
                                item.setDescripcion(objItem.getString("descripcion"));
                                item.setTamano(objItem.getString("tamano"));
                                item.setPrecio(Float.valueOf(objItem.getString("precio")));
                                Tipoitems tipoItem = new Tipoitems();
                                JSONObject objTipoItem =(JSONObject) objItem.get("tipoitems");
                                tipoItem.setIdTipoItem(Integer.parseInt(objTipoItem.getString("idTipoItem")));
                                tipoItem.setNombreTipoItem(objTipoItem.getString("nombreTipoItem"));
                                lstProductos.add(item);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    adaptador = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1 ,lstProductos);
                    lvProductos.setAdapter(adaptador);
                }
            }
        } catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void resultadoError(String tipo, VolleyError e) {
        System.out.println("Error: "+e.getMessage());
    }

    private void abrirProductoDetalle(Items item){
        Intent intent = new Intent(this, ProductoDetalle.class);
        intent.putExtra("item", item);
        startActivityForResult(intent,0);
    }

    private void volverMenu(){
        super.finish();
    }
}
