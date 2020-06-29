package modelo.entidad;
// Generated May 15, 2020 3:37:35 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tipousuarios generated by hbm2java
 */
@Entity
@Table(name="tipousuarios"
    ,catalog="pizzeriaDSA"
)
public class Tipousuarios  implements java.io.Serializable {


     private Integer idTipoUsuario;
     private String nombreTipoUsuario;

    public Tipousuarios() {
    }

	
    public Tipousuarios(String nombreTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idTipoUsuario", unique=true, nullable=false)
    public Integer getIdTipoUsuario() {
        return this.idTipoUsuario;
    }
    
    public void setIdTipoUsuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    
    @Column(name="nombreTipoUsuario", nullable=false, length=50)
    public String getNombreTipoUsuario() {
        return this.nombreTipoUsuario;
    }
    
    public void setNombreTipoUsuario(String nombreTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
    }


}


