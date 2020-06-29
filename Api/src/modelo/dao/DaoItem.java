
package modelo.dao;

import java.util.List;
import modelo.dominio.HibernateUtil;
import modelo.entidad.Items;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Steven
 */
public class DaoItem {

    private final SessionFactory factoriaSession;
    private Session sesion;

    public DaoItem() {
        this.factoriaSession = HibernateUtil.getSessionFactory();
    }

    private void abrirConexion() {
        this.sesion = this.factoriaSession.openSession();
        this.sesion.beginTransaction();
    }

    private void cerrarConexion() {
        this.sesion.getTransaction().commit();
        this.sesion.close();
    }

    public boolean insertar(Items it) {
        try {
            this.abrirConexion();
            this.sesion.save(it);
            this.cerrarConexion();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        this.abrirConexion();
        Items it = (Items) this.sesion.get(Items.class, id);
        if (it != null) {
            this.sesion.delete(it);
        }
        this.cerrarConexion();
        return true;
    }

    public boolean modificar(Items it) {
        this.abrirConexion();
        this.sesion.update(it);
        this.cerrarConexion();
        return true;
    }

    public Items consultaItems(int id) {
        this.abrirConexion();
        Items it = (Items) this.sesion.get(Items.class, id);
        this.cerrarConexion();
        return it;
    }

    public List<Items> consultarTodo() {
        List<Items> lista;
        this.abrirConexion();
        lista = this.sesion.createQuery("from Items").list();
        this.cerrarConexion();
        return lista;
    }
}
