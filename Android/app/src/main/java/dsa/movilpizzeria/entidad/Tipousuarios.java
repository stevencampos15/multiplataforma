package dsa.movilpizzeria.entidad;

import java.io.Serializable;

public class Tipousuarios implements Serializable {
    private Integer idTipoUsuario;
    private String nombreTipoUsuario;

    public Tipousuarios() {
    }

    public Tipousuarios(String nombreTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

    public Integer getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getNombreTipoUsuario() {
        return nombreTipoUsuario;
    }

    public void setNombreTipoUsuario(String nombreTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

    @Override
    public String toString() {
        return "Tipousuarios{" +
                "nombreTipoUsuario='" + nombreTipoUsuario + '\'' +
                '}';
    }
}
