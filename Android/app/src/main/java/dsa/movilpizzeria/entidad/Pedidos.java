package dsa.movilpizzeria.entidad;

import java.util.Date;

public class Pedidos {
    private Integer idPedido;
    private Usuarios usuarios;
    private String fecha;

    public Pedidos() {
    }

    public Pedidos(Usuarios usuarios, String fecha) {
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
