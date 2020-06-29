/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.List;

/**
 *
 * @author Steven
 */
public class Respuesta {

    private Usuarios usuario;
    private String status;
    private List<Items> listaItems;
    private List<Tipoitems> listaTipoItems;
    private Tipoitems tipoItem;
    private List<Usuarios> listaUsuarios;
    private List<Pedidos> listaPedidos;
    private List<Detallespedido> listaDetallePedidos;

    
    public Respuesta() {
    }

    public Respuesta(Usuarios usuario, String status, List<Items> listaItems, List<Tipoitems> listaTipoItems, Tipoitems tipoItem, List<Usuarios> listaUsuarios, List<Pedidos> listaPedidos, List<Detallespedido> listaDetallePedidos) {
        this.usuario = usuario;
        this.status = status;
        this.listaItems = listaItems;
        this.listaTipoItems = listaTipoItems;
        this.tipoItem = tipoItem;
        this.listaUsuarios = listaUsuarios;
        this.listaPedidos = listaPedidos;
        this.listaDetallePedidos = listaDetallePedidos;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Items> getListaItems() {
        return listaItems;
    }

    public void setListaItems(List<Items> listaItems) {
        this.listaItems = listaItems;
    }

    public List<Tipoitems> getListaTipoItems() {
        return listaTipoItems;
    }

    public void setListaTipoItems(List<Tipoitems> listaTipoItems) {
        this.listaTipoItems = listaTipoItems;
    }

    public Tipoitems getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(Tipoitems tipoItem) {
        this.tipoItem = tipoItem;
    }

    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Pedidos> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedidos> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public List<Detallespedido> getListaDetallePedidos() {
        return listaDetallePedidos;
    }

    public void setListaDetallePedidos(List<Detallespedido> listaDetallePedidos) {
        this.listaDetallePedidos = listaDetallePedidos;
    }

    
    
    
}
