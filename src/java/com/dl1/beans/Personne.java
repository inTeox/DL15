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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")
public class Personne implements Serializable, Comparable {

    protected Long   idPersonne;
    private String nom;
    private String prenom;
    private String type;
    private String telephone;
    private String mobile;
    private String mail;
    private String noteP;
    private Timestamp dateDebutP;
    private Timestamp dateFinP;

//<editor-fold defaultstate="collapsed" desc="constructeur ">    
    public Personne() {
    }

    public Personne(Long idPersonne, String nom, String prenom, String type, String telephone, Timestamp dateDebutP) {
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.telephone = telephone;
        this.dateDebutP = dateDebutP;
    }

    public Personne(Long idPersonne, String nom, String prenom, String type, String telephone, Timestamp dateDebutP, Timestamp dateFinP) {
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.telephone = telephone;
        this.dateDebutP = dateDebutP;
        this.dateFinP = dateFinP;
    }

    public Personne(Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, Timestamp dateDebutP, Timestamp dateFinP) {
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.telephone = telephone;
        this.mobile = mobile;
        this.dateDebutP = dateDebutP;
        this.dateFinP = dateFinP;
    }

    public Personne(Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, String mail, Timestamp dateDebutP, Timestamp dateFinP) {
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.telephone = telephone;
        this.mobile = mobile;
        this.mail = mail;
        this.dateDebutP = dateDebutP;
        this.dateFinP = dateFinP;
    }

    public Personne(Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, String mail, String noteP, Timestamp dateDebutP, Timestamp dateFinP) {
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.telephone = telephone;
        this.mobile = mobile;
        this.mail = mail;
        this.noteP = noteP;
        this.dateDebutP = dateDebutP;
        this.dateFinP = dateFinP;
    }
    
//</editor-fold>    
    
    
//<editor-fold defaultstate="collapsed" desc="getter et setter ">
    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
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

//</editor-fold>    
    
    
//<editor-fold defaultstate="collapsed" desc="methode override ">    
    @Override
    public int compareTo(Object o) {
        Personne p = (Personne) o;
        int compNom = getNom().compareTo(p.getNom());
        return compNom != 0 ? compNom : getPrenom().compareTo(p.getPrenom());
    } 
    
    @Override
    public int hashCode() {
        return 31 * getPrenom().hashCode() + getNom().hashCode();
    }
    
     @Override
    public boolean equals(Object o) {
        return o instanceof Personne
                && ((Personne) o).getPrenom().equals(getPrenom())
                && ((Personne) o).getNom().equals(getNom());
    }

