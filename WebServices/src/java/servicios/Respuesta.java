package servicios;

import java.util.ArrayList;
import java.util.List;
import modelo.entidad.Detallespedido;
import modelo.entidad.Items;
import modelo.entidad.Pedidos;
import modelo.entidad.Tipoitems;
import modelo.entidad.Usuarios;

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
        this.status = "fail";
        this.usuario = new Usuarios();
        this.listaItems = new ArrayList<>();
        this.listaTipoItems = new ArrayList<>();
        this.tipoItem = new Tipoitems();
        this.listaUsuarios = new ArrayList<>();
        this.listaPedidos = new ArrayList<>();
        this.listaDetallePedidos = new ArrayList<>();
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios username) {
        if (!username.getUsername().equals("")) {
            this.status = "ok";
            this.usuario = username;
        }
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
        if (tipoItem != null) {
            this.status = "ok";
            this.tipoItem = tipoItem;
        }

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
