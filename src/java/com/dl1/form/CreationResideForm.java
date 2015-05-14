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


public final class CreationResideForm {
    
    private static final String CHAMP_ID                 = "idReside";
    private static final String CHAMP_IDA                = "idAdresse";
    private static final String CHAMP_IDP                = "idPersonne";
        
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
    
    
    public Reside creerReside( HttpServletRequest request, Personne personne, Adresse adresse ) {
         /* Récupération de la date dans un DateTime Joda. */
        
        Calendar calendar = Calendar.getInstance();
        Timestamp dt = new java.sql.Timestamp(calendar.getTime().getTime());

        Reside reside = new Reside();
        
        reside.setDateDebutR(dt);
        reside.setDateFinR(dt);
        reside.setPersonne(personne);
        reside.setAdresse(adresse);
        
    /*   System.out.println("reside : " + reside.toString());
        if ( erreurs.isEmpty() ) {
           resultat = "Succès de la création dans le carnet.";
        } else {
            resultat = "Échec de la création dans le carnet.";
        
        }
    */    
    /*    Long num = reside.getIdReside();
        
        if ( num == null) {
            resultat = " ";
        } */
        
        return reside;

    }
    
 
     
}
