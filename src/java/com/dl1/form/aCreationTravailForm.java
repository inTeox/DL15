/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dl1.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dl1.beans.Employe;
import com.dl1.beans.Travail;
import com.dl1.beans.Chantier;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;



public final class aCreationTravailForm {
    
    private static final String CHAMP_ID                    = "idChantier";
    private static final String CHAMP_NOM                   = "nomChantier";
    private static final String CHAMP_FROM                  = "from";
    private static final String CHAMP_LISTE_CHANTIERS       = "listeChantiers";
    private static final String CHAMP_LISTE_EMPLOYES        = "listeEmployes";
    
    private static final String CHAMP_LUAM                  = "Luam";
    private static final String CHAMP_LUPM                  = "Lupm";
    private static final String CHAMP_MAAM                  = "Maam";
    private static final String CHAMP_MAPM                  = "Mapm";
    private static final String CHAMP_MEAM                  = "Meam";
    private static final String CHAMP_MEPM                  = "Mepm";
    private static final String CHAMP_JEAM                  = "Jeam";
    private static final String CHAMP_JEPM                  = "Jepm";
    private static final String CHAMP_VEAM                  = "Veam";
    private static final String CHAMP_VEPM                  = "Vepm";
    
    private static final String TOUS_LES_EMPLOYES           = "base";
    
    private static final String CHANTIER                     = "chantier";
    private static final String EMPLOYE                      = "employe";
    private static final String SESSION_CHANTIERS            = "chantiers";
    private static final String SESSION_EMPLOYES             = "employes";
    
    
    private static final String FORMAT_DATE                 = "dd/MM/yyyy HH:mm:ss";
    
    private String resultat;

