/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dl1.servlet;

import com.dl1.beans.Personne;
import static com.dl1.servlet.Page1.SESSION_PERSONNES;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author veronique
 */
@WebServlet(name = "Menu", urlPatterns = {"/Menu"})
public class Menu extends HttpServlet {

    public static final String VUE_SUCCES  = "/WEB-INF/menu.jsp";
    public static final String VUE_FORM    = "/WEB-INF/menu.jsp";
    public static final String VUE_RETOUR  = "/WEB-INF/page1.jsp";
    
    private final String driverDB = "Derby";
    private final String ipDB = "localhost";
    private final String databaseDB = "daliRenov";
    private final String loginDB = "vtt ";
    private final String pwdDB = "vtt";
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
        HttpSession session = request.getSession();
    /*    
        System.out.println("param:" + request.getParameter("section") );
        if ("logout".equals(request.getParameter("section"))) {
          this.getServletContext().getRequestDispatcher( VUE_RETOUR ).forward( request, response );  
            
        }
    */    
    
    //    Personne personne = new Personne();
    //    HashMap<Long, Personne> personnes =  (HashMap<Long, Personne>) personne.lister();
    //    System.out.println("table personnes :" + personnes.toString()) ;
        
//     /* Et enfin (ré)enregistrement de la map en session */
    //    session.setAttribute( SESSION_PERSONNES, personnes );
    //    System.out.println("MENU ---> table des personnes :" + personnes.size());
               
        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
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
