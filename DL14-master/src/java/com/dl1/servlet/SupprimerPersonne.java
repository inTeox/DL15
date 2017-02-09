/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dl1.servlet;

import com.dl1.beans.Carnet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(name = "SupprimerPersonne", urlPatterns = {"/SuppressionPersonne"})
public class SupprimerPersonne extends HttpServlet {

    public static final String ATT_PERSONNE = "personne";
    public static final String ATT_CARNET = "carnet";
    public static final String ATT_FORM     = "form";
    
    public static final String PARAM_IDPERSONNE = "idPersonne";
    public static final String SESSION_CARNET       = "carnet";
    
    public static final String VUE          = "/WEB-INF/listerCarnet.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("ENTER SupprimerPersonne");
        String idPersonne = getValeurParametre( request, PARAM_IDPERSONNE );
        
        /* Récupération de la Map des contacts enregistrées en session */
        HttpSession session = request.getSession();
        Map<Long, Carnet> carnet = (HashMap<Long, Carnet>) session.getAttribute( SESSION_CARNET );

        /* Si l' idPErsonne et la Map des contacts ne sont pas vides */
        if ( idPersonne != null && carnet != null ) {
            /* Alors suppression de la commande de la Map */
            Integer id1 = new Integer(idPersonne); 
            long idL =  id1.longValue(); 
            carnet.remove(idL );
            /* Et remplacement de l'ancienne Map en session par la nouvelle */
            session.setAttribute( SESSION_CARNET, carnet );
        }
        
     /* Redirection vers la fiche récapitulative */
        response.sendRedirect( request.getContextPath() + VUE );   
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

    /*
     * Méthode utilitaire qui retourne null si un paramètre est vide, et son
     * contenu sinon.
     */

    private static String getValeurParametre( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}

