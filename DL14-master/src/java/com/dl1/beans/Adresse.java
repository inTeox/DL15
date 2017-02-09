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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")
public class Adresse implements Serializable {

    protected Long idAdresse;
    protected String ligne1;
    protected String ligne2;
    protected String cp;
    protected String ville;
    protected String pays;
    protected Timestamp dateDebut;
    protected Timestamp dateFin;

//<editor-fold defaultstate="collapsed" desc="getter et setter ">
    public Long getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(Long idAdresse) {
        this.idAdresse = idAdresse;
    }

    public String getLigne1() {
        return ligne1;
    }

    public void setLigne1(String ligne1) {
        this.ligne1 = ligne1;
    }

    public String getLigne2() {
        return ligne2;
    }

    public void setLigne2(String ligne2) {
        this.ligne2 = ligne2;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="création d'une adresse ">    
    public void insertAdresse(Adresse adresse) {

        new Timestamp( adresse.getDateDebut().getTime());
        new Timestamp( adresse.getDateFin().getTime() );
       // Calendar calendar = Calendar.getInstance();
       // Timestamp dt = new java.sql.Timestamp(calendar.getTime().getTime());

        
        
        
        System.out.println("Adresse : entree dans insertAdresse");
        String SQL_INSERT
                = "INSERT INTO ADRESSE (LIGNE1ADRESSE, LIGNE2ADRESSE, CPADRESSE, "
                + " VILLEADRESSE, PAYSADRESSE, DCADRESSE, DFADRESSE) "
                + " VALUES ( '" + adresse.getLigne1() + "', ";

        System.out.println("insertAdresse: --> getLigne2 : " + adresse.getLigne2());
        System.out.println("insertAdresse: --> SQL_INSERT : " + SQL_INSERT);
        
        if (adresse.ligne2 == null) {
            SQL_INSERT = SQL_INSERT + null + " ,";
            System.out.println("insertAdresse: --> passage null getLigne2 : " );
        } else {
            SQL_INSERT = SQL_INSERT + " '" + adresse.getLigne2() + "' , ";
            System.out.println("insertAdresse: --> passage pas null getLigne2 : " );
        }
        
        SQL_INSERT = SQL_INSERT + "'" + adresse.getCp()     + "', "
                   + "'" +  adresse.getVille()     + "', ";

        if (adresse.getPays().isEmpty()) {
            SQL_INSERT = SQL_INSERT + null + " ,";
        } else {
            SQL_INSERT = SQL_INSERT + " '" + adresse.getPays() + "' , ";
        }
        SQL_INSERT = SQL_INSERT + "'" +  adresse.getDateDebut()     + "', "
                   +    "'" +  adresse.getDateFin()     + "' ) ";
        
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
            System.out.println("Adresse : connexion faite");
            Statement stmt = connexion.createStatement();
            /*
             PreparedStatement stmt = connexion.prepareStatement(SQL_INSERT);
             stmt.setString(1, adresse.getLigne1());
             stmt.setString(2, adresse.getLigne2());
             stmt.setString(3, adresse.getCp());
             stmt.setString(4, adresse.getVille());
             stmt.setString(5, adresse.getPays());
             stmt.setObject(6, adresse.getDateDebut());
             stmt.setObject(7, adresse.getDateFin());
             */
            int result = stmt.executeUpdate(SQL_INSERT , Statement.RETURN_GENERATED_KEYS);
            System.out.println("resultat :" + result);
            ResultSet resultat = stmt.getGeneratedKeys();
            while ( resultat.next() ) { 
                   
                   adresse.setIdAdresse(Long.valueOf(resultat.getInt(1)));
                   System.out.println("insertAdresse: --> ID : "  + adresse.idAdresse );
             }
          //  resultat.getInt( 1 )
            
            stmt.close();
            try {

                connexion.close();
            } catch (SQLException ex) {
                System.err.println("connexion close:" + ex.getMessage());
                return;
            }
            System.out.println("done !");
        } catch (SQLException ex) {
            System.err.println("SQl error : " + ex.getErrorCode() + "/"
                    + ex.getMessage());
        }

    }


//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="lister une adresse "> 
public Adresse lister(Long id)   {
        String SQL_LIST     = "SELECT IDADRESSE, LIGNE1ADRESSE, "
                            + " LIGNE2ADRESSE, CPADRESSE, "
                            + " VILLEADRESSE, PAYSADRESSE, DCADRESSE, "
                            + " DFADRESSE "
                            + "  FROM ADRESSE WHERE IDADRESSE = " + id ;
                        

        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Adresse adresse = new Adresse();

        
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
            System.out.println("Adresse select : connexion faite :" + SQL_LIST);
            preparedStatement = connexion.prepareStatement( SQL_LIST );
             
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) { 
            //       personnes.add( map( resultSet ) );
                  adresse =  map( resultSet ) ;
                   
             }
          //  resultat.getInt( 1 )
            
            preparedStatement.close();
            try {

                connexion.close();
            } catch (SQLException ex) {
                System.err.println("connexion close:" + ex.getMessage());
                return null;
            }
            System.out.println("done !");
        } catch (SQLException ex) {
            System.err.println("SQl error : " + ex.getErrorCode() + "/"
                    + ex.getMessage());
        }

        
        return adresse;         
   }    

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="mapping resultSet pour Adresse">
private static Adresse map( ResultSet resultSet ) throws SQLException {
        Adresse adresse = new Adresse();
        adresse.setIdAdresse(resultSet.getLong( "IDADRESSE" ) );
        adresse.setLigne1( resultSet.getString( "LIGNE1ADRESSE" ) );
        adresse.setLigne2(resultSet.getString( "LIGNE2ADRESSE" ) );
        adresse.setCp(resultSet.getString( "CPADRESSE" ) );
        adresse. setVille(resultSet.getString( "VILLEADRESSE" ) );
        adresse.setPays(resultSet.getString( "PAYSADRESSE" ) );
        adresse.setDateDebut(resultSet.getTimestamp( "DCADRESSE" ) );
        adresse.setDateFin(resultSet.getTimestamp( "DFADRESSE" ) );
        return adresse;
    }
//</editor-fold>

}