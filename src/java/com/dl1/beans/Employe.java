package com.dl1.beans;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Employe extends Personne {
    private Date   dateNaissance;
    private String securiteSociale;
    private String experience;
    private String fonction;
    private String nationalite;
    private String titreSejour;
    private String permisTravail;
    private String codeAcces;
    private String equipeBase;
    private Timestamp dateDebutE;
    private Timestamp dateFinE;

//<editor-fold defaultstate="collapsed" desc="constructeur ">
    
    public Employe() {
    }

    public Employe(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Employe(Date dateNaissance, Long idPersonne, String nom, String prenom, String type, String telephone, Timestamp dateDebutP) {
        super(idPersonne, nom, prenom, type, telephone, dateDebutP);
        this.dateNaissance = dateNaissance;
    }

    public Employe(Date dateNaissance, Long idPersonne, String nom, String prenom, String type, String telephone, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, dateDebutP, dateFinP);
        this.dateNaissance = dateNaissance;
    }

    public Employe(Date dateNaissance, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, dateDebutP, dateFinP);
        this.dateNaissance = dateNaissance;
    }

    public Employe(Date dateNaissance, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, String mail, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, mail, dateDebutP, dateFinP);
        this.dateNaissance = dateNaissance;
    }

    public Employe(Date dateNaissance, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, String mail, String noteP, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, mail, noteP, dateDebutP, dateFinP);
        this.dateNaissance = dateNaissance;
    }

    public Employe(Date dateNaissance, String securiteSociale) {
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
    }

    public Employe(Date dateNaissance, String securiteSociale, Long idPersonne, String nom, String prenom, String type, String telephone, Timestamp dateDebutP) {
        super(idPersonne, nom, prenom, type, telephone, dateDebutP);
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
    }

    public Employe(Date dateNaissance, String securiteSociale, Long idPersonne, String nom, String prenom, String type, String telephone, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, dateDebutP, dateFinP);
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
    }

    public Employe(Date dateNaissance, String securiteSociale, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, dateDebutP, dateFinP);
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
    }

    public Employe(Date dateNaissance, String securiteSociale, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, String mail, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, mail, dateDebutP, dateFinP);
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
    }

    public Employe(Date dateNaissance, String securiteSociale, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, String mail, String noteP, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, mail, noteP, dateDebutP, dateFinP);
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
    }

    public Employe(Date dateNaissance, String securiteSociale, String experience, String fonction, Timestamp dateDebutE, Timestamp dateFinE) {
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
        this.experience = experience;
        this.fonction = fonction;
        this.dateDebutE = dateDebutE;
        this.dateFinE = dateFinE;
    }

    public Employe(Date dateNaissance, String securiteSociale, String experience, String fonction, Timestamp dateDebutE, Timestamp dateFinE, Long idPersonne, String nom, String prenom, String type, String telephone, Timestamp dateDebutP) {
        super(idPersonne, nom, prenom, type, telephone, dateDebutP);
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
        this.experience = experience;
        this.fonction = fonction;
        this.dateDebutE = dateDebutE;
        this.dateFinE = dateFinE;
    }

    public Employe(Date dateNaissance, String securiteSociale, String experience, String fonction, Timestamp dateDebutE, Timestamp dateFinE, Long idPersonne, String nom, String prenom, String type, String telephone, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, dateDebutP, dateFinP);
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
        this.experience = experience;
        this.fonction = fonction;
        this.dateDebutE = dateDebutE;
        this.dateFinE = dateFinE;
    }

    public Employe(Date dateNaissance, String securiteSociale, String experience, String fonction, Timestamp dateDebutE, Timestamp dateFinE, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, dateDebutP, dateFinP);
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
        this.experience = experience;
        this.fonction = fonction;
        this.dateDebutE = dateDebutE;
        this.dateFinE = dateFinE;
    }

    public Employe(Date dateNaissance, String securiteSociale, String experience, String fonction, Timestamp dateDebutE, Timestamp dateFinE, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, String mail, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, mail, dateDebutP, dateFinP);
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
        this.experience = experience;
        this.fonction = fonction;
        this.dateDebutE = dateDebutE;
        this.dateFinE = dateFinE;
    }

    public Employe(Date dateNaissance, String securiteSociale, String experience, String fonction, Timestamp dateDebutE, Timestamp dateFinE, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, String mail, String noteP, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, mail, noteP, dateDebutP, dateFinP);
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
        this.experience = experience;
        this.fonction = fonction;
        this.dateDebutE = dateDebutE;
        this.dateFinE = dateFinE;
    }

    public Employe(Date dateNaissance, String securiteSociale, String experience, String fonction, String nationalite, String titreSejour, String permisTravail, String codeAcces, String equipeBase, Timestamp dateDebutE, Timestamp dateFinE) {
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
        this.experience = experience;
        this.fonction = fonction;
        this.nationalite = nationalite;
        this.titreSejour = titreSejour;
        this.permisTravail = permisTravail;
        this.codeAcces = codeAcces;
        this.equipeBase = equipeBase;
        this.dateDebutE = dateDebutE;
        this.dateFinE = dateFinE;
    }
        

    public Employe(Date dateNaissance, String securiteSociale, String experience, String fonction, String nationalite, String titreSejour, String permisTravail, String codeAcces, Timestamp dateDebutE, Timestamp dateFinE) {
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
        this.experience = experience;
        this.fonction = fonction;
        this.nationalite = nationalite;
        this.titreSejour = titreSejour;
        this.permisTravail = permisTravail;
        this.codeAcces = codeAcces;
        this.dateDebutE = dateDebutE;
        this.dateFinE = dateFinE;
    }

    public Employe(Date dateNaissance, String securiteSociale, String experience, String fonction, String nationalite, String titreSejour, String permisTravail, String codeAcces, Timestamp dateDebutE, Timestamp dateFinE, Long idPersonne, String nom, String prenom, String type, String telephone, Timestamp dateDebutP) {
        super(idPersonne, nom, prenom, type, telephone, dateDebutP);
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
        this.experience = experience;
        this.fonction = fonction;
        this.nationalite = nationalite;
        this.titreSejour = titreSejour;
        this.permisTravail = permisTravail;
        this.codeAcces = codeAcces;
        this.dateDebutE = dateDebutE;
        this.dateFinE = dateFinE;
    }

    public Employe(Date dateNaissance, String securiteSociale, String experience, String fonction, String nationalite, String titreSejour, String permisTravail, String codeAcces, Timestamp dateDebutE, Timestamp dateFinE, Long idPersonne, String nom, String prenom, String type, String telephone, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, dateDebutP, dateFinP);
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
        this.experience = experience;
        this.fonction = fonction;
        this.nationalite = nationalite;
        this.titreSejour = titreSejour;
        this.permisTravail = permisTravail;
        this.codeAcces = codeAcces;
        this.dateDebutE = dateDebutE;
        this.dateFinE = dateFinE;
    }

    public Employe(Date dateNaissance, String securiteSociale, String experience, String fonction, String nationalite, String titreSejour, String permisTravail, String codeAcces, Timestamp dateDebutE, Timestamp dateFinE, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, dateDebutP, dateFinP);
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
        this.experience = experience;
        this.fonction = fonction;
        this.nationalite = nationalite;
        this.titreSejour = titreSejour;
        this.permisTravail = permisTravail;
        this.codeAcces = codeAcces;
        this.dateDebutE = dateDebutE;
        this.dateFinE = dateFinE;
    }

    public Employe(Date dateNaissance, String securiteSociale, String experience, String fonction, String nationalite, String titreSejour, String permisTravail, String codeAcces, Timestamp dateDebutE, Timestamp dateFinE, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, String mail, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, mail, dateDebutP, dateFinP);
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
        this.experience = experience;
        this.fonction = fonction;
        this.nationalite = nationalite;
        this.titreSejour = titreSejour;
        this.permisTravail = permisTravail;
        this.codeAcces = codeAcces;
        this.dateDebutE = dateDebutE;
        this.dateFinE = dateFinE;
    }

    public Employe(Date dateNaissance, String securiteSociale, String experience, String fonction, String nationalite, String titreSejour, String permisTravail, String codeAcces, Timestamp dateDebutE, Timestamp dateFinE, Long idPersonne, String nom, String prenom, String type, String telephone, String mobile, String mail, String noteP, Timestamp dateDebutP, Timestamp dateFinP) {
        super(idPersonne, nom, prenom, type, telephone, mobile, mail, noteP, dateDebutP, dateFinP);
        this.dateNaissance = dateNaissance;
        this.securiteSociale = securiteSociale;
        this.experience = experience;
        this.fonction = fonction;
        this.nationalite = nationalite;
        this.titreSejour = titreSejour;
        this.permisTravail = permisTravail;
        this.codeAcces = codeAcces;
        this.dateDebutE = dateDebutE;
        this.dateFinE = dateFinE;
    }

