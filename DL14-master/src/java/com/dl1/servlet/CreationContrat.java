package com.dl1.servlet;

import com.dl1.beans.Contrat;
import com.dl1.beans.Employe;
import com.dl1.form.CreationContratForm;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "CreationContrat", urlPatterns = {"/CreationContrat"})
public class CreationContrat extends HttpServlet {
    public static final String ATT_CONTRAT            = "contrat";
    public static final String ATT_EMPLOYE            = "employe";
    
    public static final String ATT_FORM               = "form";
    public static final String ATT_FORM1              = "form1";
    
    public static final String SESSION_EMPLOYES       = "employes";
    public static final String APPLICATION_EMPLOYES   = "initEmployes";
    public static final String SESSION_CONTRATS       = "contrats";
    public static final String APPLICATION_CONTRATS  = "initContrats";
    
    public static final String ATT_SESSION_USER      = "sessionLogin";

    public static final String VUE_SUCCES            = "/WEB-INF/afficherContrat.jsp";
    public static final String VUE_FORM              = "/WEB-INF/creerContrat.jsp";
    public static final String VUE_PAGE1             = "/WEB-INF/page1.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession session = request.getSession();
       System.out.println("CreationContrat -->SESSION" + session.getId());
        
       Map<Long, Employe> employes;
       Employe employe = new Employe();
       employes =  (HashMap<Long, Employe>) employe.listerE("employe");
       session.setAttribute( SESSION_EMPLOYES, employes );
        
       System.out.println("CreationContrat -->SESSIONtostring" + session.getAttribute(SESSION_EMPLOYES));
        
       if (session.getAttribute(SESSION_EMPLOYES) != null) {
          employes = (HashMap<Long, Employe>) session.getAttribute( SESSION_EMPLOYES );
           System.out.println("CreationProprietaire -1--> table des personnes :" + employes.size());
        
    } 

       
       
       /* non connecté                    */
    //    System.out.println("CreationChantier MASESSION : " + session.getAttribute(ATT_SESSION_USER) == "null" );
       if (session.getAttribute(ATT_SESSION_USER) == "null" ) {
    //       this.getServletContext().getRequestDispatcher( VUE_PAGE1 ).forward( request, response );
             response.sendRedirect( request.getContextPath() + VUE_PAGE1 );     
       }
        
       System.out.println("CreationContrat : entree");
    /* Préparation de l'objet formulaire */
        CreationContratForm form = new CreationContratForm();

    /* Traitement de la requête et récupération du bean en résultant */
        
        Contrat contrat = form.creerContrat(request);
    
    /* Ajout du bean et de l'objet métier à l'objet requête */
        request.setAttribute( ATT_EMPLOYE, employe );

        request.setAttribute( ATT_CONTRAT, contrat );
        
        request.setAttribute( ATT_FORM, form );
        
        String message = form.getResultat() ;
        
        if (request.getParameter("doIt") != null) {
            System.out.println("CreationContrat : creer un contrat");
            contrat.insertContrat(contrat);
            System.out.println("CreationContrat ---> ID contrat :" + contrat.getIdContrat());
        }
    /* Si aucune erreur */
        
        if  ( form.getErreurs().isEmpty() )       {
            
            /* récupération de la map des Contrats dans la session */
            Map<Long, Contrat> contrats = (HashMap<Long, Contrat>) session.getAttribute( SESSION_CONTRATS );
            /* Si aucune map n'existe, alors initialisation d'une nouvelle map */
            if ( contrats == null ) {
                contrats = new HashMap<Long, Contrat>();
            }
            /* Puis ajout du contrat courant dans la map */
            contrats.put( contrat.getIdContrat(), contrat );
            /* Et enfin (ré)enregistrement de la map en session */
            session.setAttribute( SESSION_CONTRATS, contrats );
            System.out.println("CreationContrat -2--> table des contrats :" + contrats.size());
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
