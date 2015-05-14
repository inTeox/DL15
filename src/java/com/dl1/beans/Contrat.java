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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")
public class Contrat implements Serializable, Comparable {

//<editor-fold defaultstate="collapsed" desc="variables ">    
    protected Long        idContrat;
    protected Employe     employe;
    protected String      poste;
    protected String      qualif;
    protected Float       remunerationHoraire;
    protected Date        dateDebContrat;
    protected Date        dateFinContrat;
    protected Timestamp   dcContrat;
    protected Timestamp   dfContrat;
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="getter et setter ">    
public Long getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(Long idContrat) {
        this.idContrat = idContrat;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getQualif() {
        return qualif;
    }

    public void setQualif(String qualif) {
        this.qualif = qualif;
    }

    public Float getRemunerationHoraire() {
        return remunerationHoraire;
    }

    public void setRemunerationHoraire(Float remunerationHoraire) {
        this.remunerationHoraire = remunerationHoraire;
    }

    public Date getDateDebContrat() {
        return dateDebContrat;
    }

    public void setDateDebContrat(Date dateDebContrat) {
        this.dateDebContrat = dateDebContrat;
    }

    public Date getDateFinContrat() {
        return dateFinContrat;
    }

    public void setDateFinContrat(Date dateFinContrat) {
        this.dateFinContrat = dateFinContrat;
    }

    public Timestamp getDcContrat() {
        return dcContrat;
    }

    public void setDcContrat(Timestamp dcContrat) {
        this.dcContrat = dcContrat;
    }

    public Timestamp getDfContrat() {
        return dfContrat;
    }

    public void setDfContrat(Timestamp dfContrat) {
        this.dfContrat = dfContrat;
    }
    
//</editor-fold>    
   
//<editor-fold defaultstate="collapsed" desc="constructeur ">    

    public Contrat() {
    } 
    
    
//</editor-fold>    
    
//<editor-fold defaultstate="collapsed" desc="methode override ">    
@Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
//</editor-fold> 

//<editor-fold defaultstate="collapsed" desc="insertContrat">    
public void insertContrat(Contrat contrat) {

        new Timestamp( contrat.getDcContrat().getTime());
        new Timestamp( contrat.getDfContrat().getTime() );
        
        System.out.println("Contrat : entree dans insertContrat");
        String sql_insert = null;
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
                
        System.out.println("Contrat toString :" + contrat.toString());
        
        sql_insert = chargSqlContrat(sql_insert, contrat);
        
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
                Logger.getLogger(Contrat.class.getName()).log(Level.SEVERE, null, ex);
            }
            host = properties.getProperty( "url" );
            uName = properties.getProperty( "nomutilisateur" );
            uPass = properties.getProperty( "motdepasse" );
              
        }

