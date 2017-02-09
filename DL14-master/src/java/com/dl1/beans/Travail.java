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

@SuppressWarnings("serial")
public class Travail implements Serializable, Comparable {

//<editor-fold defaultstate="collapsed" desc="variables ">    
    protected Long        idTravail;
    protected Employe     employe;
    protected Chantier    chantier;
    protected Date        datePlanning;
    protected boolean     presenceAM;
    protected boolean     presencePM;
    protected Timestamp   dcTravail;
    protected Timestamp   dfTravail;
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="getter et setter ">    
public Long getIdTravail() {
        return idTravail;
    }

    public void setIdTravail(Long idTravail) {
        this.idTravail = idTravail;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Chantier getChantier() {
        return chantier;
    }

    public void setChantier(Chantier chantier) {
        this.chantier = chantier;
    }

    public Date getdatePlanning() {
        return datePlanning;
    }

    public void setdatePlanning(Date datePlanning) {
        this.datePlanning = datePlanning;
    }

    public boolean isPresenceAM() {
        return presenceAM;
    }

    public void setPresenceAM(boolean presenceAM) {
        this.presenceAM = presenceAM;
    }

    public boolean isPresencePM() {
        return presencePM;
    }

    public void setPresencePM(boolean presencePM) {
        this.presencePM = presencePM;
    }

    public Timestamp getDcTravail() {
        return dcTravail;
    }

    public void setDcTravail(Timestamp dcTravail) {
        this.dcTravail = dcTravail;
    }

    public Timestamp getDfTravail() {
        return dfTravail;
    }

    public void setDfTravail(Timestamp dfTravail) {
        this.dfTravail = dfTravail;
    }

      
//</editor-fold>    
    
//<editor-fold defaultstate="collapsed" desc="constructeur ">    

    public Travail() {
    }

    public Travail(Long idTravail, Employe employe, Chantier chantier, Date dateDebPrevu, boolean presenceAM, boolean presencePM, Timestamp dcTravail, Timestamp dfTravail) {
        this.idTravail = idTravail;
        this.employe = employe;
        this.chantier = chantier;
        this.datePlanning = datePlanning;
        this.presenceAM = presenceAM;
        this.presencePM = presencePM;
        this.dcTravail = dcTravail;
        this.dfTravail = dfTravail;
    }

    
    
    
//</editor-fold>    
    
//<editor-fold defaultstate="collapsed" desc="methode override ">    
@Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 @Override
    public String toString() {
        return "Travail{" + "idTravail=" + idTravail + 
                ", employe=" + employe + ", chantier=" + chantier + 
                ", datePlanning=" + datePlanning + ", presenceAM=" + 
                presenceAM + ", presencePM=" + presencePM + ", dcTravail=" + 
                dcTravail + ", dfTravail=" + dfTravail + '}';
    }    
    
    
    
//</editor-fold> 

//<editor-fold defaultstate="collapsed" desc="insertTravail">    
public void insertTravail(Travail travail, Date d) {

        new Timestamp( travail.getDcTravail().getTime());
        new Timestamp( travail.getDfTravail().getTime() );

        travail.setdatePlanning(d);
        
        System.out.println("Travail : entree dans insertTravail");
        String sql_insert = null;
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
                
        System.out.println("Travail toString :" + travail.toString());
        
        sql_insert = chargSqlTravail(sql_insert, travail);
        
        System.out.println("SQL_INSERT :" + sql_insert);

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
                Logger.getLogger(Travail.class.getName()).log(Level.SEVERE, null, ex);
            }
            host = properties.getProperty( "url" );
            uName = properties.getProperty( "nomutilisateur" );
            uPass = properties.getProperty( "motdepasse" );
              
        }

