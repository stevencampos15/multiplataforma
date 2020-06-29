
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.json.JsonException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.Respuesta;
import servicios.Tipoitems;
import servicios.Usuarios;
import servicios.WsBuscarTipoItem;
import servicios.WsListaTipoItems;
import servicios.WsListaUsuarios;

/**
 *
 * @author Steven
 */
@WebServlet(name = "CtrlUsuarios", urlPatterns = {"/usuarios"})
public class CtrlUsuarios extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = null;
        try (PrintWriter out = response.getWriter()) {

            String accionlista = request.getParameter("accionLista");
            String accion = request.getParameter("accion");
            String formulario = request.getParameter("btnFormulario");
            if (accionlista == null) {
                //Muestra lista
                WsListaUsuarios usuarios = new WsListaUsuarios();
                Respuesta resp = usuarios.getListaUsuarios(Respuesta.class);
                ArrayList<Usuarios> lstUsuarios = new ArrayList<Usuarios>();

                if (resp.getStatus().equals("ok")) {
                    ArrayList obj = (ArrayList) resp.getListaUsuarios();
                    if (obj != null) {
                        for (int i = 0; i < obj.size(); i++) {
                            try {
                                Usuarios usuario = new Usuarios();
                                usuario = (Usuarios) obj.get(i);
                                lstUsuarios.add(usuario);
                            } catch (JsonException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    request.setAttribute("accionLista", "mostrar");
                    request.setAttribute("listaUsuarios", lstUsuarios);
                }

            }
            rd = request.getRequestDispatcher("usuarios.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
