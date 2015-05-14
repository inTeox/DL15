package com.dl1.beans;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Proprietaire extends Personne {

    private String    fax;
    private String    autretelephone;
    private String    note;
    private Timestamp dateDebutPr;
    private Timestamp dateFinPr;
    
//<editor-fold defaultstate="collapsed" desc="constructeur ">

    public Proprietaire() {
    }

    public Proprietaire(String fax, Timestamp dateDebutPr) {
        this.fax = fax;
        this.dateDebutPr = dateDebutPr;
    }

    public Proprietaire(String fax, Timestamp dateDebutPr, Long idPersonne, String nom, String prenom, String type, String telephone, Timestamp dateDebutP) {
        super(idPersonne, nom, prenom, type, telephone, dateDebutP);
        this.fax = fax;
        this.dateDebutPr = dateDebutPr;
    }

    public Proprietaire(String fax, Timestamp dateDebutPr, Long idPersonne, String nom, String prenom, String type, String telephone, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, dateDebutP, dateFinP);
        this.fax = fax;
        this.dateDebutPr = dateDebutPr;
    }

    public Proprietaire(String fax, Timestamp dateDebutPr, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, dateDebutP, dateFinP);
        this.fax = fax;
        this.dateDebutPr = dateDebutPr;
    }

    public Proprietaire(String fax, Timestamp dateDebutPr, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, String mail, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, mail, dateDebutP, dateFinP);
        this.fax = fax;
        this.dateDebutPr = dateDebutPr;
    }

    public Proprietaire(String fax, Timestamp dateDebutPr, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, String mail, String noteP, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, mail, noteP, dateDebutP, dateFinP);
        this.fax = fax;
        this.dateDebutPr = dateDebutPr;
    }

    public Proprietaire(String autretelephone, Timestamp dateDebutPr, Timestamp dateFinPr) {
        this.autretelephone = autretelephone;
        this.dateDebutPr = dateDebutPr;
        this.dateFinPr = dateFinPr;
    }

    public Proprietaire(String autretelephone, Timestamp dateDebutPr, Timestamp dateFinPr, Long idPersonne, String nom, String prenom, String type, String telephone, Timestamp dateDebutP) {
        super(idPersonne, nom, prenom, type, telephone, dateDebutP);
        this.autretelephone = autretelephone;
        this.dateDebutPr = dateDebutPr;
        this.dateFinPr = dateFinPr;
    }

    public Proprietaire(String autretelephone, Timestamp dateDebutPr, Timestamp dateFinPr, Long idPersonne, String nom, String prenom, String type, String telephone, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, dateDebutP, dateFinP);
        this.autretelephone = autretelephone;
        this.dateDebutPr = dateDebutPr;
        this.dateFinPr = dateFinPr;
    }

    public Proprietaire(String autretelephone, Timestamp dateDebutPr, Timestamp dateFinPr, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, dateDebutP, dateFinP);
        this.autretelephone = autretelephone;
        this.dateDebutPr = dateDebutPr;
        this.dateFinPr = dateFinPr;
    }

    public Proprietaire(String autretelephone, Timestamp dateDebutPr, Timestamp dateFinPr, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, String mail, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, mail, dateDebutP, dateFinP);
        this.autretelephone = autretelephone;
        this.dateDebutPr = dateDebutPr;
        this.dateFinPr = dateFinPr;
    }

    public Proprietaire(String autretelephone, Timestamp dateDebutPr, Timestamp dateFinPr, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, String mail, String noteP, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, mail, noteP, dateDebutP, dateFinP);
        this.autretelephone = autretelephone;
        this.dateDebutPr = dateDebutPr;
        this.dateFinPr = dateFinPr;
    }

    public Proprietaire(String fax, String autretelephone, String note, Timestamp dateDebutPr, Timestamp dateFinPr) {
        this.fax = fax;
        this.autretelephone = autretelephone;
        this.note = note;
        this.dateDebutPr = dateDebutPr;
        this.dateFinPr = dateFinPr;
    }

    public Proprietaire(String fax, String autretelephone, String note, Timestamp dateDebutPr, Timestamp dateFinPr, Long idPersonne, String nom, String prenom, String type, String telephone, Timestamp dateDebutP) {
        super(idPersonne, nom, prenom, type, telephone, dateDebutP);
        this.fax = fax;
        this.autretelephone = autretelephone;
        this.note = note;
        this.dateDebutPr = dateDebutPr;
        this.dateFinPr = dateFinPr;
    }

    public Proprietaire(String fax, String autretelephone, String note, Timestamp dateDebutPr, Timestamp dateFinPr, Long idPersonne, String nom, String prenom, String type, String telephone, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, dateDebutP, dateFinP);
        this.fax = fax;
        this.autretelephone = autretelephone;
        this.note = note;
        this.dateDebutPr = dateDebutPr;
        this.dateFinPr = dateFinPr;
    }

    public Proprietaire(String fax, String autretelephone, String note, Timestamp dateDebutPr, Timestamp dateFinPr, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, dateDebutP, dateFinP);
        this.fax = fax;
        this.autretelephone = autretelephone;
        this.note = note;
        this.dateDebutPr = dateDebutPr;
        this.dateFinPr = dateFinPr;
    }

    public Proprietaire(String fax, String autretelephone, String note, Timestamp dateDebutPr, Timestamp dateFinPr, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, String mail, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, mail, dateDebutP, dateFinP);
        this.fax = fax;
        this.autretelephone = autretelephone;
        this.note = note;
        this.dateDebutPr = dateDebutPr;
        this.dateFinPr = dateFinPr;
    }

    public Proprietaire(String fax, String autretelephone, String note, Timestamp dateDebutPr, Timestamp dateFinPr, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, String mail, String noteP, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, mail, noteP, dateDebutP, dateFinP);
        this.fax = fax;
        this.autretelephone = autretelephone;
        this.note = note;
        this.dateDebutPr = dateDebutPr;
        this.dateFinPr = dateFinPr;
    }
    //</editor-fold>
    
    
