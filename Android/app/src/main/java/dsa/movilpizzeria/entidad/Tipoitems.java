package dsa.movilpizzeria.entidad;

public class Tipoitems {

    private Integer idTipoItem;
    private String nombreTipoItem;

    public Tipoitems() {
    }

    public Tipoitems(Integer idTipoItem, String nombreTipoItem) {
        this.idTipoItem = idTipoItem;
        this.nombreTipoItem = nombreTipoItem;
    }

    public Integer getIdTipoItem() {
        return idTipoItem;
    }

    public void setIdTipoItem(Integer idTipoItem) {
        this.idTipoItem = idTipoItem;
    }

    public String getNombreTipoItem() {
        return nombreTipoItem;
    }

    public void setNombreTipoItem(String nombreTipoItem) {
        this.nombreTipoItem = nombreTipoItem;
    }
}
