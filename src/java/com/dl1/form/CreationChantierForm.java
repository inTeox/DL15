/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dl1.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dl1.beans.Adresse;
import com.dl1.beans.Chantier;
import com.dl1.beans.Personne;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;



public final class CreationChantierForm {
    
    private static final String CHAMP_ID                    = "idChantier";
    private static final String CHAMP_NOM                   = "nomChantier";
    private static final String CHAMP_IDP                   = "idPersonne";
    
    private static final String CHAMP_LISTE_PERSONNES       = "listePersonnes";
    private static final String CHAMP_LISTE_STATUS          = "listeStatus";
    private static final String CHAMP_LIGNE1                = "ligne1Adresse";
    private static final String CHAMP_LIGNE2                = "ligne2Adresse";
    private static final String CHAMP_CP                    = "cpAdresse";
    private static final String CHAMP_VILLE                 = "villeAdresse";
    private static final String CHAMP_PAYS                  = "paysAdresse";
    
    private static final String CHAMP_NBRJOURDEVIS          = "nbrJourDevis";
    private static final String CHAMP_NBRJOURREAL           = "nbrJourReal";
    private static final String CHAMP_MTINITIAL             = "mtInitial";
    private static final String CHAMP_MTREALISE             = "mtRealise";
    private static final String CHAMP_STATUS                = "status";
    private static final String CHAMP_DATEDEBPREVU          = "dateDebPrevu";
    private static final String CHAMP_DATEDEBREEL           = "dateDebReel";
    private static final String CHAMP_DATEFINPREVU          = "dateFinPrevu";
    private static final String CHAMP_DATEFINREEL           = "dateFinReel";
    
    private static final String ADRESSE                     = "adresse";
    private static final String PERSONNE                    = "personne";
    private static final String SESSION_PERSONNES           = "personnes";
    
    private static final String FORMAT_DATE                 = "dd/MM/yyyy HH:mm:ss";
    
    private String resultat;

