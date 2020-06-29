
package modelo.dao;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import modelo.dominio.HibernateUtil;
import modelo.entidad.Usuarios;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Steven
 */
public class DaoUsuario {

    private final SessionFactory factoriaSession;
    private Session sesion;

    public DaoUsuario() {
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

    public boolean insertar(Usuarios u) {
        try {
            this.abrirConexion();
            this.sesion.save(u);
            this.cerrarConexion();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        this.abrirConexion();
        Usuarios u = (Usuarios) this.sesion.get(Usuarios.class, id);
        if (u != null) {
            this.sesion.delete(u);
        }
        this.cerrarConexion();
        return true;
    }

    public boolean modificar(Usuarios u) {
        this.abrirConexion();
        this.sesion.update(u);
        this.cerrarConexion();
        return true;
    }

    public Usuarios consultaUsuario(int id) {
        this.abrirConexion();
        Usuarios u = (Usuarios) this.sesion.get(Usuarios.class, id);
        this.cerrarConexion();
        return u;
    }

    public List<Usuarios> consultarTodo() {
        List<Usuarios> lista;
        this.abrirConexion();
        lista = this.sesion.createQuery("from Usuarios").list();
        this.cerrarConexion();
        return lista;
    }

    public Usuarios login(String lg, String clave) {
        Usuarios usuario = new Usuarios();
        List<Usuarios> lst = this.consultarTodo();
        boolean encontrado = false;
        int cont = 0;
        String key = toMD5(clave);
        while (cont < lst.size() && !encontrado) {
            if (lst.get(cont).getUsername().equals(lg)) {
                //Sobre la clave se hara comprobacion MD5 sobre Java
                if (lst.get(cont).getPwd().equals(key)) {
                    encontrado = true;
                    usuario = lst.get(cont);
                } else {
                    break;
                }
            } else {
                cont++;
            }
        }
        return usuario;
    }

    public String toMD5(String clave) {
        MessageDigest md = null;
        byte[] raw = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(clave.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return String.format("%032x", new BigInteger(1, md.digest()));
    }

}
