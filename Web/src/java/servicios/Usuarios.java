/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

/**
 *
 * @author Steven
 */
public class Usuarios {
    private Integer idUsuario;
     private Tipousuarios tipousuarios;
     private String username;
     private String pwd;
     private String nombreCliente;
     private String apellidoCliente;
     private String documento;

    public Usuarios() {
    }

    public Usuarios(Integer idUsuario, Tipousuarios tipousuarios, String username, String pwd, String nombreCliente, String apellidoCliente, String documento) {
        this.idUsuario = idUsuario;
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
     
     
}
