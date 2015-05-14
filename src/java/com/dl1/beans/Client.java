package com.dl1.beans;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends Personne {
    
    private String    typeClient;
    private String    fax;
    private String    autretelephone;
    private String    note;
    private Timestamp dateDebutPr;
    private Timestamp dateFinPr;
    
//<editor-fold defaultstate="collapsed" desc="constructeur ">

    public Client() {
    }

    public Client(String typeClient) {
        this.typeClient = typeClient;
    }

    public Client(String typeClient, String fax, String autretelephone, Timestamp dateDebutPr, Timestamp dateFinPr) {
        this.typeClient = typeClient;
        this.fax = fax;
        this.autretelephone = autretelephone;
        this.dateDebutPr = dateDebutPr;
        this.dateFinPr = dateFinPr;
    }

    public Client(String typeClient, String fax, String autretelephone, String note, Timestamp dateDebutPr, Timestamp dateFinPr) {
        this.typeClient = typeClient;
        this.fax = fax;
        this.autretelephone = autretelephone;
        this.note = note;
        this.dateDebutPr = dateDebutPr;
        this.dateFinPr = dateFinPr;
    }

    

    //</editor-fold>
//<editor-fold defaultstate="collapsed" desc="getter et setter">
    public void setTypeClient(String typeClient) {
        this.typeClient = typeClient;
    }
    public String getTypeClient() {
        return typeClient;
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
    
//</editor-fold>      
    @Override
    public String toString() {
        return super.toString() +  "Client{" + "typeClient=" + typeClient + 
                ", fax=" + fax + ", autretelephone=" + autretelephone + 
                ", note=" + note + ", dateDebutPr=" + dateDebutPr + 
                ", dateFinPr=" + dateFinPr + '}';
    }
    
  
//<editor-fold defaultstate="collapsed" desc="insertClient">
public static String insertClient(Client client, Personne personne) {

    String msg = null;
    new Timestamp( client.getDateDebutPr().getTime());
    new Timestamp( client.getDateFinPr().getTime() );

    System.out.println("Client : entree dans insertClient");    
        
    String sql_update = ( "UPDATE PERSONNE  set TYPECLIENT = ? " + " , "
                        + "FAXCLIENT = ? " + " , "
                        + " AUTRETELCLIENT = ? "  + " , "
                        + " NOTECLIENT = ? " + " , "
                        + " DCCLIENT = ? " + " , "
                        + " DFCLIENT = ? " 
                        + " WHERE  IDPERSONNE = ? " ) ; 
     
    System.out.println("insertClient: --> SQL_UPDATE : " + sql_update);
        
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
            
            stmt.setString(1, client.getTypeClient());
            stmt.setString(2, client.getFax());
            stmt.setString(3, client.getAutretelephone());
            stmt.setString(4, client.getNote());
            stmt.setTimestamp(5,client.getDateDebutPr());
            stmt.setTimestamp(6,client.getDateFinPr());
            stmt.setLong(7,client.idPersonne);
    
            
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

            msg = "Succès de la création du client.";
            
    
    return msg;
}    
//</editor-fold>        
//<editor-fold defaultstate="collapsed" desc="lister()">    
public Map<Long, Client> listerByType(String type)   {
        String SQL_SELECT       = "SELECT IDPERSONNE, NOMPERSONNE, "
                                + " PRENOMPERSONNE, TYPEPERSONNE, "
                                + " TELPERSONNE, MOBPERSONNE, MAILPERSONNE, "
                                + " NOTEPERSONNE, DCPERSONNE, DFPERSONNE, "
                                + " TYPECLIENT, FAXCLIENT, AUTRETELCLIENT, NOTECLIENT, " 
                                + " DCCLIENT, DFCLIENT "
                                + "  FROM PERSONNE WHERE TYPECLIENT = '"
                                + type + "'";
        
        
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<Long, Client> clients = new HashMap<Long, Client>();

        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
                 
        if ( fichierProperties == null ) {
            System.err.println("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        } else
          {
            try {
                properties.load( fichierProperties );
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            host = properties.getProperty( "url" );
            uName = properties.getProperty( "nomutilisateur" );
            uPass = properties.getProperty( "motdepasse" );
              
        }
         
        try {
            Connection connexion = DriverManager.getConnection(host, uName, uPass);
            System.out.println("Client select : connexion faite");
            preparedStatement = connexion.prepareStatement( SQL_SELECT );
             
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) { 
            //       personnes.add( map( resultSet ) );
                   clients.put( resultSet.getLong( "IDPERSONNE" ), map( resultSet ) );
                   
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

        
        return clients;         
   }
public Map<Long, Client> listerC()   {
        String SQL_SELECT       = "SELECT IDPERSONNE, NOMPERSONNE, "
                                + " PRENOMPERSONNE, TYPEPERSONNE, "
                                + " TELPERSONNE, MOBPERSONNE, MAILPERSONNE, "
                                + " NOTEPERSONNE, DCPERSONNE, DFPERSONNE, "
                                + " TYPECLIENT, FAXCLIENT, AUTRETELCLIENT, NOTECLIENT, " 
                                + " DCCLIENT, DFCLIENT "   
                                + "  FROM PERSONNE WHERE TYPEPERSONNE <> 'EMPLOYE' " 
                                + "  ORDER BY IDPERSONNE" ;
                                
        
        
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<Long, Client> clients = new HashMap<Long, Client>();

        
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
            System.out.println("Client select : connexion faite");
            preparedStatement = connexion.prepareStatement( SQL_SELECT );
             
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) { 
            //       personnes.add( map( resultSet ) );
                   clients.put( resultSet.getLong( "IDPERSONNE" ), map( resultSet ) );
                   
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

        
        return clients;         
   }   
public Client lister(Long id)   {
        String SQL_SELECT       = "SELECT IDPERSONNE, NOMPERSONNE, "
                                + " PRENOMPERSONNE, TYPEPERSONNE, "
                                + " TELPERSONNE, MOBPERSONNE, MAILPERSONNE, "
                                + " NOTEPERSONNE, DCPERSONNE, DFPERSONNE, "
                                + " TYPECLIENT, FAXCLIENT, AUTRETELCLIENT, NOTECLIENT, " 
                                + " DCCLIENT, DFCLIENT "                
                                + "  FROM PERSONNE WHERE IDPERSONNE = " + id ;
                                
        
        
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Client client = new Client();

        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
                 
        if ( fichierProperties == null ) {
            System.err.println("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        } else
          {
            try {
                properties.load( fichierProperties );
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            host = properties.getProperty( "url" );
            uName = properties.getProperty( "nomutilisateur" );
            uPass = properties.getProperty( "motdepasse" );
              
        }
         
        try {
            Connection connexion = DriverManager.getConnection(host, uName, uPass);
            System.out.println("Client select : connexion faite");
            preparedStatement = connexion.prepareStatement( SQL_SELECT );
             
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) { 
            //       personnes.add( map( resultSet ) );
                   client = map( resultSet );
                   
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

        
        return client;         
   }    

//</editor-fold>    
//<editor-fold defaultstate="collapsed" desc="mapping resultSet pour Client">
private static Client map( ResultSet resultSet ) throws SQLException {
        
        Client client = new Client();
        client.setIdPersonne(resultSet.getLong( "IDPERSONNE" ) );
        client.setNom( resultSet.getString( "NOMPERSONNE" ) );
        client.setPrenom( resultSet.getString( "PRENOMPERSONNE" ) );
        client.setType( resultSet.getString( "TYPEPERSONNE" ) );
        client.setTelephone( resultSet.getString( "TELPERSONNE" ) );
        client.setMobile( resultSet.getString( "MOBPERSONNE" ) );
        client.setMail( resultSet.getString( "MAILPERSONNE" ) );
        client.setNoteP( resultSet.getString( "NOTEPERSONNE" ) );
        client.setDateDebutP(resultSet.getTimestamp( "DCPERSONNE" ) );
        client.setDateFinP(resultSet.getTimestamp( "DFPERSONNE" ) );
        client.setTypeClient(resultSet.getString( "TYPECLIENT" ) );
        client.setFax(resultSet.getString( "FAXCLIENT" ) );
        client.setAutretelephone(resultSet.getString( "AUTRETELCLIENT" ) );
        client.setNote(resultSet.getString( "NOTECLIENT" ) );
        client.setDateDebutPr(resultSet.getTimestamp("DCCLIENT" ) );
        client.setDateFinPr(resultSet.getTimestamp("DFCLIENT" ) );
        
        return client;
    }
//</editor-fold>
    

    
}