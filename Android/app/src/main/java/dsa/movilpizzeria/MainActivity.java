package dsa.movilpizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import dsa.movilpizzeria.entidad.Tipousuarios;
import dsa.movilpizzeria.entidad.Usuarios;

public class MainActivity extends AppCompatActivity implements iClaseLlamada{

    private EditText txtUsername;
    private EditText txtPwd;
    private Button btnIniciarSesion;
    private Button btnRegistrarse;
    private int cont= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.txtUsername = super.findViewById(R.id.txtUsername);
        this.txtPwd = super.findViewById(R.id.txtPwd);
        this.btnIniciarSesion = super.findViewById(R.id.btnIniciarSesion);
        this.btnRegistrarse = super.findViewById(R.id.btnRegistrarse);

        this.btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });
        this.btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuRegistro();
            }
        });
    }

    private void iniciarSesion(){
        String username, pwd;
        username = this.txtUsername.getText().toString();
        pwd = this.txtPwd.getText().toString();
        String url = "http://192.168.1.14:8080/WsPizzeriaDSA/webresources/wsLogin/login?usuario="+ username +"&pwd="+ pwd;
        ServicioVolley volley = new ServicioVolley(this,this.getApplicationContext());
        volley.llamadaPost("",url);

    }

    @Override
    public void resultadoOk(String tipo, JSONObject obj) {
        try{
            String resultado = obj.get("status").toString();
            if(resultado.equals("fail")){
                Toast.makeText(getApplicationContext(), "Usuario incorrecto", Toast.LENGTH_SHORT).show();
                if(cont>1){
                    Toast.makeText(getApplicationContext(), "Registrate", Toast.LENGTH_SHORT).show();
                    this.btnRegistrarse.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonrounded));
                    this.btnIniciarSesion.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonrounded_border));
                }
                cont +=1;

            } else {
                Usuarios usuario = new Usuarios();
                if(obj.get("status").equals("okPost")){

                    JSONObject objUser = (JSONObject) obj.get("usuario");
                    if(objUser.length()!=0){
                        usuario.setIdUsuario(Integer.parseInt(objUser.getString("idUsuario")));
                        usuario.setUsername(objUser.getString("username"));
                        usuario.setPwd(objUser.getString("pwd"));
                        usuario.setNombreCliente(objUser.getString("nombreCliente"));
                        usuario.setApellidoCliente(objUser.getString("apellidoCliente"));
                        usuario.setDocumento(objUser.getString("documento"));
                        Tipousuarios tipoUsuario = new Tipousuarios();
                        JSONObject objTipoUser =(JSONObject) objUser.get("tipousuarios");
                        tipoUsuario.setIdTipoUsuario(objTipoUser.getInt("idTipoUsuario"));
                        tipoUsuario.setNombreTipoUsuario(objTipoUser.getString("nombreTipoUsuario"));
                        usuario.setTipousuarios(tipoUsuario);
                    } else {
                        Toast.makeText(getApplicationContext(), "Usuario incorrecto", Toast.LENGTH_SHORT).show();
                        if(cont>1){
                            Toast.makeText(getApplicationContext(), "Registrate", Toast.LENGTH_SHORT).show();
                            this.btnRegistrarse.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonrounded));
                            this.btnIniciarSesion.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonrounded_border));
                        }
                        cont +=1;
                    }
                }
                if(usuario.esValido()){
                    Toast.makeText(getApplicationContext(), "Bienvenido: "+usuario.toString(), Toast.LENGTH_SHORT).show();
                    menuExito(usuario);
                }
            }
        } catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void resultadoError(String tipo, VolleyError e) {
        System.out.println("Error: "+e.getMessage());
    }

    public void menuExito(Usuarios us){
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("usuario", us);
        startActivityForResult(intent,0);

    }

    public void menuRegistro(){
        txtUsername.setText("");
        txtPwd.setText("");
        Intent intent = new Intent(this, Registro.class);
        startActivityForResult(intent,0);
    }

}
