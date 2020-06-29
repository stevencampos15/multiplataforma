package dsa.movilpizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import dsa.movilpizzeria.entidad.Items;
import dsa.movilpizzeria.entidad.Usuarios;

public class Menu extends AppCompatActivity {

    private Button btnHacerPedido;
    private Button btnVerPedidos;
    public static final ArrayList<Items> listaProductos = new ArrayList<Items>();
    private Usuarios usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.btnHacerPedido = super.findViewById(R.id.btnHacerPedido);
        this.btnVerPedidos = super.findViewById(R.id.btnVerPedidos);

        this.btnHacerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuHacerPedido();
            }
        });

        this.btnVerPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuVerPedido();
            }
        });

    }
    private void menuHacerPedido(){
        Intent intent = new Intent(this, HacerPedido.class);
        startActivityForResult(intent,0);
    }
    private void menuVerPedido(){
        //Traemos el usuario que ha iniciado sesion
        Intent it = getIntent();
        Intent intent = new Intent(this, VerPedido.class);
        if(it.hasExtra("usuario")) {
            //Mostramos
            usuario = (Usuarios) getIntent().getSerializableExtra("usuario");
            intent.putExtra("usuario", usuario);
        }
        startActivityForResult(intent,0);
    }
}