//<editor-fold defaultstate="collapsed" desc="getter et setter">  

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAutretelephone() {
        return autretelephone;
    }

    public void setAutretelephone(String autretelephone) {
        this.autretelephone = autretelephone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getDateDebutPr() {
        return dateDebutPr;
    }

    public void setDateDebutPr(Timestamp dateDebutPr) {
        this.dateDebutPr = dateDebutPr;
    }

    public Timestamp getDateFinPr() {
        return dateFinPr;
    }

    public void setDateFinPr(Timestamp dateFinPr) {
        this.dateFinPr = dateFinPr;
    }
    
//</editor-fold>      

    @Override
    public String toString() {
        return super.toString() + "Proprietaire{" + "fax=" + fax + ", autretelephone=" 
                + autretelephone + ", note=" + note + ", dateDebutPr=" + dateDebutPr 
                + ", dateFinPr=" + dateFinPr + '}';
    }
  
//<editor-fold defaultstate="collapsed" desc="insertProprietaire">
public static String insertProprietaire(Proprietaire proprietaire, Personne personne) {

    String msg = null;
    new Timestamp( proprietaire.getDateDebutPr().getTime());
    new Timestamp( proprietaire.getDateFinPr().getTime() );

    System.out.println("Proprietaire : entree dans insertProprietaire");    
        
    String sql_update = ( "UPDATE PERSONNE  set FAXPROPRIETAIRE = ? " + " , "
                        + " AUTRETELPROPRIETAIRE = ? "  + " , "
                        + " NOTEPROPRIETAIRE = ? " + " , "
                        + " DCPROPRIETAIRE = ? " + " , "
                        + " DFPROPRIETAIRE = ? " 
                        + " WHERE  IDPERSONNE = ? " ) ; 
     
    System.out.println("insertProprietaire: --> SQL_UPDATE : " + sql_update);
        
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
            System.out.println("Proprietaire : connexion faite");
            PreparedStatement stmt = connexion.prepareStatement(sql_update);
            
            stmt.setString(1, proprietaire.getFax());
            stmt.setString(2, proprietaire.getAutretelephone());
            stmt.setString(3, proprietaire.getNote());
            stmt.setTimestamp(4,proprietaire.getDateDebutPr());
            stmt.setTimestamp(5,proprietaire.getDateFinPr());
            stmt.setLong(6,proprietaire.idPersonne);
    
            
            stmt.executeUpdate();
            
            stmt.close();
            try {

                connexion.close();
            } catch (SQLException ex) {
                System.err.println("connexion close:" + ex.getMessage());
                msg = "Echec de la mise  à jour dans le carnet.";
                return msg;
            }
            System.out.println("done !");
        } catch (SQLException ex) {
            System.err.println("SQl error : " + ex.getErrorCode() + "/"
                    + ex.getMessage());
        }

            msg = "Succès de la création du propriétaire.";
            
    
    return msg;
}    
//</editor-fold>    
    
    
}