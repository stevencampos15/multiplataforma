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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Pedidos generated by hbm2java
 */
@Entity
@Table(name="pedidos"
    ,catalog="pizzeriaDSA"
)
public class Pedidos  implements java.io.Serializable {


     private Integer idPedido;
     private Usuarios usuarios;
     private String fecha;

    public Pedidos() {
    }

	
    public Pedidos(Usuarios usuarios, String fecha) {
        this.usuarios = usuarios;
        this.fecha = fecha;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idPedido", unique=true, nullable=false)
    public Integer getIdPedido() {
        return this.idPedido;
    }
    
    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idUsuario", nullable=false)
    public Usuarios getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    @Column(name="fecha", nullable=false, length=19)
    public String getFecha() {
        return this.fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }



}


