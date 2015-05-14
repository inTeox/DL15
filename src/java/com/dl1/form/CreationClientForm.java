/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dl1.form;

import com.dl1.beans.Client;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dl1.beans.Personne;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.servlet.http.HttpSession;



public final class CreationClientForm {
    
    private static final String CHAMP_LISTE_PERSONNES   = "listePersonnes";
    private static final String CHAMP_ID                = "idPersonne";
    private static final String CHAMP_CHOIX_GROUPE      = "choixGroupeClient";
    private static final String CHAMP_NOM               = "nomPersonne";
    private static final String CHAMP_PRENOM            = "prenomPersonne";
    private static final String CHAMP_FAX               = "fax";
    private static final String CHAMP_TELEPHONE         = "telephone";
    private static final String CHAMP_NOTE              = "note";
   
    private static final String CLIENT            = "client";
    private static final String PROSPECT            = "prospect";
    
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
    
//<editor-fold defaultstate="collapsed" desc="récupération et controle de la saisie Client">    
    public Client creerClient ( HttpServletRequest request ) {
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
        
        Client client = new Client();
    
        String choixGroupeClient = getValeurChamp( request, CHAMP_CHOIX_GROUPE );
        if ( CLIENT.equals( choixGroupeClient ) ) {
            client.setTypeClient(CLIENT); 
        }else {
           client.setTypeClient(PROSPECT); 
       }
        
        
            
        traiterPersonne( client );
        System.out.println("CreationClientForm --> PASSAGE4 " );
        
        client.setIdPersonne(id);
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setFax(fax);
        client.setTelephone(telephone);
        client.setNote(note);
        client.setDateDebutPr(dt);
        client.setDateFinPr(dt);
        System.out.println("CreationClientForm --> PASSAGE5 " ); 
    return client;
    
      
    }
//</editor-fold>    
    private void traiterPersonne( Client client ) {
        if ( client == null ) {
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
