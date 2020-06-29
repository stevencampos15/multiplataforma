package dsa.movilpizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import dsa.movilpizzeria.entidad.Items;

public class ProductoDetalle extends AppCompatActivity {

    private TextView lblNombreProducto;
    private TextView lblDescripcion;
    private TextView lblTamano;
    private TextView lblPrecio;
    private Button btnAgregarProducto;
    private Button btnVolverProducto;
    private Menu menu = new Menu();
    private Items prod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_detalle);


        this.lblNombreProducto = super.findViewById(R.id.lblNombreProducto);
        this.lblDescripcion = super.findViewById(R.id.lblDescripcion);
        this.lblTamano = super.findViewById(R.id.lblTamano);
        this.lblPrecio = super.findViewById(R.id.lblPrecio);
        this.btnAgregarProducto = super.findViewById(R.id.btnAgregarProducto);
        this.btnVolverProducto = super.findViewById(R.id.btnVolverProducto);

        this.btnVolverProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver();
            }
        });

        //Traemos el producto que ha seleccionado el usuario
        Intent it = getIntent();
        if(it.hasExtra("item")){
            //Mostramos
            Items producto = (Items) getIntent().getSerializableExtra("item");
            this.lblNombreProducto.setText(producto.getNombreItem());
            this.lblDescripcion.setText(producto.getDescripcion());
            this.lblTamano.setText(producto.getTamano());
            this.lblPrecio.setText("$"+String.valueOf(producto.getPrecio()));
            prod = producto;
        }
        this.btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregar();
            }
        });



    }

    private void volver(){
        super.finish();
    }

    private void agregar(){
        menu.listaProductos.add(prod);
        Toast.makeText(getApplicationContext(), "Producto agregado", Toast.LENGTH_SHORT).show();
        super.finish();
    }


}
