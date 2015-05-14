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
import com.dl1.beans.Employe;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;



public final class CreationEmployeForm {
    
    private static final String CHAMP_LISTE_PERSONNES   = "listePersonnes";
    private static final String CHAMP_CHOIX_GROUPE      = "choixGroupeEquipe";
    private static final String CHAMP_ID                = "idPersonne";
    private static final String CHAMP_NOM               = "nomPersonne";
    private static final String CHAMP_PRENOM            = "prenomPersonne";
    private static final String CHAMP_NAISSANCE         = "dateNaissance";
    private static final String CHAMP_SS                = "securiteSociale";
    private static final String CHAMP_EXP               = "experience";
    private static final String CHAMP_FONCTION          = "fonction";
    private static final String CHAMP_NATIONALITE       = "nationalite";
    private static final String CHAMP_TITRESEJOUR       = "titreSejour";
    private static final String CHAMP_PERMISTRAVAIL     = "permisTravail";
    private static final String CHAMP_CODEACCES         = "codeAcces";
    
    private static final String EMPLOYE                 = "employe";
    private static final String BASE                    = "base";
    private static final String NO_BASE                 = "nobase";
    
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
    
    
    public Employe creerEmploye ( HttpServletRequest request ) {
        Personne personne;
    
        String idAncienPersonne = getValeurChamp( request, CHAMP_LISTE_PERSONNES );
        System.out.println("CreationEmployeForm --> id Personne : " + idAncienPersonne);
        
        Long id = null;
        try {
                id = Long.parseLong( idAncienPersonne );
            } catch ( NumberFormatException e ) {
                setErreur( CHAMP_LISTE_PERSONNES, "personne inconnu, merci d'utiliser le formulaire prévu à cet effet." );
                id = 0L;
            }
            /* Récupération de l'objet client correspondant dans la session */
        HttpSession session = request.getSession();
        personne = ( (Map<Long, Personne>) session.getAttribute( SESSION_PERSONNES ) ).get( id );
         
    
    
    Calendar calendar = Calendar.getInstance();
    Timestamp dt = new java.sql.Timestamp(calendar.getTime().getTime());

//    String idp = getValeurChamp( request, CHAMP_ID );
    System.out.println("passage 1");
    String nom = getValeurChamp( request, CHAMP_NOM );
    String prenom = getValeurChamp( request, CHAMP_PRENOM );
    String naissance = getValeurChamp( request, CHAMP_NAISSANCE);
    String ss = getValeurChamp( request, CHAMP_SS);
    String exp = getValeurChamp( request, CHAMP_EXP);
    String fct = getValeurChamp( request, CHAMP_FONCTION);
    String nat = getValeurChamp( request, CHAMP_NATIONALITE);
    String titrej = getValeurChamp( request, CHAMP_TITRESEJOUR);
    String permist = getValeurChamp( request, CHAMP_PERMISTRAVAIL);
    String acces = getValeurChamp( request, CHAMP_CODEACCES);
    System.out.println("passage 10");
    Employe employe = new Employe();
    
    String choixGroupeEquipe = getValeurChamp( request, CHAMP_CHOIX_GROUPE );
    if ( BASE.equals( choixGroupeEquipe ) ) {
       employe.setEquipeBase(BASE); 
    }else {
       employe.setEquipeBase(NO_BASE); 
    }
    
    
    traiterPersonne( employe );
    
    
    //Déclaration du SimpleDateFormat
    SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
if ( naissance != null ) {
//Conversion en java.util.Date
    try {
            java.util.Date date = null;
        
            date = sdf.parse(naissance);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            employe.setDateNaissance(sqlDate);
        } catch (ParseException ex) {
            Logger.getLogger(CreationEmployeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
} 
    
    
    employe.setIdPersonne(id);
    employe.setNom(nom);
    employe.setPrenom(prenom);
    employe.setSecuriteSociale(ss);
    employe.setExperience(exp);
    employe.setFonction(fct);
    employe.setNationalite(nat);
    employe.setTitreSejour(titrej);
    employe.setPermisTravail(permist);
    employe.setCodeAcces(acces);
    employe.setDateDebutE(dt);
    employe.setDateFinE(dt);
            
    return employe;
    
    }    
    
    private void traiterPersonne( Employe employe ) {
        if ( employe == null ) {
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
