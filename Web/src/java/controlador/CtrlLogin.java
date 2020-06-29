/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.Respuesta;
import servicios.Tipousuarios;
import servicios.Usuarios;
import servicios.WsLogin;

/**
 *
 * @author Steven
 */
public class CtrlLogin extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            RequestDispatcher rd = null;
            String inicioSesion = request.getParameter("btnIniciarSesion");
            String url = "";
            if (inicioSesion != null) {
                String username = request.getParameter("username");
                String pwd = request.getParameter("pwd");
                WsLogin login = new WsLogin();
                Respuesta resp = login.getLogin(Respuesta.class, username, pwd);
                if (resp.getStatus().equals("ok")) {
                    url = "menuPrincipal.jsp";
                    Usuarios usuario = new Usuarios();
                    usuario.setNombreCliente(resp.getUsuario().getNombreCliente());
                    usuario.setApellidoCliente(resp.getUsuario().getApellidoCliente());
                    usuario.setDocumento(resp.getUsuario().getDocumento());
                    usuario.setUsername(resp.getUsuario().getUsername());
                    usuario.setPwd(resp.getUsuario().getPwd());
                    Tipousuarios tipoUsuario = new Tipousuarios();
                    tipoUsuario.setIdTipoUsuario(resp.getUsuario().getTipousuarios().getIdTipoUsuario());
                    tipoUsuario.setNombreTipoUsuario(resp.getUsuario().getTipousuarios().getNombreTipoUsuario());
                    if (tipoUsuario.getIdTipoUsuario() == 2) {
                        url = "login.jsp";
                        request.setAttribute("resultado", "errorTipoUsuario");
                    } else {
                        usuario.setTipousuarios(tipoUsuario);
                        request.setAttribute("usuario", usuario);
                    }

                } else {
                    url = "login.jsp";
                    request.setAttribute("resultado", "error");
                }
                rd = request.getRequestDispatcher(url);
            }
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