   @Override
    public String toString() {
        return "Personne{" + "idPersonne=" + idPersonne + ", nom=" + nom + ", prenom=" 
                + prenom + ", type=" + type + ", telephone=" + telephone + ", mobile=" 
                + mobile + ", mail=" + mail + ", noteP=" + noteP + ", dateDebutP=" 
                + dateDebutP + ", dateFinP=" + dateFinP + '}';
    }
//</editor-fold> 
 
//<editor-fold defaultstate="collapsed" desc="insertPersonne">    
public void insertPersonne(Personne personne) {

        new Timestamp( personne.getDateDebutP().getTime());
        new Timestamp( personne.getDateFinP().getTime() );
        
        System.out.println("Personne : entree dans insertPersonne");
        String sql_insert = null;
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
                
        sql_insert = chargSqlPersonne(sql_insert, personne);
        
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
             
            int result = stmt.executeUpdate(sql_insert , Statement.RETURN_GENERATED_KEYS);
            System.out.println("resultat :" + result);
            ResultSet resultat = stmt.getGeneratedKeys();
            while ( resultat.next() ) { 
                   
                   personne.setIdPersonne(Long.valueOf(resultat.getInt(1)));
                   System.out.println("insertPersonne: --> ID : "  + personne.idPersonne );
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
//<editor-fold defaultstate="collapsed" desc="Lister Personne">
public Map<Long, Personne> lister()   {
        String SQL_LIST     = "SELECT IDPERSONNE, NOMPERSONNE, "
                            + " PRENOMPERSONNE, TYPEPERSONNE, "
                            + " TELPERSONNE, MOBPERSONNE, MAILPERSONNE, "
                            + " NOTEPERSONNE, DCPERSONNE, DFPERSONNE "
                            + " DATENAISSEMPLOYE, ANEXPERIENCE, FONCTIONEMPLOYE, NUMEROSSEMPLOYE, " 
                            + " NATIONALITEEMPLOYE, TITRESEJOUREMPLOYE, PERMISTRAVAILEMPLOYE,  "
                            + " CODEACCESEMPLOYE, DCEMPLOYE, DFEMPLOYE,TYPECLIENT, FAXCLIENT, NOTECLIENT, "
                            + " DCCLIENT, DFCLIENT  "
                            + "  FROM PERSONNE ORDER BY IDPERSONNE";
        
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<Long, Personne> personnes = new HashMap<Long, Personne>();

        
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
            System.out.println("Personne select : connexion faite");
            preparedStatement = connexion.prepareStatement( SQL_LIST );
             
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) { 
            //       personnes.add( map( resultSet ) );
                   personnes.put( resultSet.getLong( "IDPERSONNE" ), map( resultSet ) );
                   
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

        
        return personnes;         
   }    
public Map<Long, Personne> lister(String type)   {
        String SQL_SELECT       = "SELECT IDPERSONNE, NOMPERSONNE, "
                                + " PRENOMPERSONNE, TYPEPERSONNE, "
                                + " TELPERSONNE, MOBPERSONNE, MAILPERSONNE, "
                                + " NOTEPERSONNE, DCPERSONNE, DFPERSONNE "
                                + "  FROM PERSONNE WHERE TYPEPERSONNE = '"
                                + type + "'";
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<Long, Personne> personnes = new HashMap<Long, Personne>();

        
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
            System.out.println("Personne select : connexion faite");
            preparedStatement = connexion.prepareStatement( SQL_SELECT );
             
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) { 
            //       personnes.add( map( resultSet ) );
                   personnes.put( resultSet.getLong( "IDPERSONNE" ), map( resultSet ) );
                   
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

        
        return personnes;         
   }
public Map<Long, Personne> listerClient()   {
    
    
        String SQL_SELECT       = "SELECT IDPERSONNE, NOMPERSONNE, "
                                + " PRENOMPERSONNE, TYPEPERSONNE, "
                                + " TELPERSONNE, MOBPERSONNE, MAILPERSONNE, "
                                + " NOTEPERSONNE, DCPERSONNE, DFPERSONNE,  TYPECLIENT "
                                + "  FROM PERSONNE WHERE TYPEPERSONNE = 'client' "
                                + " OR TYPEPERSONNE = 'prospect' ";
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<Long, Personne> personnes = new HashMap<Long, Personne>();

        
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
            System.out.println("Personne select : connexion faite");
            preparedStatement = connexion.prepareStatement( SQL_SELECT );
             
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) { 
            //       personnes.add( map( resultSet ) );
                   personnes.put( resultSet.getLong( "IDPERSONNE" ), map( resultSet ) );
                   
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

        
        return personnes;         
   }    

public Personne lister(Long id)   {
        String SQL_LIST     = "SELECT IDPERSONNE, NOMPERSONNE, "
                            + " PRENOMPERSONNE, TYPEPERSONNE, "
                            + " TELPERSONNE, MOBPERSONNE, MAILPERSONNE, "
                            + " NOTEPERSONNE, DCPERSONNE, DFPERSONNE , "
                            + " DATENAISSEMPLOYE, ANEXPERIENCE, FONCTIONEMPLOYE, NUMEROSSEMPLOYE, " 
                            + " NATIONALITEEMPLOYE, TITRESEJOUREMPLOYE, PERMISTRAVAILEMPLOYE,  "
                            + " CODEACCESEMPLOYE, DCEMPLOYE, DFEMPLOYE, TYPECLIENT, FAXCLIENT, AUTRETELCLIENT, "
                            + " NOTECLIENT, DCCLIENT, DFCLIENT  "
                            + "  FROM PERSONNE WHERE IDPERSONNE = " + id ;
        
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Personne personne = new Personne();

        
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
            System.out.println("Personne select : connexion faite :" + SQL_LIST);
            preparedStatement = connexion.prepareStatement( SQL_LIST );
             
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) { 
            //       personnes.add( map( resultSet ) );
                 personne =  map( resultSet ) ;
                   
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

        
        return personne;         
   }    

//</editor-fold>


//<editor-fold defaultstate="collapsed" desc="chargement SQL pour insert Personne">  
private String chargSqlPersonne(String sql_insert, Personne personne) {    
    sql_insert
                = "INSERT INTO PERSONNE (NOMPERSONNE, PRENOMPERSONNE, TYPEPERSONNE, "
                + " TELPERSONNE, MOBPERSONNE, MAILPERSONNE, NOTEPERSONNE, DCPERSONNE, DFPERSONNE, "
                + " DATENAISSEMPLOYE, ANEXPERIENCE, FONCTIONEMPLOYE, NUMEROSSEMPLOYE, "
                + " NATIONALITEEMPLOYE, TITRESEJOUREMPLOYE, PERMISTRAVAILEMPLOYE,  "
                + " CODEACCESEMPLOYE, EQUIPEBASEEMPLOYE, DCEMPLOYE, DFEMPLOYE, "
                + "  TYPECLIENT, FAXCLIENT, AUTRETELCLIENT, NOTECLIENT, "
                + " DCCLIENT, DFCLIENT) "
                + " VALUES ( '" + personne.getNom() + "', "
                + "  '" + personne.getPrenom() + "', "
                + "  '" + personne.getType() + "', "
                + "  '" + personne.getTelephone() + "', " ;
                

        System.out.println("insertPersonne: --> SQL_INSERT : " + sql_insert);
        
        if (personne.mobile == null) {
            sql_insert = sql_insert + null + " ,";
            System.out.println("insertPersonne: --> passage null mobile : " );
        } else {
            sql_insert = sql_insert + " '" + personne.getMobile() + "' , ";
            System.out.println("insertPersonne: --> passage pas null mobile : " );
        }
        

        if (personne.mail == null) {
            sql_insert = sql_insert + null + " ,";
        } else {
            sql_insert = sql_insert + " '" + personne.getMail() + "' , ";
        }
        
        if (personne.noteP == null ) {
            sql_insert = sql_insert + null + " ,";
        } else {
            sql_insert = sql_insert + " '" + personne.getNoteP() + "' , ";
        }
        
        
        sql_insert = sql_insert + "'" +  personne.getDateDebutP()     + "', "
                   +    "'" +  personne.getDateFinP()     + "' , "
                   + null + " ," + null + " ," + null + " ," + null + " ," 
                   + null + " ," + null + " ," + null + " ," + null + " ,"
                   + null + " ," + null + " ," + null + " ," + null + " ,"
                   + null + " ,"
                   + null + " ," + null + " ," + null + " ," + null + ") ";
        
         
        
        System.out.println("sql_insert :" + sql_insert);

    
    return sql_insert;
    
 }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="mapping resultSet pour Personne">
private static Personne map( ResultSet resultSet ) throws SQLException {
        Personne personne = new Personne();
        personne.setIdPersonne( resultSet.getLong( "IDPERSONNE" ) );
        personne.setNom( resultSet.getString( "NOMPERSONNE" ) );
        personne.setPrenom( resultSet.getString( "PRENOMPERSONNE" ) );
        personne.setType( resultSet.getString( "TYPEPERSONNE" ) );
        personne.setTelephone( resultSet.getString( "TELPERSONNE" ) );
        personne.setMobile( resultSet.getString( "MOBPERSONNE" ) );
        personne.setMail( resultSet.getString( "MAILPERSONNE" ) );
        personne.setNoteP( resultSet.getString( "NOTEPERSONNE" ) );
        personne.setDateDebutP(resultSet.getTimestamp( "DCPERSONNE" ) );
        personne.setDateFinP(resultSet.getTimestamp( "DFPERSONNE" ) );
        return personne;
    }
//</editor-fold>

}
