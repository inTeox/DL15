package com.dl1.beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Carnet implements Serializable {

//<editor-fold defaultstate="collapsed" desc="variables">
    protected Long          idPersonne;
    protected Long          idAdresse;
//    protected Personne        personne;
    protected String        nom;
    protected String        prenom;
    protected String        type;
    protected String        telephone;
    protected String        mobile;
    protected String        mail;
    protected String        noteP;
    protected Timestamp     dateDebutP;
    protected Timestamp     dateFinP;
//    protected Employe         employe;
    protected Date          dateNaissance;
    protected String        securiteSociale;
    protected String        experience;
    protected String        fonction;
    protected String        nationalite;
    protected String        titreSejour;
    protected String        permisTravail;
    protected String        codeAcces;
    protected String        equipeBase;
    protected Timestamp     dateDebutE;
    protected Timestamp     dateFinE;
    protected String        typeclient;
    protected String        fax;
    protected String        autretelephone;
    protected String        note;
    protected Timestamp     dateDebutPr;
    protected Timestamp     dateFinPr;
    protected Adresse       adresse;
//</editor-fold>    

    public Carnet() {
    }

   

//<editor-fold defaultstate="collapsed" desc="getter et setter">
     public String getTypeclient() {
        return typeclient;
    }
    public void setTypeclient(String typeclient) {
        this.typeclient = typeclient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNoteP() {
        return noteP;
    }

    public void setNoteP(String noteP) {
        this.noteP = noteP;
    }

    public Timestamp getDateDebutP() {
        return dateDebutP;
    }

    public void setDateDebutP(Timestamp dateDebutP) {
        this.dateDebutP = dateDebutP;
    }

    public Timestamp getDateFinP() {
        return dateFinP;
    }

    public void setDateFinP(Timestamp dateFinP) {
        this.dateFinP = dateFinP;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSecuriteSociale() {
        return securiteSociale;
    }

    public void setSecuriteSociale(String securiteSociale) {
        this.securiteSociale = securiteSociale;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getTitreSejour() {
        return titreSejour;
    }

    public void setTitreSejour(String titreSejour) {
        this.titreSejour = titreSejour;
    }

    public String getPermisTravail() {
        return permisTravail;
    }

    public void setPermisTravail(String permisTravail) {
        this.permisTravail = permisTravail;
    }

    public String getCodeAcces() {
        return codeAcces;
    }

    public void setCodeAcces(String codeAcces) {
        this.codeAcces = codeAcces;
    }

    public Timestamp getDateDebutE() {
        return dateDebutE;
    }

    public void setDateDebutE(Timestamp dateDebutE) {
        this.dateDebutE = dateDebutE;
    }

    public Timestamp getDateFinE() {
        return dateFinE;
    }

    public void setDateFinE(Timestamp dateFinE) {
        this.dateFinE = dateFinE;
    }

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
    
    
    
    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Long getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(Long idAdresse) {
        this.idAdresse = idAdresse;
    }


    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getEquipeBase() {
        return equipeBase;
    }

    public void setEquipeBase(String equipeBase) {
        this.equipeBase = equipeBase;
    }
    
//</editor-fold>

@Override
    public String toString() {
        return "Carnet{" + "idPersonne=" + idPersonne + ", idAdresse=" 
                + idAdresse + ", nom=" + nom + ", prenom=" + prenom 
                + ", type=" + type + ", telephone=" + telephone + ", mobile=" 
                + mobile + ", mail=" + mail + ", noteP=" + noteP 
                + ", dateDebutP=" + dateDebutP + ", dateFinP=" + dateFinP 
                + ", dateNaissance=" + dateNaissance + ", securiteSociale=" 
                + securiteSociale + ", experience=" + experience 
                + ", fonction=" + fonction + ", nationalite=" + nationalite 
                + ", titreSejour=" + titreSejour + ", permisTravail=" 
                + permisTravail + ", codeAcces=" + codeAcces 
                + ", equipeBase=" + equipeBase + ", dateDebutE=" 
                + dateDebutE + ", dateFinE=" + dateFinE + ", typeclient=" 
                + typeclient + ", fax=" + fax + ", autretelephone=" 
                + autretelephone + ", note=" + note + ", dateDebutPr=" 
                + dateDebutPr + ", dateFinPr=" + dateFinPr + ", adresse=" 
                + adresse + '}';
    }    
 
    
//<editor-fold defaultstate="collapsed" desc="section Lister le carnet d'adresse">            
    public Map<Long, Carnet> listerCarnet()   {
        String SQL_SELECT   = "SELECT IDPERSONNE, NOMPERSONNE, "
                            + " PRENOMPERSONNE, TYPEPERSONNE, "
                            + " TELPERSONNE, MOBPERSONNE, MAILPERSONNE, "
                            + " NOTEPERSONNE, DCPERSONNE, DFPERSONNE , "
                            + " DATENAISSEMPLOYE, ANEXPERIENCE, FONCTIONEMPLOYE, NUMEROSSEMPLOYE, " 
                            + " NATIONALITEEMPLOYE, TITRESEJOUREMPLOYE, PERMISTRAVAILEMPLOYE,  "
                            + " CODEACCESEMPLOYE, EQUIPEBASEEMPLOYE, DCEMPLOYE, DFEMPLOYE, TYPECLIENT, FAXCLIENT , "
                            + " AUTRETELCLIENT, NOTECLIENT, DCCLIENT, DFCLIENT  "
                            + "  FROM PERSONNE ORDER BY IDPERSONNE";
        
        
        
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<Long, Carnet> carnets = new HashMap<Long, Carnet>();

        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
                 
        if ( fichierProperties == null ) {
            System.err.println("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        } else
          {
            try {
                properties.load( fichierProperties );
            } catch (IOException ex) {
                Logger.getLogger(Carnet.class.getName()).log(Level.SEVERE, null, ex);
            }
            host = properties.getProperty( "url" );
            uName = properties.getProperty( "nomutilisateur" );
            uPass = properties.getProperty( "motdepasse" );
              
        }
         
        try {
            Connection connexion = DriverManager.getConnection(host, uName, uPass);
            System.out.println("Personne select : connexion faite");
            preparedStatement = connexion.prepareStatement( SQL_SELECT );
             
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) { 
            //       personnes.add( map( resultSet ) );
                   Long idPers = resultSet.getLong( "IDPERSONNE" );
                   Carnet ligneCarnet = new Carnet();
                   ligneCarnet = getInfo (resultSet);
            //       System.out.println("listerCarnet FIN1");
                   carnets.put( resultSet.getLong( "IDPERSONNE" ), map( resultSet, ligneCarnet ) );
            //       System.out.println("listerCarnet FIN2");
             }
          //  resultat.getInt( 1 )
            
            preparedStatement.close();
            try {

                connexion.close();
            } catch (SQLException ex) {
                System.err.println("connexion close:" + ex.getMessage());
                return null;
            }
            System.out.println("Done1 !");
        } catch (SQLException ex) {
            System.err.println("SQl error : " + ex.getErrorCode() + "/"
                    + ex.getMessage());
        }

        
        return carnets;         
   }        
//</editor-fold>    

   
    

//<editor-fold defaultstate="collapsed" desc="section Récuperer les informations reside et Adresse">

    private static Carnet getInfo( ResultSet resultSet ) throws SQLException {
// 1) on créee une ligne carnet        
        Carnet ligneCarnet = new Carnet();
  
// 1) Lecture table Reside        
        Long  persoIdAdresse = getInfoReside (resultSet);
// 2) recupération de l'adresse        
        
        ligneCarnet = getPersonneAdresse(persoIdAdresse, ligneCarnet);
  //      ligneCarnet.setIdAdresse(persoAdresse.getIdAdresse());
        System.out.println("getInfo FIN");
       return ligneCarnet;
                
    }
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="section recuperation de l'identifiant adresse dans la table reside">    
private static Long getInfoReside(ResultSet resultSet) throws SQLException { 
        Long persoIdAdresse = null;
           
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        String SQL_SELECT = "SELECT IDRESIDE,  IDPERSONNE, IDADRESSE, DCRESIDE, "
                          + " DFRESIDE "
                          + " FROM RESIDE  WHERE IDPERSONNE = "  
                          + resultSet.getLong( "IDPERSONNE" ); 
        
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
   
        
        Connection connexion = null;  
        
        try {
            connexion = DriverManager.getConnection(host,uName,uPass);
        //     Statement stmt = connexion.createStatement();
           
        } catch (SQLException ex) {
            System.err.println("Oops:Connection:" + ex.getErrorCode() + ":" + ex.getMessage());
             
        }
   
        try {
            Statement stmt = connexion.createStatement();
            System.out.println("query:"+ SQL_SELECT);    
                        
            ResultSet rs = stmt.executeQuery(SQL_SELECT);
            
            while (rs.next()) {
//                cb.addItem(rs.getString("Pays"));
                persoIdAdresse = rs.getLong("IDADRESSE");
                
                        
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
            return persoIdAdresse;
        }

        try {
            connexion.close();
        } catch (SQLException ex) {
            System.err.println("Oops:Close:" + ex.getErrorCode() + ":" + ex.getMessage());
            return persoIdAdresse;
        }

        System.out.println("Done2!");
        return persoIdAdresse;
        
    
}   
//</editor-fold>    
    
    
//<editor-fold defaultstate="collapsed" desc="section recuperation de l'adresse">
    
private static Carnet getPersonneAdresse(Long id, Carnet ligneCarnet) { 
        
           
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        String SQL_SELECT = "SELECT IDADRESSE,  LIGNE1ADRESSE, LIGNE2ADRESSE, CPADRESSE, "
                          + " VILLEADRESSE, PAYSADRESSE, DCADRESSE, DFADRESSE "
                          + " FROM ADRESSE  WHERE IDADRESSE = "  + id; 
        
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
                Logger.getLogger(Carnet.class.getName()).log(Level.SEVERE, null, ex);
            }
            host = properties.getProperty( "url" );
            uName = properties.getProperty( "nomutilisateur" );
            uPass = properties.getProperty( "motdepasse" );
              
        }
   
        
        Connection connexion = null;  
        
        try {
            connexion = DriverManager.getConnection(host,uName,uPass);
        //     Statement stmt = connexion.createStatement();
           
        } catch (SQLException ex) {
            System.err.println("Oops:Connection:" + ex.getErrorCode() + ":" + ex.getMessage());
             
        }
   
        try {
            Statement stmt = connexion.createStatement();
            System.out.println("query:"+ SQL_SELECT);    
                        
            ResultSet rs = stmt.executeQuery(SQL_SELECT);
            
            while (rs.next()) {
//                cb.addItem(rs.getString("Pays"));
                
                ligneCarnet = mapAdresse(rs , ligneCarnet);
                        
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
            return ligneCarnet;
        }

        try {
            connexion.close();
        } catch (SQLException ex) {
            System.err.println("Oops:Close:" + ex.getErrorCode() + ":" + ex.getMessage());
            return ligneCarnet;
        }

        System.out.println("Done3!");
        return ligneCarnet;
        
    
}   
//</editor-fold>    
    
    
    
/*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des personness (un ResultSet) et
     * un bean Personne.
     */
    private static Carnet map( ResultSet resultSet, Carnet carnet) throws SQLException {
       
        System.out.println("map 1 -->");
        
        carnet.setIdPersonne(resultSet.getLong( "IDPERSONNE" ));
        carnet.setIdPersonne( resultSet.getLong( "IDPERSONNE" ) );
        carnet.setNom( resultSet.getString( "NOMPERSONNE" ) );
        carnet.setPrenom( resultSet.getString( "PRENOMPERSONNE" ) );
        carnet.setType( resultSet.getString( "TYPEPERSONNE" ) );
        carnet.setTelephone( resultSet.getString( "TELPERSONNE" ) );
        carnet.setMobile( resultSet.getString( "MOBPERSONNE" ) );
        
        carnet.setMail( resultSet.getString( "MAILPERSONNE" ) );
        carnet.setNoteP( resultSet.getString( "NOTEPERSONNE" ) );
        carnet.setDateDebutP(resultSet.getTimestamp( "DCPERSONNE" ) );

        carnet.setDateFinP(resultSet.getTimestamp( "DFPERSONNE" ) );
        System.out.println("map 4 -->" + carnet.toString());
        carnet.setDateNaissance(resultSet.getDate("DATENAISSEMPLOYE" ) );
        carnet.setSecuriteSociale(resultSet.getString("NUMEROSSEMPLOYE" ) );
        carnet.setExperience(resultSet.getString("ANEXPERIENCE" ) );
        carnet.setFonction(resultSet.getString("FONCTIONEMPLOYE" ) );
        carnet.setNationalite(resultSet.getString("NATIONALITEEMPLOYE" ) );
        carnet.setTitreSejour(resultSet.getString("TITRESEJOUREMPLOYE" ) );
        carnet.setPermisTravail(resultSet.getString("PERMISTRAVAILEMPLOYE" ) );
        carnet.setCodeAcces(resultSet.getString("CODEACCESEMPLOYE" ) );
        carnet.setEquipeBase(resultSet.getString("EQUIPEBASEEMPLOYE" ) );
        carnet.setDateDebutE(resultSet.getTimestamp("DCEMPLOYE" ) );
        carnet.setDateFinE(resultSet.getTimestamp("DFEMPLOYE" ) );
        carnet.setTypeclient(resultSet.getString("TYPECLIENT" ));
        carnet.setFax(resultSet.getString("FAXCLIENT" ) );
        carnet.setAutretelephone(resultSet.getString("AUTRETELCLIENT" ) );
        carnet.setNote(resultSet.getString("NOTECLIENT" ) );
        carnet.setDateDebutPr(resultSet.getTimestamp("DCCLIENT" ) );
        carnet.setDateFinPr(resultSet.getTimestamp("DFCLIENT" ) );
    
        
        return carnet;
    }
    private static Carnet mapAdresse( ResultSet resultSet, Carnet ligneCarnet ) throws SQLException {
       
        Adresse adresse = new Adresse();
        System.out.println("mapAdresse" );
        Long idadresse = resultSet.getLong( "IDADRESSE" );
        System.out.println("idadresse" + idadresse);
        
    //    ligneCarnet.adresse.setIdAdresse(idadresse);
        ligneCarnet.idAdresse = (Long) idadresse;
        System.out.println("mapAdresse 2" );
        System.out.println("ligneCarnet.idAdresse =" + ligneCarnet.idAdresse );
        
        String ligne1 = resultSet.getString( "LIGNE1ADRESSE" );
        
        System.out.println("ligne1=" + ligne1 );
        
        adresse.ligne1 = ligne1;
                
        adresse.setLigne2(resultSet.getString( "LIGNE2ADRESSE" ) );
        adresse.setCp(resultSet.getString( "CPADRESSE" ) );
        adresse.setVille(resultSet.getString( "VILLEADRESSE" ) );
        adresse.setPays(resultSet.getString( "PAYSADRESSE" ) );
        adresse.setDateDebut(resultSet.getTimestamp( "DCADRESSE" ) );
        adresse.setDateFin(resultSet.getTimestamp( "DFADRESSE" ) );
        System.out.println("mapAdresse fin" );
        
        ligneCarnet.setAdresse(adresse);
        return ligneCarnet;
    }
    
    
    
    
    
    
    
}