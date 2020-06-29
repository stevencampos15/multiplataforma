package dsa.movilpizzeria.entidad;

import java.io.Serializable;

import dsa.movilpizzeria.entidad.Tipousuarios;

public class Usuarios implements Serializable {
    private Integer idUsuario;
    private Tipousuarios tipousuarios;
    private String username;
    private String pwd;
    private String nombreCliente;
    private String apellidoCliente;
    private String documento;

    public Usuarios() {
        this.username="";
    }

    public Usuarios(Tipousuarios tipousuarios, String username, String pwd, String nombreCliente, String apellidoCliente, String documento) {
        this.tipousuarios = tipousuarios;
        this.username = username;
        this.pwd = pwd;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.documento = documento;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Tipousuarios getTipousuarios() {
        return tipousuarios;
    }

    public void setTipousuarios(Tipousuarios tipousuarios) {
        this.tipousuarios = tipousuarios;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public boolean esValido(){
        return (!this.username.equals(""));
    }

    @Override
    public String toString() {
        return nombreCliente + " " +apellidoCliente;
    }
}
