package modelo.dao;

import java.util.List;
import modelo.dominio.HibernateUtil;
import modelo.entidad.Tipoitems;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Steven
 */
public class DaoTipoitem {

    private final SessionFactory factoriaSession;
    private Session sesion;

    public DaoTipoitem() {
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

    public boolean insertar(Tipoitems ti) {
        try {
            this.abrirConexion();
            this.sesion.save(ti);
            this.cerrarConexion();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        this.abrirConexion();
        Tipoitems ti = (Tipoitems) this.sesion.get(Tipoitems.class, id);
        if (ti != null) {
            this.sesion.delete(ti);
        }
        this.cerrarConexion();
        return true;
    }

    public boolean modificar(Tipoitems ti) {
        this.abrirConexion();
        this.sesion.update(ti);
        this.cerrarConexion();
        return true;
    }

    public Tipoitems consultaTipoitem(int id) {
        this.abrirConexion();
        Tipoitems ti = (Tipoitems) this.sesion.get(Tipoitems.class, id);
        this.cerrarConexion();
        return ti;
    }

    public List<Tipoitems> consultarTodo() {
        List<Tipoitems> lista;
        this.abrirConexion();
        lista = this.sesion.createQuery("from Tipoitems").list();
        this.cerrarConexion();
        return lista;
    }
}