    private Map<String, String> erreurs                = new HashMap<String, String>(); 
  
    
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }
    
    public ArrayList<Travail> creerTravail( HttpServletRequest request ) {
        /* seletion du chantier                                           */
        Chantier chantier;
        
        String idAncienChantier = getValeurChamp( request, CHAMP_LISTE_CHANTIERS );
//        System.out.println("aCreationTravailForm --> id Chantier : " + idAncienChantier);
        Long id = null;
        try {
                id = Long.parseLong( idAncienChantier );
            } catch ( NumberFormatException e ) {
                setErreur( CHAMP_LISTE_CHANTIERS, "chantier inconnu, merci d'utiliser le formulaire prévu à cet effet." );
                id = 0L;
            }
            /* Récupération de l'objet chantier correspondant dans la session */
//        System.out.println("aCreationTravailForm --> PASSAGE1 " );
        HttpSession session = request.getSession();
        chantier = ( (Map<Long, Chantier>) session.getAttribute( SESSION_CHANTIERS ) ).get( id );
//        System.out.println("aCreationTravailForm --> PASSAGE2 " + chantier );
    
         /* seletion de l'employe ou de la liste des employés             */
        
        Employe employe = new Employe();
    
        HashMap<Long, Employe> mesEmployes = new HashMap<Long, Employe>();
       
//        employes =  (HashMap<Long, Employe>) employe.listerB(TOUS_LES_EMPLOYES);
       
        String idAncienEmploye = getValeurChamp( request, CHAMP_LISTE_EMPLOYES );
        System.out.println("CreationTravailForm --> id Employe : " + idAncienEmploye);
        
        if  (!(idAncienEmploye == null) && (idAncienEmploye.equals("ALL"))) {
            System.out.println("CreationTravailForm --> ALL");
            
            mesEmployes =  (HashMap<Long, Employe>) employe.listerB(TOUS_LES_EMPLOYES);
            System.out.println("CreationTravailForm --> LISTE employés :" + mesEmployes);
            
        } else {
            Long ide = null;
            System.out.println("CreationTravailForm --> pasALL");
            try {
                ide = Long.parseLong( idAncienEmploye );
                employe = ( (Map<Long, Employe>) session.getAttribute( SESSION_EMPLOYES ) ).get( ide );
                mesEmployes.put(ide, employe);
            
            } catch ( NumberFormatException e ) {
                setErreur( CHAMP_LISTE_EMPLOYES, "employe inconnu, merci d'utiliser le formulaire prévu à cet effet." );
                id = 0L;
            }           
        }
        System.out.println("CreationTravailForm  SIZE employes-->" + mesEmployes.size());
//        System.out.println("CreationTravaillForm --> sorti de la partie identifiant employe");
        Calendar calendar = Calendar.getInstance();
        Timestamp dt = new java.sql.Timestamp(calendar.getTime().getTime());
        
        String dfrom = getValeurChamp( request, CHAMP_FROM );
//        System.out.println("aCreationTravailForm passer 0 dfrom-->  " + dfrom);
        if (dfrom == null) {
            dfrom = "01-01-2015";
        }
        
//        String dto   = getValeurChamp( request, CHAMP_TO );  
        
        ArrayList<Travail> semaine = new ArrayList<Travail>();
           
        Travail travail = new Travail(); 
 
         
        travail.setChantier(chantier);
        
        travail.setDcTravail(dt);
        travail.setDfTravail(dt);
        int j = 0;
if  (!(idAncienEmploye == null)) {        
   for ( Long key : mesEmployes.keySet()) {
        travail.setEmploye(mesEmployes.get(key));
        String dfrom2 = dfrom; 
//        for ( int i = 0; i <= 4; i++) {                
            try {
                travail.setChantier(chantier);
                travail.setEmploye(mesEmployes.get(key));
                travail.setDcTravail(dt);
                travail.setDfTravail(dt);
    //  0            
                Date sqlDfrom = traiterDfrom( dfrom2 );
                travail.setdatePlanning(sqlDfrom);
                semaine.add(j, travail);  
                
                SimpleDateFormat sdf = new SimpleDateFormat ("dd-MM-yyyy");
                Calendar c = Calendar.getInstance();
                c.setTime(sdf.parse(dfrom2));
                c.add (Calendar.DATE, 1);
                dfrom2 = sdf.format(c.getTime());
                
                System.out.println("boucle0 SEMAINE : " + semaine.get(j) );
                j++;
    //  1            
                sqlDfrom = traiterDfrom( dfrom2 );
                travail.setdatePlanning(sqlDfrom);
                semaine.add(j, travail);  
                
                c.setTime(sdf.parse(dfrom2));
                c.add (Calendar.DATE, 1);
                dfrom2 = sdf.format(c.getTime());
                
                System.out.println("boucle1 SEMAINE : " + semaine.get(j) );
                j++; 
    //  2            
                sqlDfrom = traiterDfrom( dfrom2 );
                travail.setdatePlanning(sqlDfrom);
                semaine.add(j, travail);  
                
                c.setTime(sdf.parse(dfrom2));
                c.add (Calendar.DATE, 1);
                dfrom2 = sdf.format(c.getTime());
                
                System.out.println("boucle2 SEMAINE : " + semaine.get(j) );
                j++;     
    //  3            
                sqlDfrom = traiterDfrom( dfrom2 );
                travail.setdatePlanning(sqlDfrom);
                semaine.add(j, travail);  
                
                c.setTime(sdf.parse(dfrom2));
                c.add (Calendar.DATE, 1);
                dfrom2 = sdf.format(c.getTime());
                
                System.out.println("boucle3 SEMAINE : " + semaine.get(j) );
                j++;     
    //  4            
                sqlDfrom = traiterDfrom( dfrom2 );
                travail.setdatePlanning(sqlDfrom);
                semaine.add(j, travail);  
                
                c.setTime(sdf.parse(dfrom2));
                c.add (Calendar.DATE, 1);
                dfrom2 = sdf.format(c.getTime());
                
                System.out.println("boucle4 SEMAINE : " + semaine.get(j) );
                j++;     
                    
            } catch (ParseException ex) {
                Logger.getLogger(aCreationTravailForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
//        }
            
        
//        }
                
 }    
        System.out.println("CreationTravailForm --> longueur semaine :" + semaine.size());
        
        Iterator it = semaine.iterator();
        while(it.hasNext()) {
            System.out.println("=================================================");
            System.out.println("semaine :" + it.next().toString());
        } 
}        
        
        
        return semaine;
    
    }    
    
private Date traiterDfrom( String dfrom3 ) {
        //Déclaration du SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat ("dd-MM-yyyy");
        if ( dfrom3 != null ) {
        //Conversion en java.util.Date
        try {
            java.util.Date date = null;
        
            date = sdf.parse(dfrom3);
            java.sql.Date sqlDfrom = new java.sql.Date(date.getTime());
    //        travail.setdatePlanning(sqlDfrom);
            return sqlDfrom;
        } catch (ParseException ex) {
            Logger.getLogger(aCreationTravailForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
        return null;
    }        
private void traiterDto( String dto ) {
        //Déclaration du SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat ("dd-MM-yyyy");
        if ( dto != null ) {
        //Conversion en java.util.Date
        try {
            java.util.Date date = null;
        
            date = sdf.parse(dto);
            java.sql.Date sqlDto = new java.sql.Date(date.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(CreationContratForm.class.getName()).log(Level.SEVERE, null, ex);
        }
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
