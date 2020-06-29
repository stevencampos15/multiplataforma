
package servicios;

/**
 *
 * @author Steven
 */
public class Items {
    private Integer idItem;
     private Tipoitems tipoitems;
     private String nombreItem;
     private String tamano;
     private float precio;
     private String descripcion;

    public Items() {
    }

    public Items( Tipoitems tipoitems, String nombreItem, String tamano, float precio, String descripcion) {
        this.tipoitems = tipoitems;
        this.nombreItem = nombreItem;
        this.tamano = tamano;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Tipoitems getTipoitems() {
        return tipoitems;
    }

    public void setTipoitems(Tipoitems tipoitems) {
        this.tipoitems = tipoitems;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
     
     
}
