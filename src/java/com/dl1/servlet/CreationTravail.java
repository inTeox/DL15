package com.dl1.servlet;

import com.dl1.beans.Chantier;
import com.dl1.beans.Contrat;
import com.dl1.beans.Employe;
import com.dl1.beans.Travail;
import com.dl1.form.CreationContratForm;
import com.dl1.form.CreationTravailForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "CreationTravail", urlPatterns = {"/CreationTravail"})
public class CreationTravail extends HttpServlet {
    public static final String ATT_SEMAINE            = "semaine";
    public static final String ATT_CHANTIER           = "chantier";
    public static final String ATT_EMPLOYE            = "employe";
    
    public static final String ATT_FORM               = "form";
    public static final String ATT_FORM1              = "form1";
    
    public static final String SESSION_EMPLOYES       = "employes";
    public static final String SESSION_CHANTIERS      = "chantiers";
    public static final String APPLICATION_CHANTIERS  = "initChantiers";
    public static final String APPLICATION_EMPLOYES   = "initEmployes";
    public static final String SESSION_TRAVAILS       = "travails";
    public static final String APPLICATION_TRAVAILS   = "initTravails";
    
    public static final String ATT_SESSION_USER      = "sessionLogin";

    public static final String VUE_SUCCES            = "/WEB-INF/afficherTravail.jsp";
    public static final String VUE_FORM              = "/WEB-INF/creerTravail.jsp";
    public static final String VUE_PAGE1             = "/WEB-INF/page1.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession session = request.getSession();
       System.out.println("CreationTravail -->SESSION" + session.getId());
        
       Map<Long, Chantier> chantiers;
       Chantier chantier = new Chantier();
       chantiers =  (HashMap<Long, Chantier>) chantier.lister();
       session.setAttribute( SESSION_CHANTIERS, chantiers );
        
       System.out.println("CreationTravail -->SESSIONtostring" + session.getAttribute(SESSION_CHANTIERS));
        
       if (session.getAttribute(SESSION_CHANTIERS) != null) {
          chantiers = (HashMap<Long, Chantier>) session.getAttribute( SESSION_CHANTIERS );
           System.out.println("CreationTravail -1--> table des chantiers :" + chantiers.size());
          } 
    
       Map<Long, Employe> employes;
       Employe employe = new Employe();
       employes =  (HashMap<Long, Employe>) employe.listerE("employe");
       session.setAttribute( SESSION_EMPLOYES, employes );
       
    if (session.getAttribute(SESSION_EMPLOYES) != null) {
          employes = (HashMap<Long, Employe>) session.getAttribute( SESSION_EMPLOYES );
    }    
       
       /* non connecté                    */
    //    System.out.println("CreationChantier MASESSION : " + session.getAttribute(ATT_SESSION_USER) == "null" );
       if (session.getAttribute(ATT_SESSION_USER) == "null" ) {
    //       this.getServletContext().getRequestDispatcher( VUE_PAGE1 ).forward( request, response );
             response.sendRedirect( request.getContextPath() + VUE_PAGE1 );     
       }
        
    /* Préparation de l'objet formulaire */
        CreationTravailForm form = new CreationTravailForm();

    /* Traitement de la requête et récupération du bean en résultant */
        ArrayList<Travail> semaine = (ArrayList<Travail>) form.creerTravail(request);
        System.out.println("CreationTravail : CREATION SEMAINE ---- :" + semaine);
            
    /* Ajout du bean et de l'objet métier à l'objet requête */
        request.setAttribute( ATT_CHANTIER, chantier );
        request.setAttribute( ATT_EMPLOYE, employe );

        request.setAttribute( ATT_SEMAINE, semaine );
        
        request.setAttribute( ATT_FORM, form );
        Travail travail = new Travail();
        String message = form.getResultat() ;
        
        if (request.getParameter("doIt") != null) {
            System.out.println("CreationTravail : creer un travail");
            for (Travail s : semaine) {
                  travail.insertTravail(s);
                }
//            System.out.println("CreationTravail ---> ID travail :" + travail.getIdTravail());
        }
    /* Si aucune erreur */
        
        if  ( form.getErreurs().isEmpty() )       {
            
            /* récupération de la map des Contrats dans la session */
            Map<Long, Travail> travails = (HashMap<Long, Travail>) session.getAttribute( SESSION_TRAVAILS );
            /* Si aucune map n'existe, alors initialisation d'une nouvelle map */
            if ( travails == null ) {
                travails = new HashMap<Long, Travail>();
            }
            /* Puis ajout du contrat courant dans la map */
            travails.put( travail.getIdTravail(), travail );
            /* Et enfin (ré)enregistrement de la map en session */
            session.setAttribute( SESSION_TRAVAILS, travails );
            System.out.println("CreationTravail -2--> table des travails :" + travails.size());
            /* Affichage de la fiche récapitulative */
            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
        } else {
            /* Sinon, ré-affichage du formulaire de création avec les erreurs */
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
