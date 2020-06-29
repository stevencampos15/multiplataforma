package modelo.dao;

import java.util.List;
import modelo.dominio.HibernateUtil;
import modelo.entidad.Pedidos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Steven
 */
public class DaoPedido {

    private final SessionFactory factoriaSession;
    private Session sesion;

    public DaoPedido() {
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

    public boolean insertar(Pedidos pe) {
        try {
            this.abrirConexion();
            this.sesion.save(pe);
            this.cerrarConexion();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        this.abrirConexion();
        Pedidos pe = (Pedidos) this.sesion.get(Pedidos.class, id);
        if (pe != null) {
            this.sesion.delete(pe);
        }
        this.cerrarConexion();
        return true;
    }

    public boolean modificar(Pedidos pe) {
        this.abrirConexion();
        this.sesion.update(pe);
        this.cerrarConexion();
        return true;
    }

    public Pedidos consultaPedido(int id) {
        this.abrirConexion();
        Pedidos pe = (Pedidos) this.sesion.get(Pedidos.class, id);
        this.cerrarConexion();
        return pe;
    }

    public List<Pedidos> consultarTodo() {
        List<Pedidos> lista;
        this.abrirConexion();
        lista = this.sesion.createQuery("from Pedidos").list();
        this.cerrarConexion();
        return lista;
    }

}
