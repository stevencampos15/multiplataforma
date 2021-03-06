package modelo.entidad;
// Generated May 15, 2020 3:37:35 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Detallespedido generated by hbm2java
 */
@Entity
@Table(name="detallespedido"
    ,catalog="pizzeriaDSA"
)
public class Detallespedido  implements java.io.Serializable {


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
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idDetalle", unique=true, nullable=false)
    public Integer getIdDetalle() {
        return this.idDetalle;
    }
    
    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idItem", nullable=false)
    public Items getItems() {
        return this.items;
    }
    
    public void setItems(Items items) {
        this.items = items;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idPedido", nullable=false)
    public Pedidos getPedidos() {
        return this.pedidos;
    }
    
    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    
    @Column(name="cantidad", nullable=false)
    public int getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }




}


