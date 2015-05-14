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
public class Chantier implements Serializable, Comparable {

//<editor-fold defaultstate="collapsed" desc="variables ">    
    protected Long        idChantier;
    protected Personne    personne;
    protected Adresse     adresse;
    protected String      nomChantier;
    protected Integer     nbrJourDevis;
    protected Integer     nbrJourReal;
    protected Float       mtInitial;
    protected Float       mtRealise;
    protected String      status;
    protected Date        dateDebPrevu;
    protected Date        dateDebReel;
    protected Date        dateFinPrevu;
    protected Date        dateFinReel;
    protected Timestamp   dcChantier;
    protected Timestamp   dfChantier;
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="getter et setter ">    
    public Long getIdChantier() {
        return idChantier;
    }

    public void setIdChantier(Long idChantier) {
        this.idChantier = idChantier;
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

    public String getNomChantier() {
        return nomChantier;
    }

    public void setNomChantier(String nomChantier) {
        this.nomChantier = nomChantier;
    }

    public Integer getNbrJourDevis() {
        return nbrJourDevis;
    }

    public void setNbrJourDevis(Integer nbrJourDevis) {
        this.nbrJourDevis = nbrJourDevis;
    }

    public Integer getNbrJourReal() {
        return nbrJourReal;
    }

    public void setNbrJourReal(Integer nbrJourReal) {
        this.nbrJourReal = nbrJourReal;
    }

    public Float getMtInitial() {
        return mtInitial;
    }

    public void setMtInitial(Float mtInitial) {
        this.mtInitial = mtInitial;
    }

    public Float getMtRealise() {
        return mtRealise;
    }

    public void setMtRealise(Float mtRealise) {
        this.mtRealise = mtRealise;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateDebPrevu() {
        return dateDebPrevu;
    }

    public void setDateDebPrevu(Date dateDebPrevu) {
        this.dateDebPrevu = dateDebPrevu;
    }

    public Date getDateDebReel() {
        return dateDebReel;
    }

    public void setDateDebReel(Date dateDebReel) {
        this.dateDebReel = dateDebReel;
    }

    public Date getDateFinPrevu() {
        return dateFinPrevu;
    }

    public void setDateFinPrevu(Date dateFinPrevu) {
        this.dateFinPrevu = dateFinPrevu;
    }

    public Date getDateFinReel() {
        return dateFinReel;
    }

    public void setDateFinReel(Date dateFinReel) {
        this.dateFinReel = dateFinReel;
    }

    public Timestamp getDcChantier() {
        return dcChantier;
    }

    public void setDcChantier(Timestamp dcChantier) {
        this.dcChantier = dcChantier;
    }

    public Timestamp getDfChantier() {
        return dfChantier;
    }

    public void setDfChantier(Timestamp dfChantier) {
        this.dfChantier = dfChantier;
    }
    
//</editor-fold>    
    
//<editor-fold defaultstate="collapsed" desc="constructeur ">    

    public Chantier() {
    }

    public Chantier(Long idChantier, Personne personne, Adresse adresse, String nomChantier, Integer nbrJourDevis, Integer nbrJourReal, Float mtInitial, Float mtRealise, String status, Date dateDebPrevu, Date dateDebReel, Date dateFinPrevu, Date dateFinReel, Timestamp dcChantier, Timestamp dfChantier) {
        this.idChantier = idChantier;
        this.personne = personne;
        this.adresse = adresse;
        this.nomChantier = nomChantier;
        this.nbrJourDevis = nbrJourDevis;
        this.nbrJourReal = nbrJourReal;
        this.mtInitial = mtInitial;
        this.mtRealise = mtRealise;
        this.status = status;
        this.dateDebPrevu = dateDebPrevu;
        this.dateDebReel = dateDebReel;
        this.dateFinPrevu = dateFinPrevu;
        this.dateFinReel = dateFinReel;
        this.dcChantier = dcChantier;
        this.dfChantier = dfChantier;
    }

    
     
    
    
//</editor-fold>    
    
//<editor-fold defaultstate="collapsed" desc="methode override ">    
@Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Chantier{" + "idChantier=" + idChantier + ", personne=" 
                + personne + ", adresse=" + adresse + ", nomChantier=" 
                + nomChantier + ", nbrJourDevis=" + nbrJourDevis 
                + ", nbrJourReal=" + nbrJourReal + ", mtInitial=" + mtInitial 
                + ", mtRealise=" + mtRealise + ", status=" + status 
                + ", dateDebPrevu=" + dateDebPrevu + ", dateDebReel=" 
                + dateDebReel + ", dateFinPrevu=" + dateFinPrevu 
                + ", dateFinReel=" + dateFinReel + ", dcChantier=" + dcChantier 
                + ", dfChantier=" + dfChantier + '}';
    }
    
    
    
    
//</editor-fold> 

//<editor-fold defaultstate="collapsed" desc="insertChantier">    
public void insertChantier(Chantier chantier) {

        new Timestamp( chantier.getDcChantier().getTime());
        new Timestamp( chantier.getDfChantier().getTime() );
        
        System.out.println("Chantier : entree dans insertChantier");
        String sql_insert = null;
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
                
        System.out.println("Chantier toString :" + chantier.toString());
        
        sql_insert = chargSqlChantier(sql_insert, chantier);
        
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
                Logger.getLogger(Chantier.class.getName()).log(Level.SEVERE, null, ex);
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
                   
                   chantier.setIdChantier(Long.valueOf(resultat.getInt(1)));
                   System.out.println("insertChantier: --> ID : "  + chantier.idChantier );
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

//<editor-fold defaultstate="collapsed" desc="Lister Chantier">
public Map<Long, Chantier> lister()   {
        String SQL_LIST     = "SELECT IDCHANTIER,  "
                            + "IDPERSONNE, IDADRESSE, NOMCHANTIER, "
                            + " NBRJOURDEVISCHANTIER, NBRJOURREALISECHANTIER,  "
                            + "MTINITIALDEVISCHANTIER, MTTOTALREALISECHANTIER, "
                            + "STATUTCHANTIER, DATEDEBPREVUCHANTIER, DATEDEBCHANTIER, "
                            + "DATEFINPREVUCHANTIERR, DATEFINCHANTIER, "
                            + " DCCHANTIER, DFCHANTIER "
                            + "  FROM CHANTIER ORDER BY IDCHANTIER";
        
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<Long, Chantier> chantiers = new HashMap<Long, Chantier>();

        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
                 
        if ( fichierProperties == null ) {
            System.err.println("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        } else
          {
            try {
                properties.load( fichierProperties );
            } catch (IOException ex) {
                Logger.getLogger(Chantier.class.getName()).log(Level.SEVERE, null, ex);
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
                   chantiers.put( resultSet.getLong( "IDCHANTIER" ), map( resultSet ) );
                   
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

        
        return chantiers;         
   }    
public Chantier lister(Long id)   {
        String SQL_LIST     = "SELECT IDCHANTIER,  "
                            + "IDPERSONNE, IDADRESSE, NOMCHANTIER, "
                            + " NBRJOURDEVISCHANTIER, NBRJOURREALISECHANTIER,  "
                            + "MTINITIALDEVISCHANTIER, MTTOTALREALISECHANTIER, "
                            + "STATUTCHANTIER, DATEDEBPREVUCHANTIER, DATEDEBCHANTIER, "
                            + "DATEFINPREVUCHANTIERR, DATEFINCHANTIER, "
                            + " DCCHANTIER, DFCHANTIER "
                            + "  FROM CHANTIER WHERE IDCHANTIER = " + id ;
        
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Chantier chantier = new Chantier();

        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
                 
        if ( fichierProperties == null ) {
            System.err.println("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        } else
          {
            try {
                properties.load( fichierProperties );
            } catch (IOException ex) {
                Logger.getLogger(Chantier.class.getName()).log(Level.SEVERE, null, ex);
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
                   chantier =  map( resultSet ) ;
                   
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

        
        return chantier;         
   }    


//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="chargement SQL pour insert Chantier">  
private String chargSqlChantier(String sql_insert, Chantier chantier) {    
    sql_insert
                = "INSERT INTO CHANTIER (IDPERSONNE, IDADRESSE, NOMCHANTIER, "
                + " NBRJOURDEVISCHANTIER, NBRJOURREALISECHANTIER,  "
                + "MTINITIALDEVISCHANTIER, MTTOTALREALISECHANTIER, "
                + "STATUTCHANTIER, DATEDEBPREVUCHANTIER, DATEDEBCHANTIER, "
                + "DATEFINPREVUCHANTIERR, DATEFINCHANTIER, "
                + " DCCHANTIER, DFCHANTIER) "
                + " VALUES ( " + chantier.getPersonne().getIdPersonne() + " , "
                + "  " + chantier.getAdresse().getIdAdresse() + " , "
                + " '" + chantier.getNomChantier() + "' , " 
                + "  " + chantier.getNbrJourDevis() + " , " ;
        
    System.out.println("insertChantier1: --> " + sql_insert );
    
        if (chantier.nbrJourReal == null) {
            sql_insert = sql_insert + null + " ,";
            System.out.println("insertChantier: --> passage null nbrJourReal : " );
        } else {
            sql_insert = sql_insert + " " + chantier.getNbrJourReal() + " , ";
            System.out.println("insertChantier: --> passage pas null nbrJourReal : " );
        }
        

        if (chantier.mtInitial == null) {
            sql_insert = sql_insert + null + " ,";
        } else {
            sql_insert = sql_insert + " " + chantier.getMtInitial() + " , ";
        }
        
        if (chantier.mtRealise == null ) {
            sql_insert = sql_insert + null + " ,";
        } else {
            sql_insert = sql_insert + " " + chantier.getMtRealise() + " , ";
        }
        
        if (chantier.status == null ) {
            sql_insert = sql_insert + null + " ,";
        } else {
            sql_insert = sql_insert + " '" + chantier.getStatus() + "' , ";
        }
        
        if (chantier.dateDebPrevu == null ) {
            sql_insert = sql_insert + null + " ,";
        } else {
            sql_insert = sql_insert + " '" + chantier.getDateDebPrevu() + "' , ";
        }
        
        if (chantier.dateDebReel == null ) {
            sql_insert = sql_insert + null + " ,";
        } else {
            sql_insert = sql_insert + " '" + chantier.getDateDebReel() + "' , ";
        }
        
        if (chantier.dateFinPrevu == null ) {
            sql_insert = sql_insert + null + " ,";
        } else {
            sql_insert = sql_insert + " '" + chantier.getDateFinPrevu() + "' , ";
        }
        
        if (chantier.dateFinReel == null ) {
            sql_insert = sql_insert + null + " ,";
        } else {
            sql_insert = sql_insert + " '" + chantier.getDateFinReel() + "' , ";
        }
        
        sql_insert = sql_insert + "'" +  chantier.getDcChantier()     + "', "
                   +    "'" +  chantier.getDfChantier()     + "'  "
                   +    ") ";
        
         
        
        System.out.println("sql_insert :" + sql_insert);

    
    return sql_insert;
    
 }
//</editor-fold>


//<editor-fold defaultstate="collapsed" desc="mapping resultSet pour Chantier">
private static Chantier map( ResultSet resultSet ) throws SQLException {
        Chantier chantier = new Chantier();
        Personne personne = new Personne();
        personne =  personne.lister(resultSet.getLong( "IDPERSONNE" ));;
        
        Adresse adresse = new Adresse();
        adresse = adresse.lister(resultSet.getLong( "IDADRESSE" ));
//      personne.setIdPersonne(resultSet.getLong( "IDPERSONNE" ));
//        adresse.setIdAdresse(resultSet.getLong( "IDADRESSE" ));
        chantier.setIdChantier(resultSet.getLong( "IDCHANTIER" ) );
        chantier.setPersonne(personne);
        chantier.setAdresse(adresse);
        
        chantier.setNomChantier(resultSet.getString( "NOMCHANTIER" ) );
        chantier.setNbrJourDevis(resultSet.getInt("NBRJOURDEVISCHANTIER" ) );
        chantier.setNbrJourReal(resultSet.getInt( "NBRJOURREALISECHANTIER" ) );
        chantier.setMtInitial(resultSet.getFloat("MTINITIALDEVISCHANTIER" ) );
        chantier.setMtRealise(resultSet.getFloat("MTTOTALREALISECHANTIER" ) );
        chantier.setStatus(resultSet.getString("STATUTCHANTIER" ) );
        chantier.setDateDebPrevu(resultSet.getDate("DATEDEBPREVUCHANTIER" ) );
        chantier.setDateDebReel(resultSet.getDate("DATEDEBCHANTIER" ) );
        chantier.setDateFinPrevu(resultSet.getDate("DATEFINPREVUCHANTIERR" ) );
        chantier.setDateFinReel(resultSet.getDate("DATEFINCHANTIER" ) );
        chantier.setDcChantier(resultSet.getTimestamp("DCCHANTIER" ) );
        chantier.setDfChantier(resultSet.getTimestamp("DFCHANTIER" ) );
        
        return chantier;
    }
//</editor-fold>


    
}