//</editor-fold>         
    
//<editor-fold defaultstate="collapsed" desc="getter et setter ">    
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

    public String getEquipeBase() {
        return equipeBase;
    }

    public void setEquipeBase(String equipeBase) {
        this.equipeBase = equipeBase;
    }
    
//</editor-fold>     

@Override
public String toString(  ) {
        
        return super.toString() + "Employe{" + "dateNaissance=" + dateNaissance + 
                ", securiteSociale=" + securiteSociale + ", experience=" + 
                experience + ", fonction=" + fonction + ", nationalite=" + 
                nationalite + ", titreSejour=" + titreSejour + 
                ", permisTravail=" + permisTravail + ", codeAcces=" + 
                codeAcces + ", equipeBase=" + equipeBase + ", dateDebutE=" + 
                dateDebutE + ", dateFinE=" + dateFinE + '}';
    }    

//<editor-fold defaultstate="collapsed" desc="insertEmploye">
public String insertEmploye(Employe employe, Personne personne) {

    String msg = null;
    new Timestamp( employe.getDateDebutE().getTime());
    new Timestamp( employe.getDateFinE().getTime() );
    /*
    System.out.println("===== insertEmploye  EMPLOYE EN MISE A JOUR =========");
    System.out.println("employe.getDateNaissance():" + employe.getDateNaissance()  );
    System.out.println("employe.getExperience():" + employe.getExperience() );
    System.out.println("employe.getFonction():" + employe.getFonction() );
    System.out.println("employe.getSecuriteSociale():" + employe.getSecuriteSociale() );
    System.out.println("employe.getNationalite():" + employe.getNationalite() );
    System.out.println("employe.getTitreSejour():" + employe.getTitreSejour());
    System.out.println("employe.getPermisTravail():" + employe.getPermisTravail() );
    System.out.println("employe.idPersonne:" + employe.idPersonne );
    System.out.println("===== insertEmploye  FIN LISTE EMPLOYE EN MISE A JOUR =========");
    System.out.println("Employe : entree dans insertEmploye");    
    System.out.println("insertEmploye -->  employe.idPersonne :" + employe.idPersonne ) ;
    */
    String sql_update = ( "UPDATE PERSONNE  set DATENAISSEMPLOYE = ? " + " , "
                        + " ANEXPERIENCE = ? "  + " , "
                        + " FONCTIONEMPLOYE = ? " + " , "
                        + " NUMEROSSEMPLOYE = ? " + " , "
                        + " NATIONALITEEMPLOYE = ? " + " , "
                        + " TITRESEJOUREMPLOYE = ? " + " , "
                        + " PERMISTRAVAILEMPLOYE = ? " + " , "
                        + " CODEACCESEMPLOYE = ? " + " , "
                        + " EQUIPEBASEEMPLOYE = ? " + " , "
                        + " DCEMPLOYE = ? " + " , "
                        + " DFEMPLOYE = ? " 
                        + " WHERE  IDPERSONNE = ? " ) ; 
     
    System.out.println("insertEmploye: --> SQL_UPDATE : " + sql_update);
    employe.setCodeAcces("5878");
    
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
            System.out.println("Employe : connexion faite");
            PreparedStatement stmt = connexion.prepareStatement(sql_update);
            
           
            SimpleDateFormat formatDateJour = new SimpleDateFormat("yyyy-MM-dd"); 
            String dateFormatee = formatDateJour.format(employe.getDateNaissance());
            System.out.println("dateFormatee" + dateFormatee );
            
            
            stmt.setString(1, dateFormatee);
            stmt.setString(2,employe.getExperience());
            stmt.setString(3,employe.getFonction());
            stmt.setString(4,employe.getSecuriteSociale());
            stmt.setString(5,employe.getNationalite());
            stmt.setString(6,employe.getTitreSejour());
            stmt.setString(7,employe.getPermisTravail());
            stmt.setString(8,employe.getCodeAcces());
            stmt.setString(9,employe.getEquipeBase());
            stmt.setTimestamp(10,employe.getDateDebutE());
            stmt.setTimestamp(11,employe.getDateFinE());
            stmt.setLong(12,employe.idPersonne);
    
            
            stmt.executeUpdate();
            
            stmt.close();
            try {

                connexion.close();
            } catch (SQLException ex) {
                System.err.println("connexion close:" + ex.getMessage());
                msg = "Echec de la mise à jour dans le carnet.";
                return msg;
            }
            System.out.println("done !");
        } catch (SQLException ex) {
            System.err.println("SQl error : " + ex.getErrorCode() + "/"
                    + ex.getMessage());
        }

            msg = "Succès de la mise à jour de l'employé.";
            
    
    return msg;
}    
//</editor-fold>    
    