        try {
            Connection connexion = DriverManager.getConnection(host, uName, uPass);
            System.out.println("Contrat : connexion faite");
            Statement stmt = connexion.createStatement();
             
            int result = stmt.executeUpdate(sql_insert , Statement.RETURN_GENERATED_KEYS);
            System.out.println("resultat :" + result);
            ResultSet resultat = stmt.getGeneratedKeys();
            while ( resultat.next() ) { 
                   
                   contrat.setIdContrat(Long.valueOf(resultat.getInt(1)));
                   System.out.println("insertContrat: --> ID : "  + contrat.idContrat );
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

//<editor-fold defaultstate="collapsed" desc="Lister Contrat">
public Map<Long, Contrat> lister()   {
        String SQL_LIST     = "SELECT IDCONTRAT,  "
                            + "IDPERSONNE, POSTECONTRAT, QUALIFCONTRAT, "
                            + " REMUNERATIONHORAIRECONTRAT, DATEDEBUTCONTRATT,  "
                            + "DATEFINCONTRAT,  "
                            + " DCCONTRAT, DFCONTRAT "
                            + "  FROM CONTRAT ORDER BY IDCONTRAT";
        
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<Long, Contrat> contrats = new HashMap<Long, Contrat>();

        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
                 
        if ( fichierProperties == null ) {
            System.err.println("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        } else
          {
            try {
                properties.load( fichierProperties );
            } catch (IOException ex) {
                Logger.getLogger(Contrat.class.getName()).log(Level.SEVERE, null, ex);
            }
            host = properties.getProperty( "url" );
            uName = properties.getProperty( "nomutilisateur" );
            uPass = properties.getProperty( "motdepasse" );
              
        }
         
        try {
            Connection connexion = DriverManager.getConnection(host, uName, uPass);
            System.out.println("Contrat select : connexion faite");
            preparedStatement = connexion.prepareStatement( SQL_LIST );
             
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) { 
            //       chantiers.add( map( resultSet ) );
                   contrats.put( resultSet.getLong( "IDCONTRAT" ), map( resultSet ) );
                   
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

        
        return contrats;         
   }    
public ArrayList<Contrat> listerA()   {
        String SQL_LIST     = "SELECT IDCONTRAT,  "
                            + "IDPERSONNE, POSTECONTRAT, QUALIFCONTRAT, "
                            + " REMUNERATIONHORAIRECONTRAT, DATEDEBUTCONTRATT,  "
                            + "DATEFINCONTRAT,  "
                            + " DCCONTRAT, DFCONTRAT "
                            + "  FROM CONTRAT ORDER BY IDCONTRAT";
        
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Contrat> contrats = new ArrayList<Contrat>();

        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
                 
        if ( fichierProperties == null ) {
            System.err.println("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        } else
          {
            try {
                properties.load( fichierProperties );
            } catch (IOException ex) {
                Logger.getLogger(Contrat.class.getName()).log(Level.SEVERE, null, ex);
            }
            host = properties.getProperty( "url" );
            uName = properties.getProperty( "nomutilisateur" );
            uPass = properties.getProperty( "motdepasse" );
              
        }
         
        try {
            Connection connexion = DriverManager.getConnection(host, uName, uPass);
            System.out.println("Contrat select : connexion faite");
            preparedStatement = connexion.prepareStatement( SQL_LIST );
             
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) { 
            //       chantiers.add( map( resultSet ) );
                   contrats.add(map( resultSet ));
                   
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

        
        return contrats;         
   }    

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="chargement SQL pour insert Contrat">  
private String chargSqlContrat(String sql_insert, Contrat contrat) {    
    sql_insert
                = "INSERT INTO CONTRAT (IDPERSONNE, POSTECONTRAT, "
                + " QUALIFCONTRAT, REMUNERATIONHORAIRECONTRAT,  "
                + "DATEDEBUTCONTRATT, DATEFINCONTRAT, "
                + " DCCONTRAT, DFCONTRAT) "
                + " VALUES ( " + contrat.getEmploye().getIdPersonne() + " , " ;
                
    System.out.println("chargSqlContrat: --> " + sql_insert );
    
        if (contrat.poste == null) {
            sql_insert = sql_insert + null + " ,";
            System.out.println("insertContrat: --> passage null poste : " );
        } else {
            sql_insert = sql_insert + " '" + contrat.getPoste() + "' , ";
            System.out.println("chargSqlContrat: --> passage pas null poste : " );
        }
        

        if (contrat.qualif == null) {
            sql_insert = sql_insert + null + " ,";
        } else {
            sql_insert = sql_insert + " '" + contrat.getQualif() + "' , ";
        }
        
        if (contrat.remunerationHoraire == null ) {
            sql_insert = sql_insert + null + " ,";
        } else {
            sql_insert = sql_insert + " " + contrat.getRemunerationHoraire() + " , ";
        }
        
        if (contrat.dateDebContrat == null ) {
            sql_insert = sql_insert + null + " ,";
        } else {
            sql_insert = sql_insert + " '" + contrat.getDateDebContrat() + "' , ";
        }
        
        if (contrat.dateFinContrat == null ) {
            sql_insert = sql_insert + null + " ,";
        } else {
            sql_insert = sql_insert + " '" + contrat.getDateFinContrat() + "' , ";
        }
        
        sql_insert = sql_insert + "'" +  contrat.getDcContrat()     + "', "
                   +    "'" +  contrat.getDfContrat()     + "'  "
                   +    ") ";
        
         
        
        System.out.println("sql_insert :" + sql_insert);

    
    return sql_insert;
    
 }
//</editor-fold>


//<editor-fold defaultstate="collapsed" desc="mapping resultSet pour Chantier">
private static Contrat map( ResultSet resultSet ) throws SQLException {
        Contrat contrat = new Contrat();
        Employe employe = new Employe();
        
        employe =  employe.listerE(resultSet.getLong( "IDPERSONNE" ));
        
        contrat.setIdContrat(resultSet.getLong( "IDCONTRAT" ) );
        contrat.setEmploye(employe);
        
        contrat.setPoste(resultSet.getString( "POSTECONTRAT" ) );
        contrat.setQualif(resultSet.getString("QUALIFCONTRAT" ) );
        contrat.setRemunerationHoraire(resultSet.getFloat("REMUNERATIONHORAIRECONTRAT" ) );
        contrat.setDateDebContrat(resultSet.getDate("DATEDEBUTCONTRATT" ) );
        contrat.setDateFinContrat(resultSet.getDate("DATEFINCONTRAT" ) );
        contrat.setDcContrat(resultSet.getTimestamp("DCCONTRAT" ) );
        contrat.setDfContrat(resultSet.getTimestamp("DFCONTRAT" ) );
        
        return contrat;
    }
//</editor-fold>

    @Override
    public String toString() {
        return "Contrat{" + "idContrat=" + idContrat + ", employe=" + employe + ", poste=" + poste + ", qualif=" + qualif + ", remunerationHoraire=" + remunerationHoraire + ", dateDebContrat=" + dateDebContrat + ", dateFinContrat=" + dateFinContrat + ", dcContrat=" + dcContrat + ", dfContrat=" + dfContrat + '}';
    }



        
}
