  /*==============================================================*/
/* Nom de SGBD :  DERBY                                     */
/* Date de création :  17/02/2015 18:29:27                      */
/*==============================================================*/


/*==============================================================*/
/* Table : ADRESSE                                              */
/*==============================================================*/
CREATE TABLE ADRESSE
(
   IDADRESSE            int GENERATED ALWAYS AS IDENTITY,
   LIGNE1ADRESSE        VARCHAR(100) NOT NULL,
   LIGNE2ADRESSE        VARCHAR(100),
   CPADRESSE            CHAR(5) NOT NULL,
   VILLEADRESSE         VARCHAR(100) NOT NULL,
   PAYSADRESSE          VARCHAR(100),
   DCADRESSE            TIMESTAMP NOT NULL, 
   DFADRESSE            TIMESTAMP NOT NULL, 
   PRIMARY KEY (IDADRESSE)
);

/*==============================================================*/
/* Table : PERSONNE                                             */
/*==============================================================*/
CREATE TABLE PERSONNE
(
   IDPERSONNE           		int GENERATED ALWAYS AS IDENTITY,
   NOMPERSONNE          		VARCHAR(50) NOT NULL,
   PRENOMPERSONNE       		VARCHAR(50) NOT NULL,
   TYPEPERSONNE         		CHAR(12)  NOT NULL, 
   TELPERSONNE          		CHAR(10) NOT NULL,
   MOBPERSONNE          		CHAR(10),
   MAILPERSONNE         		VARCHAR(250),
   NOTEPERSONNE         		VARCHAR(500),
   DCPERSONNE            		TIMESTAMP NOT NULL, 
   DFPERSONNE            		TIMESTAMP NOT NULL,
   DATENAISSEMPLOYE     		DATE ,
   ANEXPERIENCE         		CHAR(8),
   FONCTIONEMPLOYE      		VARCHAR(100),
   NUMEROSSEMPLOYE      		CHAR(18),
   NATIONALITEEMPLOYE   		VARCHAR(30),
   TITRESEJOUREMPLOYE   		CHAR(15),
   PERMISTRAVAILEMPLOYE 		CHAR(20),
   CODEACCESEMPLOYE     		CHAR(4) ,
   EQUIPEBASEEMPLOYE                    CHAR(6),
   DCEMPLOYE            		TIMESTAMP , 
   DFEMPLOYE            		TIMESTAMP ,
   TYPECLIENT                           CHAR(12)  , 
   FAXCLIENT                  		CHAR(10),
   AUTRETELCLIENT         		CHAR(10),
   NOTECLIENT                		VARCHAR(500),
   DCCLIENT                      	TIMESTAMP , 
   DFCLIENT                     	TIMESTAMP ,
   PRIMARY KEY (IDPERSONNE)
);



/*==============================================================*/
/* Table : CHANTIER                                             */
/*==============================================================*/
CREATE TABLE CHANTIER
(
   IDCHANTIER              int GENERATED ALWAYS AS IDENTITY,
   IDPERSONNE       	   int not null,
   IDADRESSE               int not null,
   NOMCHANTIER             VARCHAR(100) NOT NULL,
   NBRJOURDEVISCHANTIER    SMALLINT NOT NULL,
   NBRJOURREALISECHANTIER  SMALLINT ,
   MTINITIALDEVISCHANTIER  DECIMAL(8,2) NOT NULL,
   MTTOTALREALISECHANTIER  DECIMAL(8,2) ,
   STATUTCHANTIER          CHAR(15) ,
   DATEDEBPREVUCHANTIER    DATE NOT NULL,
   DATEDEBCHANTIER         DATE  ,
   DATEFINPREVUCHANTIERR   DATE  ,
   DATEFINCHANTIER         DATE  ,
   DCCHANTIER            TIMESTAMP NOT NULL, 
   DFCHANTIER            TIMESTAMP NOT NULL,
   PRIMARY KEY (IDCHANTIER),
   CONSTRAINT FK_POSSEDE FOREIGN KEY (IDPERSONNE)
      REFERENCES PERSONNE (IDPERSONNE) ON DELETE RESTRICT ON UPDATE RESTRICT,
   CONSTRAINT FK_EST_SITUE FOREIGN KEY (IDADRESSE)
      REFERENCES ADRESSE (IDADRESSE) ON DELETE RESTRICT ON UPDATE RESTRICT
);


/*==============================================================*/
/* Table : CONTRAT                                              */
/*==============================================================*/
CREATE TABLE CONTRAT
(
   IDCONTRAT            int GENERATED ALWAYS AS IDENTITY,
   IDPERSONNE            int not null,
   POSTECONTRAT         VARCHAR(100),
   QUALIFCONTRAT        VARCHAR(100),
   REMUNERATIONHORAIRECONTRAT DECIMAL(5,2) NOT NULL,
   DATEDEBUTCONTRATT    DATE NOT NULL,
   DATEFINCONTRAT       DATE,
   DCCONTRAT            TIMESTAMP NOT NULL, 
   DFCONTRAT            TIMESTAMP NOT NULL,
   PRIMARY KEY (IDCONTRAT),
   CONSTRAINT FK_SIGNE FOREIGN KEY (IDPERSONNE)
      REFERENCES PERSONNE (IDPERSONNE) ON DELETE RESTRICT ON UPDATE RESTRICT
);

/*==============================================================*/
/* Table : RESIDE                                               */
/*==============================================================*/
CREATE TABLE RESIDE
(
   IDRESIDE             int GENERATED ALWAYS AS IDENTITY,
   IDPERSONNE           int not null,
   IDADRESSE            int not null,
   DCRESIDE            TIMESTAMP NOT NULL, 
   DFRESIDE            TIMESTAMP NOT NULL,
   PRIMARY KEY (IDRESIDE),
   CONSTRAINT FK_RESIDE FOREIGN KEY (IDPERSONNE)
      REFERENCES PERSONNE (IDPERSONNE) ON DELETE RESTRICT ON UPDATE RESTRICT,
   CONSTRAINT FK_RESIDE2 FOREIGN KEY (IDADRESSE)
      REFERENCES ADRESSE (IDADRESSE) ON DELETE RESTRICT ON UPDATE RESTRICT
);

/*==============================================================*/
/* Table : TRAVAILLE                                            */
/*==============================================================*/
CREATE TABLE TRAVAILLE
(
   IDTRAVAILLE          int GENERATED ALWAYS AS IDENTITY,
   IDPERSONNE            int not null,
   IDCHANTIER           int not null,
   DATEPLANNING         DATE NOT NULL,
   PRESENCEAMPLANNING   BOOLEAN NOT NULL,
   PRESENCEPMPLANNING   BOOLEAN NOT NULL,
   DCTRAVAILLE            TIMESTAMP NOT NULL, 
   DFTRAVAILLE            TIMESTAMP NOT NULL,
   PRIMARY KEY (IDTRAVAILLE),
   CONSTRAINT FK_TRAVAILLE FOREIGN KEY (IDPERSONNE)
      REFERENCES PERSONNE (IDPERSONNE) ON DELETE RESTRICT ON UPDATE RESTRICT,
   CONSTRAINT FK_TRAVAILLE2 FOREIGN KEY (IDCHANTIER)
      REFERENCES CHANTIER (IDCHANTIER) ON DELETE RESTRICT ON UPDATE RESTRICT
);