//<editor-fold defaultstate="collapsed" desc="lister()">    
public Map<Long, Employe> listerE(String type)   {
        String SQL_SELECT       = "SELECT IDPERSONNE, NOMPERSONNE, "
                                + " PRENOMPERSONNE, TYPEPERSONNE, "
                                + " TELPERSONNE, MOBPERSONNE, MAILPERSONNE, "
                                + " NOTEPERSONNE, DCPERSONNE, DFPERSONNE, "
                                + " DATENAISSEMPLOYE, ANEXPERIENCE, FONCTIONEMPLOYE, NUMEROSSEMPLOYE, " 
                                + " NATIONALITEEMPLOYE, TITRESEJOUREMPLOYE, PERMISTRAVAILEMPLOYE,  "
                                + " CODEACCESEMPLOYE, EQUIPEBASEEMPLOYE, DCEMPLOYE, DFEMPLOYE "                
                                + "  FROM PERSONNE WHERE TYPEPERSONNE = '"
                                + type + "'";
        
        
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<Long, Employe> employes = new HashMap<Long, Employe>();

        
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
            System.out.println("Employe select : connexion faite");
            preparedStatement = connexion.prepareStatement( SQL_SELECT );
             
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) { 
            //       personnes.add( map( resultSet ) );
                   employes.put( resultSet.getLong( "IDPERSONNE" ), map( resultSet ) );
                   
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

        
        return employes;         
   }
