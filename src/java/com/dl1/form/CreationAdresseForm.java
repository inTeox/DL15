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
import java.sql.Timestamp;
import java.util.Calendar;


public final class CreationAdresseForm {
    
    private static final String CHAMP_ID                = "idAdresse";
    private static final String CHAMP_LIGNE1            = "ligne1Adresse";
    private static final String CHAMP_LIGNE2            = "ligne2Adresse";
    private static final String CHAMP_CP                = "cpAdresse";
    private static final String CHAMP_VILLE             = "villeAdresse";
    private static final String CHAMP_PAYS              = "paysAdresse";
        
    private static final String FORMAT_DATE             = "dd/MM/yyyy HH:mm:ss";
    
    private String resultat;

    private Map<String, String> erreurs                = new HashMap<String, String>(); 
    
    
    
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }
    
    public Adresse creerAdresse( HttpServletRequest request ) {
        String ligne1 = getValeurChamp( request, CHAMP_LIGNE1 );
        String ligne2 = getValeurChamp( request, CHAMP_LIGNE2 );
        String cp = getValeurChamp( request, CHAMP_CP );
        String ville = getValeurChamp( request, CHAMP_VILLE  );
        String pays = getValeurChamp( request, CHAMP_PAYS );
         /* Récupération de la date dans un DateTime Joda. */
   //     Timestamp dt = null;
   //     dt.getTime();
        Calendar calendar = Calendar.getInstance();
        Timestamp dt = new java.sql.Timestamp(calendar.getTime().getTime());

        
        
        Adresse adresse = new Adresse();
        
        adresse.setDateDebut(dt);
        adresse.setDateFin(dt);
        traiterLigne1( ligne1, adresse );
        traiterLigne2( ligne2, adresse );
        traiterCp( cp, adresse );
        traiterVille( ville, adresse );
        traiterPays( pays, adresse );
        
        
        return adresse;
        
    }
    
    private void traiterLigne1( String ligne1, Adresse adresse ) {
        try {
            validationLigne1( ligne1 );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_LIGNE1, e.getMessage() );
        }
        adresse.setLigne1(ligne1);
    }

    private void traiterLigne2( String ligne2, Adresse adresse ) {
        try {
            validationLigne2( ligne2 );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_LIGNE1, e.getMessage() );
        }
        adresse.setLigne2(ligne2);
    }
    
    private void traiterCp( String cp, Adresse adresse ) {
        try {
            validationCp( cp );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_CP, e.getMessage() );
        }
        adresse.setCp(cp);
    }
    
    private void traiterVille( String ville, Adresse adresse ) {
        try {
            validationVille( ville );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_LIGNE1, e.getMessage() );
        }
        adresse.setVille(ville);
    }
    
    
   private void traiterPays( String pays, Adresse adresse ) {
        try {
            validationPays( pays );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_LIGNE1, e.getMessage() );
        }
        adresse.setPays(pays);
    }
     
    
    
    
    
    
    
    
    

    
    
    
    
    
private void validationLigne1( String ligne1 ) throws FormValidationException {
        if ( ligne1 != null ) {
            if ( ligne1.length() < 2 ) {
                throw new FormValidationException( "L'adresse doit contenir au moins 2 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer une adresse valide." );
        }
    }    
    
    
private void validationLigne2( String ligne2 ) throws FormValidationException {
        if ( ligne2 != null ) {
            if ( ligne2.length() < 2 ) {
                throw new FormValidationException( "L'adresse doit contenir au moins 2 caractères." );
            }
        } 
}    
    
private void validationCp( String cp ) throws FormValidationException {
        if ( cp != null ) {
            if ( cp.length() < 5 || cp.length() > 5 ) {
                throw new FormValidationException( "L'adresse doit avoir un code postal de 5 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un code postal valide." );
        }
    }    
    
private void validationVille( String ville ) throws FormValidationException {
        if ( ville != null ) {
            if ( ville.length() < 2 ) {
                throw new FormValidationException( "La ville n'est pas valide." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer une ville valide." );
        }
    }    
    
private void validationPays( String pays ) throws FormValidationException {
        if ( pays != null ) {
            if ( pays.length() < 2 ) {
                throw new FormValidationException( "Le pays n'est pas valide." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un pays valide." );
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
