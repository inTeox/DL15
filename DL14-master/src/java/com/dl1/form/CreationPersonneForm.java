/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dl1.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dl1.beans.Personne;
import com.dl1.beans.Adresse;
import com.dl1.beans.Reside;
import java.sql.Timestamp;
import java.util.Calendar;



public final class CreationPersonneForm {
    
    private static final String CHAMP_ID                = "idPersonne";
    private static final String CHAMP_CHOIX_GROUPE      = "choixGroupePersonne";
    private static final String CHAMP_NOM               = "nomPersonne";
    private static final String CHAMP_PRENOM            = "prenomPersonne";
    
    private static final String CHAMP_LIGNE1            = "ligne1Adresse";
    private static final String CHAMP_LIGNE2            = "ligne2Adresse";
    private static final String CHAMP_CP                = "cpAdresse";
    private static final String CHAMP_VILLE             = "villeAdresse";
    private static final String CHAMP_PAYS              = "paysAdresse";
    private static final String CHAMP_TELEPHONE         = "telephone";
    private static final String CHAMP_MOBILE            = "mobile";
    private static final String CHAMP_MAIL              = "mail";
    private static final String CHAMP_NOTE              = "noteP";
    
    private static final String EMPLOYE                 = "employe";
    private static final String CLIENT                  = "client";
    private static final String PROSPECT                = "prospect";
    
    private static final String FORMAT_DATE             = "dd/MM/yyyy HH:mm:ss";
    
    private String resultat;

    private Map<String, String> erreurs                = new HashMap<String, String>(); 
  
    
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }
    
    public Personne creerPersonne( HttpServletRequest request ) {
        Adresse adresse;
    /*        
     * L'objet métier pour valider la création d'un client existe déjà,
     * il est donc déconseillé de dupliquer ici son contenu ! À la
     * place, il suffit de passer la requête courante à l'objet métier
     * existant et de récupérer l'objet Client créé.
     */
    CreationAdresseForm adresseForm = new CreationAdresseForm();
    adresse = adresseForm.creerAdresse( request );

    /*
     * Et très important, il ne faut pas oublier de récupérer le contenu
     * de la map d'erreur créée par l'objet métier CreationClientForm
     * dans la map d'erreurs courante, actuellement vide.
    */
    erreurs = adresseForm.getErreurs();
    
    Calendar calendar = Calendar.getInstance();
    Timestamp dt = new java.sql.Timestamp(calendar.getTime().getTime());

    
    String nom = getValeurChamp( request, CHAMP_NOM );
    String prenom = getValeurChamp( request, CHAMP_PRENOM );
    String telephone = getValeurChamp( request, CHAMP_TELEPHONE);
    String mobile = getValeurChamp( request, CHAMP_MOBILE);
    String mail = getValeurChamp( request, CHAMP_MAIL);
    String noteP = getValeurChamp( request, CHAMP_NOTE);
    
    
    
    //CHAMP_CHOIX_GROUPE
    
    Personne personne = new Personne();
    
    String choixGroupePersonne = getValeurChamp( request, CHAMP_CHOIX_GROUPE );
    if ( EMPLOYE.equals( choixGroupePersonne ) ) {
       personne.setType(EMPLOYE); 
    } else if (CLIENT.equals( choixGroupePersonne ) ){
       personne.setType(CLIENT);
    }else {
       personne.setType(PROSPECT); 
    }
    
            
            traiterAdresse( adresse, personne );

            personne.setDateDebutP(dt);
            personne.setDateFinP(dt);
            personne.setNoteP(noteP);
            
            traiterNom( nom, personne);
            traiterPrenom( prenom, personne );
            traiterTelephone( telephone, personne );
            traiterMobile( mobile, personne );
            traiterMail( mail, personne );
    
            
        return personne;
    
    }    
    private void traiterNom( String nom, Personne personne ) {
        try {
            validationNom( nom );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        personne.setNom( nom );
    }

    private void traiterPrenom( String prenom, Personne personne ) {
        try {
            validationPrenom( prenom );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_PRENOM, e.getMessage() );
        }
        personne.setPrenom(prenom);
    }
    
    private void traiterAdresse( Adresse adresse, Personne personne ) {
        if ( adresse == null ) {
            setErreur( CHAMP_LIGNE1, "Adresse inconnu" );
        }
        //personne.setClient( client );
              
    }
    private void traiterTelephone( String telephone, Personne personne ) {
        try {
            validationTelephone( telephone );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_PRENOM, e.getMessage() );
        }
        personne.setTelephone(telephone);
    }
    private void traiterMobile( String mobile, Personne personne ) {
        try {
            validationMobile( mobile );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_PRENOM, e.getMessage() );
        }
        personne.setMobile(mobile);
    }
    private void traiterMail( String mail, Personne personne ) {
        try {
            validationMail( mail );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_PRENOM, e.getMessage() );
        }
        personne.setMail(mail);
    }
    
    private void validationNom( String nom ) throws FormValidationException {
        if ( nom != null ) {
            if ( nom.length() < 2 ) {
                throw new FormValidationException( "Le nom d'utilisateur doit contenir au moins 2 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un nom d'utilisateur." );
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