public Map<Long, Employe> listerE()   {
        String SQL_SELECT       = "SELECT IDPERSONNE, NOMPERSONNE, "
                                + " PRENOMPERSONNE, TYPEPERSONNE, "
                                + " TELPERSONNE, MOBPERSONNE, MAILPERSONNE, "
                                + " NOTEPERSONNE, DCPERSONNE, DFPERSONNE, "
                                + " DATENAISSEMPLOYE, ANEXPERIENCE, FONCTIONEMPLOYE, NUMEROSSEMPLOYE, " 
                                + " NATIONALITEEMPLOYE, TITRESEJOUREMPLOYE, PERMISTRAVAILEMPLOYE,  "
                                + " CODEACCESEMPLOYE, EQUIPEBASEEMPLOYE, DCEMPLOYE, DFEMPLOYE "                
                                + "  FROM PERSONNE ORDER BY IDPERSONNE" ;
                                
        
        
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<Long, Employe> employes = new HashMap<Long, Employe>();

        
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
            System.out.println("Employe select : connexion faite");
            preparedStatement = connexion.prepareStatement( SQL_SELECT );
             
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) { 
            //       personnes.add( map( resultSet ) );
                   employes.put( resultSet.getLong( "IDPERSONNE" ), map( resultSet ) );
                   
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

        
        return employes;         
   }   
public Employe listerE(Long id)   {
        String SQL_SELECT       = "SELECT IDPERSONNE, NOMPERSONNE, "
                                + " PRENOMPERSONNE, TYPEPERSONNE, "
                                + " TELPERSONNE, MOBPERSONNE, MAILPERSONNE, "
                                + " NOTEPERSONNE, DCPERSONNE, DFPERSONNE, "
                                + " DATENAISSEMPLOYE, ANEXPERIENCE, FONCTIONEMPLOYE, NUMEROSSEMPLOYE, " 
                                + " NATIONALITEEMPLOYE, TITRESEJOUREMPLOYE, PERMISTRAVAILEMPLOYE,  "
                                + " CODEACCESEMPLOYE, EQUIPEBASEEMPLOYE, DCEMPLOYE, DFEMPLOYE "                
                                + "  FROM PERSONNE WHERE IDPERSONNE = " + id ;
                                
        
        
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Employe employe = new Employe();

        
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
            System.out.println("Employe select : connexion faite");
            preparedStatement = connexion.prepareStatement( SQL_SELECT );
             
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) { 
            //       personnes.add( map( resultSet ) );
                   employe = map( resultSet );
                   
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

        
        return employe;         
   }    
