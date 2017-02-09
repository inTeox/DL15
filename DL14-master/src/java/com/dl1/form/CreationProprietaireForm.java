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
import com.dl1.beans.Proprietaire;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.servlet.http.HttpSession;



public final class CreationProprietaireForm {
    
    private static final String CHAMP_LISTE_PERSONNES   = "listePersonnes";
    private static final String CHAMP_ID                = "idPersonne";
    private static final String CHAMP_NOM               = "nomPersonne";
    private static final String CHAMP_PRENOM            = "prenomPersonne";
    private static final String CHAMP_FAX               = "fax";
    private static final String CHAMP_TELEPHONE         = "telephone";
    private static final String CHAMP_NOTE              = "note";
   
    private static final String PROPRIETAIRE            = "proprietaire";
    
    private static final String SESSION_PERSONNES        = "personnes";
   
    private static final String FORMAT_DATE             = "dd/MM/yyyy HH:mm:ss";
    
    public String resultat;

    private Map<String, String> erreurs                = new HashMap<String, String>(); 
  
    
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }
    
//<editor-fold defaultstate="collapsed" desc="récupération et controle de la saisie Proprietaire">    
    public Proprietaire creerProprietaire ( HttpServletRequest request ) {
        Personne personne;
    
        String idAncienPersonne = getValeurChamp( request, CHAMP_LISTE_PERSONNES );
        System.out.println("CreationProprietaireForm --> id Personne : " + idAncienPersonne);
        Long id = null;
        try {
                id = Long.parseLong( idAncienPersonne );
            } catch ( NumberFormatException e ) {
                setErreur( CHAMP_LISTE_PERSONNES, "personne inconnu, merci d'utiliser le formulaire prévu à cet effet." );
                id = 0L;
            }
            /* Récupération de l'objet client correspondant dans la session */
        System.out.println("CreationProprietaireForm --> PASSAGE1 " );
        HttpSession session = request.getSession();
        personne = ( (Map<Long, Personne>) session.getAttribute( SESSION_PERSONNES ) ).get( id );
         
    
    
        Calendar calendar = Calendar.getInstance();
        Timestamp dt = new java.sql.Timestamp(calendar.getTime().getTime());
        System.out.println("CreationProprietaireForm --> PASSAGE2 " );
        
        String idp = getValeurChamp( request, CHAMP_ID );
        String nom = getValeurChamp( request, CHAMP_NOM );
        String prenom = getValeurChamp( request, CHAMP_PRENOM );
        String fax = getValeurChamp( request, CHAMP_FAX);
        String telephone = getValeurChamp( request, CHAMP_TELEPHONE);
        String note = getValeurChamp( request, CHAMP_NOTE);
        
        System.out.println("CreationProprietaireForm --> PASSAGE3 " );
        
        Proprietaire proprietaire = new Proprietaire();
    
            
        traiterPersonne( proprietaire );
        System.out.println("CreationProprietaireForm --> PASSAGE4 " );
        
        proprietaire.setIdPersonne(id);
        proprietaire.setNom(nom);
        proprietaire.setPrenom(prenom);
        proprietaire.setFax(fax);
        proprietaire.setTelephone(telephone);
        proprietaire.setNote(note);
        proprietaire.setDateDebutPr(dt);
        proprietaire.setDateFinPr(dt);
        System.out.println("CreationProprietaireForm --> PASSAGE5 " ); 
    return proprietaire;
    
      
    }
//</editor-fold>    
    private void traiterPersonne( Proprietaire proprietaire ) {
        if ( proprietaire == null ) {
            setErreur( CHAMP_NOM, "Personne inconnu" );
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
