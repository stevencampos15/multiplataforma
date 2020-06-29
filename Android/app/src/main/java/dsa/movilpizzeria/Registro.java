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

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dsa.movilpizzeria.entidad.Tipousuarios;
import dsa.movilpizzeria.entidad.Usuarios;

public class Registro extends AppCompatActivity implements iClaseLlamada{

    private EditText txtNombreCliente;
    private EditText txtApellidoCliente;
    private EditText txtDocumentoCliente;
    private EditText txtUsernameCliente;
    private EditText txtPwdCliente;
    private Button btnRegistrarCliente;
    private Button btnCancelarCliente;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        this.txtNombreCliente = super.findViewById(R.id.txtNombreCliente);
        this.txtApellidoCliente = super.findViewById(R.id.txtApellidoCliente);
        this.txtDocumentoCliente = super.findViewById(R.id.txtDocumentoCliente);
        this.txtUsernameCliente = super.findViewById(R.id.txtUsernameCliente);
        this.txtPwdCliente = super.findViewById(R.id.txtPwdCliente);
        this.btnRegistrarCliente = super.findViewById(R.id.btnRegistrarCliente);
        this.btnCancelarCliente = super.findViewById(R.id.btnCancelarCliente);

        this.btnRegistrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registroCliente();
            }
        });


        this.btnCancelarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });
    }

    public void registroCliente(){
        Tipousuarios tipoUsuario = new Tipousuarios();
        tipoUsuario.setIdTipoUsuario(2);
        tipoUsuario.setNombreTipoUsuario("Cliente");
        String usernameCliente = this.txtUsernameCliente.getText().toString();
        String pwdCliente = this.txtPwdCliente.getText().toString();
        String nombre = this.txtNombreCliente.getText().toString();
        String apellido = this.txtApellidoCliente.getText().toString();
        String documento = this.txtDocumentoCliente.getText().toString();

        Usuarios us = new Usuarios();
        us.setNombreCliente(nombre);
        us.setApellidoCliente(apellido);
        us.setDocumento(documento);
        us.setUsername(usernameCliente);
        String encryptedPwd = toMD5(pwdCliente);
        us.setPwd(encryptedPwd);
        us.setTipousuarios(tipoUsuario);
        String url = "http://192.168.1.14:8080/WsPizzeriaDSA/webresources/WsUsuarios";
        ServicioVolley volley = new ServicioVolley(this,this.getApplicationContext());
        volley.guardarUsuario(url, us);

    }

    @Override
    public void resultadoOk(String tipo, JSONObject obj) {
        try {
            String resultado = obj.get("status").toString();
            if (resultado.equals("fail")) {
                Toast.makeText(getApplicationContext(), "Hubo un error al crear el usuario", Toast.LENGTH_SHORT).show();
            } else {
                if (obj.get("status").equals("ok")) {
                    Toast.makeText(getApplicationContext(), "Felicidades por registrarse. ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivityForResult(intent,0);
                    Toast.makeText(getApplicationContext(), "¡Ya puedes iniciar Sesion! ", Toast.LENGTH_SHORT).show();
                }
            }
        } catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void resultadoError(String tipo, VolleyError e) {
        System.out.println("Error: " + e.getMessage());
    }

    private void cancelar(){
        super.finish();
    }

    public String toMD5(String clave) {
        MessageDigest md = null;
        byte[] raw = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(clave.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return String.format("%032x", new BigInteger(1, md.digest()));
    }
}
