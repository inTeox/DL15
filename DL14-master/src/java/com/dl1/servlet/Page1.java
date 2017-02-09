/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dl1.servlet;

import com.dl1.beans.Login;
import com.dl1.beans.Personne;
import com.dl1.form.LoginForm;
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
@WebServlet(name = "Page1", urlPatterns = {"/Page1"})
public class Page1 extends HttpServlet {
    
    public static final String VUE_SUCCES  = "/WEB-INF/menu.jsp";
    public static final String VUE_FORM    = "/WEB-INF/page1.jsp";
    
    public static final String ATT_SESSION_USER = "sessionLogin";
    public static final String ATT_FORM = "form";
    public static final String SESSION_PERSONNES      = "personnes";
/*    
    private final String driverDB = "Derby";
    private final String ipDB = "localhost";
    private final String databaseDB = "daliRenov";
    private final String loginDB = "vtt ";
    private final String pwdDB = "vtt";
*/    
    //Connection tmpConnection = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        System.out.println("Page1 -->SESSION" + session.getId());        
        
    if (request.getParameter("doIt") != null) {
            
            LoginForm form = new LoginForm();
            Login login = form.verifierIdentifiants(request);
            request.setAttribute(ATT_FORM, form);
             
            String message = form.getResultat() ;
                 
        if  (message.equals("vous êtes bien connecté")) {
                    System.out.println("PAGE1 :PASSAGE LOGIN");
		    session.setAttribute( ATT_SESSION_USER, login );
                    this.getServletContext().getRequestDispatcher( VUE_SUCCES ).forward( request, response );
         }
         else {  System.out.println("PAGE1 : pas LOGIN ");
                 session.setAttribute( ATT_SESSION_USER, null );
        	 this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
         //        response.sendRedirect( request.getContextPath() + VUE_FORM );
         }       
        
    }
    else {       System.out.println("PAGE1: pas3 ");
                 session.setAttribute( ATT_SESSION_USER, null ); 
        	 this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
         }
    } // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

    
    public void init() throws ServletException {
        super.init();    
        
        
    }
}
