/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dl1.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dl1.beans.Contrat;
import com.dl1.beans.Employe;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;



public final class CreationContratForm {
    
    private static final String CHAMP_ID                    = "idContrat";
    private static final String CHAMP_IDP                   = "idPersonne";
    private static final String CHAMP_NOM                   = "nom";
    
    private static final String CHAMP_LISTE_EMPLOYES        = "listeEmployes";
    private static final String CHAMP_POSTE                 = "poste";
    private static final String CHAMP_QUALIF                = "qualif";
    private static final String CHAMP_REM                   = "remunerationHoraire";
    private static final String CHAMP_DATEDEB               = "dateDebContrat";
    private static final String CHAMP_DATEFIN               = "dateFinContrat";
    
    private static final String EMPLOYE                     = "employe";
    private static final String SESSION_EMPLOYES            = "employes";
    
    private static final String FORMAT_DATE                 = "dd/MM/yyyy HH:mm:ss";
    
    private String resultat;

    private Map<String, String> erreurs                = new HashMap<String, String>(); 
  
    
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }
    
    public Contrat creerContrat( HttpServletRequest request ) {
        Employe employe;
    
        String idAncienPersonne = getValeurChamp( request, CHAMP_LISTE_EMPLOYES );
        System.out.println("CreationContratForm --> id Employe : " + idAncienPersonne);
        Long id = null;
        try {
                id = Long.parseLong( idAncienPersonne );
            } catch ( NumberFormatException e ) {
                setErreur( CHAMP_LISTE_EMPLOYES, "personne inconnu, merci d'utiliser le formulaire prévu à cet effet." );
                id = 0L;
            }
            /* Récupération de l'objet client correspondant dans la session */
        System.out.println("CreationContratForm --> PASSAGE1 " );
        HttpSession session = request.getSession();
        employe = ( (Map<Long, Employe>) session.getAttribute( SESSION_EMPLOYES ) ).get( id );
        System.out.println("CreationContratForm --> PASSAGE2 " );
    
        Calendar calendar = Calendar.getInstance();
        Timestamp dt = new java.sql.Timestamp(calendar.getTime().getTime());

        String emp = getValeurChamp( request, CHAMP_LISTE_EMPLOYES );
        String nom = getValeurChamp( request, CHAMP_NOM );
         
        String poste = getValeurChamp( request, CHAMP_POSTE );
        String qualif = getValeurChamp( request, CHAMP_QUALIF );
        String rem = getValeurChamp( request, CHAMP_REM );
//      
        String deb = getValeurChamp( request, CHAMP_DATEDEB );
        String fin = getValeurChamp( request, CHAMP_DATEFIN );
    
        Contrat contrat = new Contrat(); 
            
        contrat.setEmploye(employe);
        
        traiterPoste( poste, contrat );
        traiterQualif( qualif, contrat );
        traiterRem( rem, contrat );
        traiterDeb( deb, contrat );
        traiterFin( fin, contrat );
        
        System.out.println("CreationContratForm --> PASSAGE3 " + contrat.toString());
        contrat.setDcContrat(dt);
        contrat.setDfContrat(dt);
               
        return contrat;
    
    }    
    private void traiterPoste( String poste, Contrat contrat ) {
        try {
            validationPoste( poste );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_POSTE, e.getMessage() );
        }
        contrat.setPoste(poste);
    }
    private void traiterQualif( String qualif, Contrat contrat ) {
        try {
            validationQualif( qualif );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_QUALIF, e.getMessage() );
        }
        contrat.setQualif(qualif);
    }
    
    private void traiterRem( String rem, Contrat contrat ) {
        try {
            validationRem( rem , contrat);
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_REM, e.getMessage() );
        }
        
    }
    private void traiterDeb( String deb, Contrat contrat ) {
        //Déclaration du SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
        if ( deb != null ) {
        //Conversion en java.util.Date
        try {
            java.util.Date date = null;
        
            date = sdf.parse(deb);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            contrat.setDateDebContrat(sqlDate);
        } catch (ParseException ex) {
            Logger.getLogger(CreationContratForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
    }    
    private void traiterFin( String fin, Contrat contrat ) {
        //Déclaration du SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
        if ( fin != null ) {
        //Conversion en java.util.Date
        try {
            java.util.Date date = null;
        
            date = sdf.parse(fin);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            contrat.setDateFinContrat(sqlDate);
        } catch (ParseException ex) {
            Logger.getLogger(CreationContratForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 

        
    }
    
    private void validationPoste( String poste ) throws FormValidationException {
        if ( poste != null ) {
            if ( poste.length() < 2 ) {
                throw new FormValidationException( "Le nom du poste doit contenir au moins 2 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un poste pour ce contrat." );
        }
    }
    private void validationQualif( String qualif ) throws FormValidationException {
        if ( qualif != null ) {
            
        } else {
            throw new FormValidationException( "Merci d'entrer un poste pour ce contrat." );
        }
    }
    
    private void validationRem( String rem , Contrat contrat) throws FormValidationException {
        if ( rem != null ) {      
            float f = Float.valueOf(rem.trim()).floatValue();
            contrat.setRemunerationHoraire(f);
        } else {
            throw new FormValidationException( "Merci d'entrer la rémunération horaire." );
        }
    }
    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
   
     
}
