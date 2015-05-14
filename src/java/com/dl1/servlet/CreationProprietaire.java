/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dl1.servlet;

import com.dl1.beans.Personne;
import com.dl1.beans.Proprietaire;
import com.dl1.form.CreationProprietaireForm;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CreationProprietaire", urlPatterns = {"/Proprietaire"})
public class CreationProprietaire extends HttpServlet {

    public static final   String ATT_PERSONNE          = "personne";
    public static final   String ATT_PROPRIETAIRE      = "proprietaire";
    public static final   String ATT_FORM              = "form";
    public static final   String SESSION_PERSONNES       = "personnes";
    public static final   String APPLICATION_PERSONNES   = "initPersonnes";
    public static final   String SESSION_PROPRIETAIRES       = "proprietaires";
    public static final   String APPLICATION_PROPRIETAIRES   = "initProprietaires";
    
    public static final String ATT_SESSION_USER = "sessionLogin";

    public static final String VUE_SUCCES            = "/WEB-INF/afficherProprietaire.jsp";
    public static final String VUE_FORM              = "/WEB-INF/creerProprietaire.jsp";
    public static final String VUE_PAGE1             = "/WEB-INF/page1.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession session = request.getSession();
       
       Map<Long, Personne> personnes;
       Personne personne = new Personne();
       personnes =  (HashMap<Long, Personne>) personne.lister("proprietaire");
       session.setAttribute( SESSION_PERSONNES, personnes );
       System.out.println("CreationProprietaire -1--> table des personnes :" + personnes.size());
       
       System.out.println("CreationProprietaire -->SESSION" + session.getId());
        
       System.out.println("CreationProprietaire -->SESSIONtostring" + session.getAttribute(SESSION_PERSONNES));
        
       if (session.getAttribute(SESSION_PERSONNES) != null) {
          personnes = (HashMap<Long, Personne>) session.getAttribute( SESSION_PERSONNES );
           System.out.println("CreationProprietaire -1--> table des personnes :" + personnes.size());
        
    } 
       
    
       if (session.getAttribute(ATT_SESSION_USER) == "null" ) {
    //       this.getServletContext().getRequestDispatcher( VUE_PAGE1 ).forward( request, response );
             response.sendRedirect( request.getContextPath() + VUE_PAGE1 );     
       }
        
       System.out.println("CreationProprietaire : entree");
    /* Préparation de l'objet formulaire */
        CreationProprietaireForm form = new CreationProprietaireForm();
        
        
    /* Traitement de la requête et récupération du bean en résultant */
        Proprietaire proprietaire = form.creerProprietaire(request );
        System.out.println("CreationProprietaire retour creerProprietaire --> PASSAGE6 " );
    /* Ajout du bean et de l'objet métier à l'objet requête */
        request.setAttribute( ATT_PROPRIETAIRE, proprietaire );
        
        request.setAttribute( ATT_FORM, form );
        
   //     Personne personne = new Personne();
   //     personnes =  (HashMap<Long, Personne>) personne.lister("proprietaire");
   //     System.out.println("table personnes :" + personnes.toString()) ;
   //     session.setAttribute( SESSION_PERSONNES, personnes );
   //     System.out.println("CreationProprietaire -2--> table des personnes :" + personnes.size());
        
        System.out.println("CreationProprietaire doIt :" + request.getParameter("doIt"));
        if (request.getParameter("doIt") != null) {
            System.out.println("CreationProprietaire : creer un proprietaire");
            form.setResultat(Proprietaire.insertProprietaire(proprietaire, personne));
            
        }
        System.out.println("CreationProprietaire retour creerProprietaire --> PASSAGE7 " );
        System.out.println("CreationProprietaire form erreur --> " + form.getErreurs() );
    /* Si aucune erreur */
        if ( form.getErreurs().isEmpty() ) {
            System.out.println("CreationProprietaire PAS ERREUR");
            /* Alors récupération de la map des adresses dans la session */
        //    HttpSession session = request.getSession();
            personnes = (HashMap<Long, Personne>) session.getAttribute( SESSION_PERSONNES );
            /* Si aucune map n'existe, alors initialisation d'une nouvelle map */
            if ( personnes == null ) {
                personnes = new HashMap<Long, Personne>();
            }
            personnes.put( proprietaire.getIdPersonne(), personne);
            /* Et enfin (ré)enregistrement de la map en session */
            session.setAttribute( SESSION_PERSONNES, personnes );
            System.out.println("CreationProprietaire --3-> table des personnes :" + personnes.size());   
            /* Affichage de la fiche récapitulative */
            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
        } else {
            /* Sinon, ré-affichage du formulaire de création avec les erreurs */
            System.out.println("CreationProprietaire retour creerProprietaire --> PASSAGE8 " );
            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
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

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