public Map<Long, Employe> listerB(String equipeBase)   {
        String SQL_SELECT       = "SELECT IDPERSONNE, NOMPERSONNE, "
                                + " PRENOMPERSONNE, TYPEPERSONNE, "
                                + " TELPERSONNE, MOBPERSONNE, MAILPERSONNE, "
                                + " NOTEPERSONNE, DCPERSONNE, DFPERSONNE, "
                                + " DATENAISSEMPLOYE, ANEXPERIENCE, FONCTIONEMPLOYE, NUMEROSSEMPLOYE, " 
                                + " NATIONALITEEMPLOYE, TITRESEJOUREMPLOYE, PERMISTRAVAILEMPLOYE,  "
                                + " CODEACCESEMPLOYE, EQUIPEBASEEMPLOYE, DCEMPLOYE, DFEMPLOYE "                
                                + "  FROM PERSONNE WHERE EQUIPEBASEEMPLOYE = '"
                                + equipeBase + "'";
        
        System.out.println("listerB :" + SQL_SELECT);
        String FICHIER_PROPERTIES       = "/com/dl1/BDD/connection.properties";
        
        Properties properties = new Properties();
        String host = null;
        String uName = null;
        String uPass = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<Long, Employe> employes = new HashMap<Long, Employe>();

        
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
            System.out.println("Employe select : connexion faite");
            preparedStatement = connexion.prepareStatement( SQL_SELECT );
             
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) { 
            //       personnes.add( map( resultSet ) );
                   employes.put( resultSet.getLong( "IDPERSONNE" ), map( resultSet ) );
                   
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

        
        return employes;         
   }

//</editor-fold>    
    

//<editor-fold defaultstate="collapsed" desc="mapping resultSet pour Employe">
    private static Employe map(ResultSet resultSet) throws SQLException {
        Employe employe = new Employe();
        employe.setIdPersonne(resultSet.getLong( "IDPERSONNE" ) );
        employe.setNom( resultSet.getString( "NOMPERSONNE" ) );
        employe.setPrenom( resultSet.getString( "PRENOMPERSONNE" ) );
        employe.setType( resultSet.getString( "TYPEPERSONNE" ) );
        employe.setTelephone( resultSet.getString( "TELPERSONNE" ) );
        employe.setMobile( resultSet.getString( "MOBPERSONNE" ) );
        employe.setMail( resultSet.getString( "MAILPERSONNE" ) );
        employe.setNoteP( resultSet.getString( "NOTEPERSONNE" ) );
        employe.setDateDebutP(resultSet.getTimestamp( "DCPERSONNE" ) );
        employe.setDateFinP(resultSet.getTimestamp( "DFPERSONNE" ) );
        employe.setDateNaissance(resultSet.getDate( "DATENAISSEMPLOYE" ) );
        employe.setSecuriteSociale(resultSet.getString( "NUMEROSSEMPLOYE" ) );
        employe.setExperience(resultSet.getString( "ANEXPERIENCE" ) );
        employe.setFonction(resultSet.getString( "FONCTIONEMPLOYE" ) );
        employe.setNationalite(resultSet.getString( "NATIONALITEEMPLOYE" ) );
        employe.setTitreSejour(resultSet.getString( "TITRESEJOUREMPLOYE" ) );
        employe.setPermisTravail(resultSet.getString( "PERMISTRAVAILEMPLOYE" ) );
        employe.setCodeAcces(resultSet.getString( "CODEACCESEMPLOYE" ) );
        employe.setEquipeBase(resultSet.getString( "EQUIPEBASEEMPLOYE" ) );
        employe.setDateDebutE(resultSet.getTimestamp("DCEMPLOYE" ) );
        employe.setDateFinE(resultSet.getTimestamp("DFEMPLOYE" ) );
        
        return employe;
    }
//</editor-fold>
    

}