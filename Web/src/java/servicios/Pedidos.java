/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.Date;

/**
 *
 * @author Steven
 */
public class Pedidos {
    private Integer idPedido;
     private Usuarios usuarios;
     private String fecha;

    public Pedidos() {
    }

    public Pedidos(Integer idPedido, Usuarios usuarios, String fecha) {
        this.idPedido = idPedido;
        this.usuarios = usuarios;
        this.fecha = fecha;
    }
     
     

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
     
     
}