    private Map<String, String> erreurs                = new HashMap<String, String>(); 
  
    
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }
    
    public Chantier creerChantier( HttpServletRequest request , Adresse adresse) {
        Personne personne;
    
        String idAncienPersonne = getValeurChamp( request, CHAMP_LISTE_PERSONNES );
        System.out.println("CreationChantierForm --> id Personne : " + idAncienPersonne);
        Long id = null;
        try {
                id = Long.parseLong( idAncienPersonne );
            } catch ( NumberFormatException e ) {
                setErreur( CHAMP_LISTE_PERSONNES, "personne inconnu, merci d'utiliser le formulaire prévu à cet effet." );
                id = 0L;
            }
            /* Récupération de l'objet client correspondant dans la session */
        System.out.println("CreationChantierForm --> PASSAGE1 " );
        HttpSession session = request.getSession();
        personne = ( (Map<Long, Personne>) session.getAttribute( SESSION_PERSONNES ) ).get( id );
        System.out.println("CreationChantierForm --> PASSAGE2 " );
    
        Calendar calendar = Calendar.getInstance();
        Timestamp dt = new java.sql.Timestamp(calendar.getTime().getTime());

    //    System.out.println("CreationChantierForm --> PASSAGE2 " );
        String status = getValeurChamp( request, CHAMP_LISTE_STATUS );
        String nom = getValeurChamp( request, CHAMP_NOM );
        String idp = getValeurChamp( request, CHAMP_IDP );
        String nbrjd = getValeurChamp( request, CHAMP_NBRJOURDEVIS );
        String nbrjr = getValeurChamp( request, CHAMP_NBRJOURREAL );
        String mtinit = getValeurChamp( request, CHAMP_MTINITIAL );
        String mtreal = getValeurChamp( request, CHAMP_MTREALISE );    
//      
        String debprev = getValeurChamp( request, CHAMP_DATEDEBPREVU );
        String finprev = getValeurChamp( request, CHAMP_DATEFINPREVU );
    
        String debreel = getValeurChamp( request, CHAMP_DATEDEBREEL );
        String finreel = getValeurChamp( request, CHAMP_DATEFINREEL );
    
        
        Chantier chantier = new Chantier(); 
            
        chantier.setPersonne(personne);
        chantier.setStatus(status);
        
        traiterAdresse( adresse, chantier );
        traiterNom( nom, chantier);
        traiterNbrjd( nbrjd, chantier );
        traiterNbrjr( nbrjr, chantier );
        traiterMtinit( mtinit, chantier );
        traiterMtreal( mtreal, chantier );
        traiterDebprev( debprev, chantier );
        traiterFinprev( finprev, chantier );
        traiterDebreel( debreel, chantier );
        traiterFinreel( finreel, chantier );
        
        
        System.out.println("CreationChantierForm --> PASSAGE3 " + chantier.toString());
        chantier.setDcChantier(dt);
        chantier.setDfChantier(dt);
               
        return chantier;
    
    }    
    private void traiterNom( String nom, Chantier chantier ) {
        try {
            validationNom( nom );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        chantier.setNomChantier(nom);
    }
    
    private void traiterAdresse( Adresse adresse, Chantier chantier ) {
        if ( adresse == null ) {
            setErreur( CHAMP_LIGNE1, "Adresse inconnu" );
        }
        chantier.setAdresse(adresse);
              
    }
    private void traiterNbrjd( String nbrjd, Chantier chantier ) {
        try {
            validationNbrjd( nbrjd , chantier);
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_NBRJOURDEVIS, e.getMessage() );
        }
        }
    private void traiterNbrjr( String nbrjr, Chantier chantier ) {
        try {
            validationNbrjr( nbrjr , chantier);
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_NBRJOURREAL, e.getMessage() );
        }
        
    }
    
    private void traiterMtinit( String mtinit, Chantier chantier ) {
        try {
            validationMtinit( mtinit , chantier);
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_MTINITIAL, e.getMessage() );
        }
        
    }
    private void traiterMtreal( String mtreal, Chantier chantier ) {
        try {
            validationMtreal( mtreal , chantier );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_MTREALISE, e.getMessage() );
        }
        
    }
    private void traiterStatus( String status, Chantier chantier ) {
        try {
            validationStatus( status );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_STATUS, e.getMessage() );
        }
        chantier.setStatus(status);
    }
    private void traiterDebprev( String debprev, Chantier chantier ) {
        //Déclaration du SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
        if ( debprev != null ) {
        //Conversion en java.util.Date
        try {
            java.util.Date date = null;
        
            date = sdf.parse(debprev);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            chantier.setDateDebPrevu(sqlDate);
        } catch (ParseException ex) {
            Logger.getLogger(CreationChantierForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
    }    
    private void traiterFinprev( String finprev, Chantier chantier ) {
        //Déclaration du SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
        if ( finprev != null ) {
        //Conversion en java.util.Date
        try {
            java.util.Date date = null;
        
            date = sdf.parse(finprev);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            chantier.setDateFinPrevu(sqlDate);
        } catch (ParseException ex) {
            Logger.getLogger(CreationChantierForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 

        
    }
    private void traiterDebreel( String debreel, Chantier chantier ) {
        //Déclaration du SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
        if ( debreel != null ) {
        //Conversion en java.util.Date
        try {
            java.util.Date date = null;
        
            date = sdf.parse(debreel);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            chantier.setDateDebReel(sqlDate);
        } catch (ParseException ex) {
            Logger.getLogger(CreationChantierForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 

        
    }
    private void traiterFinreel( String finreel, Chantier chantier ) {
        //Déclaration du SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
        if ( finreel != null ) {
        //Conversion en java.util.Date
        try {
            java.util.Date date = null;
        
            date = sdf.parse(finreel);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            chantier.setDateFinReel(sqlDate);
        } catch (ParseException ex) {
            Logger.getLogger(CreationChantierForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 

        
    }
    private void validationNom( String nom ) throws FormValidationException {
        if ( nom != null ) {
            if ( nom.length() < 2 ) {
                throw new FormValidationException( "Le nom du chantier doit contenir au moins 2 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un nom pour ce chantier." );
        }
    }
    
    private void validationNbrjd( String nbrjd , Chantier chantier ) throws FormValidationException {
        if ( nbrjd != null ) {  
            Integer numNbrjd = Integer.parseInt(nbrjd);
            chantier.setNbrJourDevis(numNbrjd);

        } else {
            throw new FormValidationException( "Merci d'entrer un nombre de jours pour le devis." );
        }
    }
    
    private void validationNbrjr( String nbrjr, Chantier chantier ) throws FormValidationException {
        if ( nbrjr != null ) {   
            Integer numNbrjr = Integer.parseInt(nbrjr);
            chantier.setNbrJourReal(numNbrjr);
        } 
        /*
        else {
            throw new FormValidationException( "Merci d'entrer un nombre de jours réalisés." );
        }
        */
    }
    private void validationMtinit( String mtinit , Chantier chantier) throws FormValidationException {
        if ( mtinit != null ) {      
            float f = Float.valueOf(mtinit.trim()).floatValue();
            chantier.setMtInitial(f);
        } else {
            throw new FormValidationException( "Merci d'entrer le montant initial du devis." );
        }
    }
    private void validationMtreal( String mtreal , Chantier chantier) throws FormValidationException {
        if ( mtreal != null ) {            
            float f = Float.valueOf(mtreal.trim()).floatValue();
            chantier.setMtInitial(f);
        } 
        /*
        else {
            throw new FormValidationException( "Merci d'entrer le montant realisé." );
        }
        */
    }
    private void validationStatus( String status ) throws FormValidationException {
        if ( status != null ) {
            if (( status != "A REALISER" )&& ( status != "ENCOURS" ) &&( status != "TERMINE" )) {
                throw new FormValidationException( "Le status doit être ENCOURS ou TERMINE" );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un status." );
        }
    }
    private void validationPrenom( String prenom ) throws FormValidationException {
        if ( prenom != null && prenom.length() < 2 ) {
            throw new FormValidationException( "Le prénom d'utilisateur doit contenir au moins 2 caractères." );
        }
    }
    private void validationTelephone( String telephone ) throws FormValidationException {
        if ( telephone != null ) {
            if ( telephone.length() < 10 ) {
                throw new FormValidationException( "Le telephone doit contenir au moins 10 chiffres." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un teleephone." );
        }
    }
    private void validationMobile( String mobile ) throws FormValidationException {
        if ( mobile != null && mobile.length() < 10 ) {
            throw new FormValidationException( "Le telephone doit contenir au moins 10 chifffres." );
        }
    }
    private void validationMail( String mail ) throws FormValidationException {
        if ( mail != null && mail.length() < 7 ) {
            throw new FormValidationException( "Le mail doit contenir au moins 7 caractères." );
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
