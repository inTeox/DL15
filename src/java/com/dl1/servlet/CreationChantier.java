package com.dl1.servlet;

import com.dl1.beans.Adresse;
import com.dl1.beans.Chantier;
import com.dl1.beans.Personne;

import com.dl1.form.CreationAdresseForm;
import com.dl1.form.CreationChantierForm;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "CreationChantier", urlPatterns = {"/CreationChantier"})
public class CreationChantier extends HttpServlet {
    public static final String ATT_PERSONNE       = "personne";
    public static final String ATT_ADRESSE            = "adresse";
    public static final String ATT_CHANTIER           = "chantier";
    public static final String ATT_FORM               = "form";
    public static final String ATT_FORM1              = "form1";
    public static final String ATT_FORM2              = "form2";
    public static final String SESSION_ADRESSES       = "adresses";
    public static final String APPLICATION_ADRESSES   = "initAdresses";
    public static final String SESSION_PERSONNES      = "personnes";
    public static final String APPLICATION_PERSONNES  = "initPersonnes";
    public static final String SESSION_CHANTIERS      = "chantiers";
    public static final String APPLICATION_CHANTIERS  = "initChantiers";
    
    public static final String ATT_SESSION_USER      = "sessionLogin";

    public static final String VUE_SUCCES            = "/WEB-INF/afficherChantier.jsp";
    public static final String VUE_FORM              = "/WEB-INF/creerChantier.jsp";
    public static final String VUE_PAGE1             = "/WEB-INF/page1.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession session = request.getSession();
       System.out.println("CreationChantier -->SESSION" + session.getId());
        
       Map<Long, Personne> personnes;
       Personne personne = new Personne();
       personnes =  (HashMap<Long, Personne>) personne.listerClient();
       session.setAttribute( SESSION_PERSONNES, personnes );
        
       if (session.getAttribute(SESSION_PERSONNES) != null) {
          personnes = (HashMap<Long, Personne>) session.getAttribute( SESSION_PERSONNES );
           System.out.println("CreationProprietaire -1--> table des personnes :" + personnes.size());
        
    } 

       
       
       /* non connecté                    */
    //    System.out.println("CreationChantier MASESSION : " + session.getAttribute(ATT_SESSION_USER) == "null" );
       if (session.getAttribute(ATT_SESSION_USER) == "null" ) {
    //       this.getServletContext().getRequestDispatcher( VUE_PAGE1 ).forward( request, response );
             response.sendRedirect( request.getContextPath() + VUE_PAGE1 );     
       }
        
       System.out.println("CreationChantier : entree");
    /* Préparation de l'objet formulaire */
        CreationChantierForm form = new CreationChantierForm();
        CreationAdresseForm form2 = new CreationAdresseForm();

        
    /* Traitement de la requête et récupération du bean en résultant */
        Adresse adresse = form2.creerAdresse( request );
        Chantier chantier = form.creerChantier(request, adresse );
    
    /* Ajout du bean et de l'objet métier à l'objet requête */
        request.setAttribute( ATT_ADRESSE, adresse );
        request.setAttribute( ATT_PERSONNE, personne );

        request.setAttribute( ATT_CHANTIER, chantier );
        
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_FORM2, form2 );
        
        String message = form.getResultat() ;
        
        if (request.getParameter("doIt") != null) {
            System.out.println("CreationChantier : creer une adresse");
            adresse.insertAdresse(adresse);
            System.out.println("CreationChantier : creer un chantier");
            chantier.insertChantier(chantier);
            System.out.println("CreationChantier ---> ID chantier :" + chantier.getIdChantier());
        }
    /* Si aucune erreur */
        
        if ( ( form.getErreurs().isEmpty() )  
                && ( form2.getErreurs().isEmpty() ) ){
            /* Alors récupération de la map des adresses dans la session */
            Map<Long, Adresse> adresses = (HashMap<Long, Adresse>) session.getAttribute( SESSION_ADRESSES );
            /* Si aucune map n'existe, alors initialisation d'une nouvelle map */
            if ( adresses == null ) {
                adresses = new HashMap<Long, Adresse>();
            }
            /* Puis ajout de l'adresse de la personne courante dans la map */
            //adresses.put( personne.getClient().getId(), commande.getClient() );
            adresses.put( chantier.getAdresse().getIdAdresse(), chantier.getAdresse());
            /* Et enfin (ré)enregistrement de la map en session */
            session.setAttribute( SESSION_ADRESSES, adresses );
            
            /* Ensuite récupération de la map des Chantiers dans la session */
            Map<Long, Chantier> chantiers = (HashMap<Long, Chantier>) session.getAttribute( SESSION_CHANTIERS );
            /* Si aucune map n'existe, alors initialisation d'une nouvelle map */
            if ( chantiers == null ) {
                chantiers = new HashMap<Long, Chantier>();
            }
            /* Puis ajout de la personne courante dans la map */
            chantiers.put( chantier.getIdChantier(), chantier );
            /* Et enfin (ré)enregistrement de la map en session */
            session.setAttribute( SESSION_CHANTIERS, chantiers );
            System.out.println("CreationChantier -2--> table des chantiers :" + chantiers.size());
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
