package modelo.dao;

import java.util.List;
import modelo.dominio.HibernateUtil;
import modelo.entidad.Tipousuarios;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Steven
 */
public class DaoTipousuario {

    private final SessionFactory factoriaSession;
    private Session sesion;

    public DaoTipousuario() {
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

    public boolean insertar(Tipousuarios tu) {
        try {
            this.abrirConexion();
            this.sesion.save(tu);
            this.cerrarConexion();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        this.abrirConexion();
        Tipousuarios tu = (Tipousuarios) this.sesion.get(Tipousuarios.class, id);
        if (tu != null) {
            this.sesion.delete(tu);
        }
        this.cerrarConexion();
        return true;
    }

    public boolean modificar(Tipousuarios tu) {
        this.abrirConexion();
        this.sesion.update(tu);
        this.cerrarConexion();
        return true;
    }

    public Tipousuarios consultaTipoUsuario(int id) {
        this.abrirConexion();
        Tipousuarios tu = (Tipousuarios) this.sesion.get(Tipousuarios.class, id);
        this.cerrarConexion();
        return tu;
    }

    public List<Tipousuarios> consultarTodo() {
        List<Tipousuarios> lista;
        this.abrirConexion();
        lista = this.sesion.createQuery("from Tipousuarios").list();
        this.cerrarConexion();
        return lista;
    }
}
