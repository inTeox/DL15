
package com.dl1.servlet;

import com.dl1.beans.Adresse;
import com.dl1.beans.Personne;
import com.dl1.beans.Reside;

import com.dl1.form.CreationAdresseForm;
import com.dl1.form.CreationPersonneForm;
import com.dl1.form.CreationResideForm;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "CreationCarnetAdresses", urlPatterns = {"/CreationCarnetAdresses"})
public class CreationCarnetAdresses extends HttpServlet {
    public static final String ATT_PERSONNE          = "personne";
    public static final String ATT_ADRESSE            = "adresse";
    public static final String ATT_RESIDE            = "reside";
    public static final String ATT_FORM              = "form";
    public static final String ATT_FORM1              = "form1";
    public static final String ATT_FORM2              = "form2";
    public static final String SESSION_ADRESSES       = "adresses";
    public static final String APPLICATION_ADRESSES   = "initAdresses";
    public static final String SESSION_PERSONNES      = "personnes";
    public static final String APPLICATION_PERSONNES  = "initPersonnes";
    
    public static final String ATT_SESSION_USER = "sessionLogin";

    public static final String VUE_SUCCES            = "/WEB-INF/afficherCarnet.jsp";
    public static final String VUE_FORM              = "/WEB-INF/creerCarnetAdresse.jsp";
    public static final String VUE_PAGE1             = "/WEB-INF/page1.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession session = request.getSession();
       System.out.println("CreationCarnetAdresse -->SESSION" + session.getId());
        
    /* non connecté                    */
        System.out.println("CreationCarnetAdresses MASESSION : " + session.getAttribute(ATT_SESSION_USER) == "null" );
       if (session.getAttribute(ATT_SESSION_USER) == "null" ) {
    //       this.getServletContext().getRequestDispatcher( VUE_PAGE1 ).forward( request, response );
             response.sendRedirect( request.getContextPath() + VUE_PAGE1 );     
       }
        
       System.out.println("CreationCarnetAdresses : entree");
    /* Préparation de l'objet formulaire */
        CreationResideForm form = new CreationResideForm();
        CreationPersonneForm form1 = new CreationPersonneForm();
        CreationAdresseForm form2 = new CreationAdresseForm();

        
    /* Traitement de la requête et récupération du bean en résultant */
        Adresse adresse = form2.creerAdresse( request );
        Personne personne = form1.creerPersonne( request );
        Reside reside = form.creerReside( request, personne, adresse );
    
    /* Ajout du bean et de l'objet métier à l'objet requête */
        request.setAttribute( ATT_PERSONNE, personne );
        request.setAttribute( ATT_ADRESSE, adresse );
        request.setAttribute( ATT_RESIDE, reside );
        
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_FORM1, form1 );
        request.setAttribute( ATT_FORM2, form2 );
        
        String message = form.getResultat() ;
        
        if (request.getParameter("doIt") != null) {
            System.out.println("CreationCarnetAdresses : creer une adresse");
            adresse.insertAdresse(adresse);
            System.out.println("CreationCarnetAdresses : creer une personne");
            personne.insertPersonne(personne);
            System.out.println("CreationCarnetAdresses : creer un lien reside");
             
            form.setResultat(reside.insertReside(reside));
            System.out.println("CreationCarnetAdresses ---> ID personne :" + personne.getIdPersonne());
        }
    /* Si aucune erreur */
        
        if ( ( form.getErreurs().isEmpty() )  && ( form1.getErreurs().isEmpty() )
                && ( form2.getErreurs().isEmpty() ) ){
            /* Alors récupération de la map des adresses dans la session */
        //    HttpSession session = request.getSession();
            Map<Long, Adresse> adresses = (HashMap<Long, Adresse>) session.getAttribute( SESSION_ADRESSES );
            /* Si aucune map n'existe, alors initialisation d'une nouvelle map */
            if ( adresses == null ) {
                adresses = new HashMap<Long, Adresse>();
            }
            /* Puis ajout de l'adresse de la personne courante dans la map */
            //adresses.put( personne.getClient().getId(), commande.getClient() );
            adresses.put( reside.getAdresse().getIdAdresse(), reside.getAdresse());
            /* Et enfin (ré)enregistrement de la map en session */
            session.setAttribute( SESSION_ADRESSES, adresses );

            /* Ensuite récupération de la map des Personnes dans la session */
            Map<Long, Personne> personnes = (HashMap<Long, Personne>) session.getAttribute( SESSION_PERSONNES );
            /* Si aucune map n'existe, alors initialisation d'une nouvelle map */
            if ( personnes == null ) {
                personnes = new HashMap<Long, Personne>();
            }
            /* Puis ajout de la personne courante dans la map */
            personnes.put( personne.getIdPersonne(), personne );
            /* Et enfin (ré)enregistrement de la map en session */
            session.setAttribute( SESSION_PERSONNES, personnes );
            System.out.println("CreationCarnetAdresse -2--> table des personnes :" + personnes.size());
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