        try {
            Connection connexion = DriverManager.getConnection(host, uName, uPass);
            System.out.println("Chantier : connexion faite");
            Statement stmt = connexion.createStatement();
             
            int result = stmt.executeUpdate(sql_insert , Statement.RETURN_GENERATED_KEYS);
            System.out.println("resultat :" + result);
            ResultSet resultat = stmt.getGeneratedKeys();
            while ( resultat.next() ) { 
                   
                   travail.setIdTravail(Long.valueOf(resultat.getInt(1)));
                   System.out.println("insertTravail: --> ID : "  + travail.idTravail );
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

//<editor-fold defaultstate="collapsed" desc="Lister Travaille">
public HashMap<Long, Travail> lister()   {
        String SQL_LIST     = "SELECT IDTRAVAIL,  "
                            + "IDPERSONNE, IDCHANTIER, DATEPLANNING, "
                            + " PRESENCEAMPLANNING, PRESENCEPMPLANNING,  "
                            + " DCTRAVAILLE, DFTRAVAILLE "
                            + "  FROM TRAVAILLE ORDER BY IDTRAVAILLE";
        
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<Long, Travail> travailles = new HashMap<Long, Travail>();

        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
                 
        if ( fichierProperties == null ) {
            System.err.println("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        } else
          {
            try {
                properties.load( fichierProperties );
            } catch (IOException ex) {
                Logger.getLogger(Travail.class.getName()).log(Level.SEVERE, null, ex);
            }
            host = properties.getProperty( "url" );
            uName = properties.getProperty( "nomutilisateur" );
            uPass = properties.getProperty( "motdepasse" );
              
        }
         
        try {
            Connection connexion = DriverManager.getConnection(host, uName, uPass);
            System.out.println("Chantier select : connexion faite");
            preparedStatement = connexion.prepareStatement( SQL_LIST );
             
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) { 
            //       chantiers.add( map( resultSet ) );
                   travailles.put( resultSet.getLong( "IDTRAVAILLE" ), map( resultSet ) );
                   
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

        
        return travailles;         
   }    

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Lister Travail/date">
public HashMap<Long, Travail> listerParDate(Date deb)   {
        String SQL_LIST     = "SELECT IDTRAVAIL,  "
                            + "IDPERSONNE, IDCHANTIER, DATEPLANNING, "
                            + " PRESENCEAMPLANNING, PRESENCEPMPLANNING,  "
                            + " DCTRAVAILLE, DFTRAVAILLE "
                            + "  FROM TRAVAILLE  "
                            + " DATEPLANNING = " + deb 
                            + " and DATEPLANNING < " + deb + 7 ;
                
        
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<Long, Travail> travailles = new HashMap<Long, Travail>();

        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
                 
        if ( fichierProperties == null ) {
            System.err.println("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        } else
          {
            try {
                properties.load( fichierProperties );
            } catch (IOException ex) {
                Logger.getLogger(Travail.class.getName()).log(Level.SEVERE, null, ex);
            }
            host = properties.getProperty( "url" );
            uName = properties.getProperty( "nomutilisateur" );
            uPass = properties.getProperty( "motdepasse" );
              
        }
         
        try {
            Connection connexion = DriverManager.getConnection(host, uName, uPass);
            System.out.println("Chantier select : connexion faite");
            preparedStatement = connexion.prepareStatement( SQL_LIST );
             
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) { 
            //       chantiers.add( map( resultSet ) );
                   travailles.put( resultSet.getLong( "IDTRAVAILLE" ), map( resultSet ) );
                   
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

        
        return travailles;         
   }    

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="chargement SQL pour insert Travaille">  
private String chargSqlTravail(String sql_insert, Travail travail) {    
    sql_insert
                = "INSERT INTO TRAVAILLE ( IDPERSONNE, IDCHANTIER, "
                + " DATEPLANNING, PRESENCEAMPLANNING,  "
                + "PRESENCEPMPLANNING,  "
                + " DCTRAVAILLE, DFTRAVAILLE) "
                + " VALUES ( " + travail.getEmploye().getIdPersonne() + " , "
                + "  " + travail.getChantier().getIdChantier() + " , "
                + " '" + travail.getdatePlanning()+ "' , " 
                + "  '" + travail.isPresenceAM() + "' , " 
                + "  '" + travail.isPresencePM() + "' , "
                + "  '" + travail.getDcTravail() + "' , "
                + "  '" + travail.getDfTravail() + "'  "
                +    ") "
               ;
        
        System.out.println("sql_insert :" + sql_insert);

    
    return sql_insert;
    
 }
//</editor-fold>


//<editor-fold defaultstate="collapsed" desc="mapping resultSet pour Travail">
private static Travail map( ResultSet resultSet ) throws SQLException {
        Travail travail = new Travail();
        Employe employe = new Employe();
        Chantier chantier = new Chantier();
        employe =  employe.listerE(resultSet.getLong( "IDPERSONNE" ));;
        chantier = chantier.lister(resultSet.getLong( "IDCHANTIER" ));
        
        travail.setIdTravail(resultSet.getLong( "IDTRAVAILLE" ) );
        travail.setEmploye(employe);
        travail.setChantier(chantier);
        
        travail.setdatePlanning(resultSet.getDate("DATEPLANNING" ));
        travail.setPresenceAM(resultSet.getBoolean("PRESENCEAMPLANNING" ) );
        travail.setPresencePM(resultSet.getBoolean("PRESENCEPMPLANNING" ) );
        travail.setDcTravail(resultSet.getTimestamp("DCTRAVAILLE" ) );
        travail.setDfTravail(resultSet.getTimestamp("DFTRAVAILLE" ) );
        
        return travail;
    }
//</editor-fold>


   
    
}
