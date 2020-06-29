package modelo.dao;

import java.util.ArrayList;
import java.util.List;
import modelo.dominio.HibernateUtil;
import modelo.entidad.Detallespedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Steven
 */
public class DaoDetallepedido {

    private final SessionFactory factoriaSession;
    private Session sesion;

    public DaoDetallepedido() {
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

    public boolean insertar(Detallespedido dp) {
        try {
            this.abrirConexion();
            this.sesion.save(dp);
            this.cerrarConexion();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        this.abrirConexion();
        Detallespedido dp = (Detallespedido) this.sesion.get(Detallespedido.class, id);
        if (dp != null) {
            this.sesion.delete(dp);
        }
        this.cerrarConexion();
        return true;
    }

    public boolean modificar(Detallespedido dp) {
        this.abrirConexion();
        this.sesion.update(dp);
        this.cerrarConexion();
        return true;
    }

    public Detallespedido consultadeDetallespedido(int id) {
        this.abrirConexion();
        Detallespedido dp = (Detallespedido) this.sesion.get(Detallespedido.class, id);
        this.cerrarConexion();
        return dp;
    }

    public List<Detallespedido> consultarTodo() {
        List<Detallespedido> lista;
        this.abrirConexion();
        lista = this.sesion.createQuery("from Detallespedido").list();
        this.cerrarConexion();
        return lista;
    }
    
    public List<Detallespedido> consultaPorPedido(int idPedido) {
        List<Detallespedido> lista;
        List<Detallespedido> lst = new ArrayList<>();
        this.abrirConexion();
        lista = this.sesion.createQuery("from Detallespedido").list();
        for (Detallespedido det : lista) {
            if(det.getPedidos().getIdPedido()==idPedido){
                lst.add(det);
            }
        }
        this.cerrarConexion();
        return lst;
    }
}
