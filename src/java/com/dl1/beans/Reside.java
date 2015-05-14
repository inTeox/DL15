package com.dl1.beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reside implements Serializable {

    private Long       idReside;
    private Personne   personne;
    private Adresse    adresse;
    private Timestamp   dateDebutR;
    private Timestamp   dateFinR;

    public Long getIdReside() {
        return idReside;
    }

    public void setIdReside(Long idReside) {
        this.idReside = idReside;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Timestamp getDateDebutR() {
        return dateDebutR;
    }

    public void setDateDebutR(Timestamp dateDebutR) {
        this.dateDebutR = dateDebutR;
    }

    public Timestamp getDateFinR() {
        return dateFinR;
    }

    public void setDateFinR(Timestamp dateFinR) {
        this.dateFinR = dateFinR;
    }

    @Override
    public String toString() {
        return "Reside{" + "idReside=" + idReside + ", personne=" + personne + ", adresse=" + adresse + ", dateDebutR=" + dateDebutR + ", dateFinR=" + dateFinR + '}';
    }

    
    public String insertReside(Reside reside) {

        String msg = new String();
        new Timestamp( reside.getDateDebutR().getTime());
        new Timestamp( reside.getDateFinR().getTime() );
        
        
        System.out.println("Reside : entree dans insertReside");
        String SQL_INSERT
                = "INSERT INTO RESIDE (IDPERSONNE, IDADRESSE, "
                + " DCRESIDE, DFRESIDE) "
                + " VALUES ( " + reside.getPersonne().getIdPersonne() + ", "
                + "  " + reside.getAdresse().getIdAdresse() + " , "
                + "'" + reside.getDateDebutR()    + "', "
                + "'" + reside.getDateFinR()    + "' ) ";
         
        System.out.println("SQL_INSERT :" + SQL_INSERT);

        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
    
        String host = null;
        String uName = null;
        String uPass = null;
        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
        
        if ( fichierProperties == null ) {
        System.err.println("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        } else 
        {
        try {
          properties.load( fichierProperties );
          } catch (IOException ex) {
            Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
          }
          host = properties.getProperty( "url" );
          uName = properties.getProperty( "nomutilisateur" );
          uPass = properties.getProperty( "motdepasse" );    
          }

        try {
            Connection connexion = DriverManager.getConnection(host, uName, uPass);
            System.out.println("Personne : connexion faite");
            Statement stmt = connexion.createStatement();
             
            int result = stmt.executeUpdate(SQL_INSERT , Statement.RETURN_GENERATED_KEYS);
            System.out.println("resultat :" + result);
            ResultSet resultat = stmt.getGeneratedKeys();
            while ( resultat.next() ) { 
                   
                   reside.setIdReside(Long.valueOf(resultat.getInt(1)));
                   System.out.println("insertReside: --> ID : "  + reside.idReside );
             }
          //  resultat.getInt( 1 )
            
            stmt.close();
            try {

                connexion.close();
            } catch (SQLException ex) {
                System.err.println("connexion close:" + ex.getMessage());
                msg = "Echec de la création dans le carnet.";
                return msg;
            }
            System.out.println("done !");
        } catch (SQLException ex) {
            System.err.println("SQl error : " + ex.getErrorCode() + "/"
                    + ex.getMessage());
  
        }

                msg = "Succès de la création dans le carnet.";
                return msg;
    }

    
    
}