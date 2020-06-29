package dsa.movilpizzeria.entidad;

public class Detallespedido {
    private Integer idDetalle;
    private Items items;
    private Pedidos pedidos;
    private int cantidad;

    public Detallespedido() {
    }

    public Detallespedido(Items items, Pedidos pedidos, int cantidad) {
        this.items = items;
        this.pedidos = pedidos;
        this.cantidad = cantidad;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
