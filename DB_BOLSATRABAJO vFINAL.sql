USE master
GO

CREATE DATABASE BD_BOLSATRABAJO
GO

USE BD_BOLSATRABAJO
GO

CREATE TABLE CANDIDATO(
SEMAIL CHAR(50) PRIMARY KEY,
SPASSWORD CHAR(30),
SDOCUMENTO VARCHAR(20),
SNOMBRE VARCHAR(30),
SAPELLIDOS VARCHAR(60),
SFECNACIMIENTO VARCHAR(15),
SGENERO VARCHAR(1),
NANOSEXPERIENCIA INT,
SESPECIALIDAD VARCHAR(50)
)
GO



INSERT CANDIDATO VALUES('jcarlos1210@outlook.com','5538511','73129639','Jean Carlos','Garcia Changano','12/10/1996','M',5,'SISTEMAS')
 INSERT INTO CANDIDATO VALUES ('juanandrade@gmail.com','123456789','25732215','Juan','Andrade','01/01/96','M',5,'SISTEMAS')
 INSERT INTO CANDIDATO VALUES ('jeanrobles@gmail.com','123456789','09103562','Jean','Robles','02/02/97','M',1,'ECONOMIA')
 INSERT INTO CANDIDATO VALUES ('mariapanfila@gmail.com','123456789','25423232','Maria','Panfila','03/03/98','F',10,'EDUCACION')
 INSERT INTO CANDIDATO VALUES ('kennygallager@gmail.com','123456789','25724393','Kenny','Gallager','05/04/94','M',2,'SISTEMAS')
 INSERT INTO CANDIDATO VALUES ('matiasramos@gmail.com','123456789','68459422','Matias','Ramos','10/05/99','M',3,'ECONOMIA')
 INSERT INTO CANDIDATO VALUES ('renatocueva@gmail.com','123456789','28459516','Renato','Cueva','15/06/92','M',5,'SISTEMAS')
 INSERT INTO CANDIDATO VALUES ('nicolasramirez@gmail.com','123456789','65498725','Nicolas','Ramirez','19/08/90','M',5,'SISTEMAS')
 INSERT INTO CANDIDATO VALUES ('irinaria@gmail.com','123456789','95486533','Irina','Ria','16/09/95','F',6,'SISTEMAS')
 INSERT INTO CANDIDATO VALUES ('patriciomanri@gmail.com','123456789','01589546','Patricio','Manrique','20/10/96','M',1,'ECONOMIA')
 INSERT INTO CANDIDATO VALUES ('pedroandrade@gmail.com','123456789','02356845','Pedro','Andrade','24/11/94','M',2,'ECONOMIA')
 INSERT INTO CANDIDATO VALUES ('francomatos@gmail.com','123456789','95685232','Franco','Matos','26/12/98','M',3,'EDUCACION')
 INSERT INTO CANDIDATO VALUES ('nellycasa@gmail.com','123456789','16848984','Nelly','Casa','23/11/90','F',6,'EDUCACION')
 INSERT INTO CANDIDATO VALUES ('robinclayce@gmail.com','123456789','98224565','Robin','Clayce','11/10/95','M',1,'SIECONOMIASTEMAS')
 INSERT INTO CANDIDATO VALUES ('ronymolly@gmail.com','123456789','20351564','Ronny','Molly','15/06/96','M',2,'SISTEMAS')
  GO
 
CREATE TABLE EMPRESA(
SEMAIL CHAR(50) PRIMARY KEY,
SPASSWORD CHAR(30),
SRAZSOCIAL VARCHAR(100),
SRUC CHAR(20),
SINDUSTRIA VARCHAR(50) --INT REFERENCES INDUSTRIA
)
GO
INSERT INTO EMPRESA VALUES ('tconsulting@tconsulting.com','123456789','Transaction Consulting SA','0730145008175','Consultoria')
INSERT INTO EMPRESA VALUES ('fygsa@gmail.com','123456789','F&G SAC','07301703458175','Tecnologia Software')
INSERT INTO EMPRESA VALUES ('candystore@hotmail.com','123456789','Candy Store SA','07301705608175','Alimentación')
INSERT INTO EMPRESA VALUES ('tremolosong@gmail.com','123456789','Tremolo Song SA','0730175608175','Música')
INSERT INTO EMPRESA VALUES ('parapentes@parapentesperu.com','123456789','Parapentes Lucy SA','0730170005175','Entretenimiento')


GO

CREATE TABLE OFERTA_TRABAJO(
NIDOFERTA INT IDENTITY(1,1) PRIMARY KEY,
SOFERTA VARCHAR(100),
NSALARIO INT,
SDESOFERTA VARCHAR(800),
SEMAIL CHAR(50) REFERENCES EMPRESA
)
GO

INSERT INTO OFERTA_TRABAJO VALUES ('Se necesita trabajador para servicios de mensajeria',500,'El trabajo con una remuneración justa te brindará las facilidades para tu desplazo y servicios basicos como comida','candystore@hotmail.com')
INSERT INTO OFERTA_TRABAJO VALUES ('Se necesita trabajador programador',500,' se necesita programador con experiencia en sistemas IOS y conocimiento basico en swift ','fygsa@gmail.com')
INSERT INTO OFERTA_TRABAJO VALUES ('Se necesita trabajador joven de 20',1000,'se necesita joven animado y con ganas de progresar para trabajo de 15 horas y sin permisos especiales, el contrato es por 50 años','parapentes@parapentesperu.com')
 
 INSERT INTO OFERTA_TRABAJO VALUES ('Necesitamos analistas',1000,'se necesita joven animado y con ganas de progresar para trabajo de 15 horas y sin permisos especiales, el contrato es por 50 años','parapentes@parapentesperu.com')
 INSERT INTO OFERTA_TRABAJO VALUES ('Buscamos joven proactivo programador',1000,'se necesita joven animado y con ganas de progresar para trabajo de 15 horas y sin permisos especiales, el contrato es por 50 años','parapentes@parapentesperu.com')
 INSERT INTO OFERTA_TRABAJO VALUES ('Practicante de sistemas',800,'se necesita joven animado y con ganas de progresar para trabajo de 15 horas y sin permisos especiales, el contrato es por 50 años','parapentes@parapentesperu.com')
 
INSERT INTO OFERTA_TRABAJO VALUES ('Practicante de gestor de proyectos',800,'se necesita joven animado y con ganas de progresar para trabajo de 15 horas y sin permisos especiales, el contrato es por 50 años','parapentes@parapentesperu.com')
 INSERT INTO OFERTA_TRABAJO VALUES ('Practicante pre profesional de economía',1000,'se necesita joven animado y con ganas de progresar para trabajo de 15 horas y sin permisos especiales, el contrato es por 50 años','parapentes@parapentesperu.com')
 
 INSERT INTO OFERTA_TRABAJO VALUES ('Buscamos practicantes de soporte',1500,'se necesita joven animado y con ganas de progresar para trabajo de 15 horas y sin permisos especiales, el contrato es por 50 años','fygsa@gmail.com')

 INSERT INTO OFERTA_TRABAJO VALUES ('Importante empresa busca practicante de sistemas',900,'se necesita joven animado y con ganas de progresar para trabajo de 15 horas y sin permisos especiales, el contrato es por 50 años','tremolosong@gmail.com')
 INSERT INTO OFERTA_TRABAJO VALUES ('Importante empresa busca DBA tecnico o universitario',2000,'se necesita joven animado y con ganas de progresar para trabajo de 15 horas y sin permisos especiales, el contrato es por 50 años','tremolosong@gmail.com')

CREATE TABLE SOLICITUD(
NIDSOLICITUD INT IDENTITY(1,1) PRIMARY KEY,
SEMAIL CHAR(50) REFERENCES CANDIDATO,
NIDOFERTA INT REFERENCES OFERTA_TRABAJO
)
GO

INSERT INTO SOLICITUD VALUES ('juanandrade@gmail.com',2)
GO

INSERT INTO SOLICITUD VALUES ('nellycasa@gmail.com',2)
GO

INSERT INTO SOLICITUD VALUES ('patriciomanri@gmail.com',2)
GO

INSERT INTO SOLICITUD VALUES ('nicolasramirez@gmail.com',2)
GO

INSERT INTO SOLICITUD VALUES ('kennygallager@gmail.com',1)
GO

INSERT INTO SOLICITUD VALUES ('patriciomanri@gmail.com',1)
GO

INSERT INTO SOLICITUD VALUES ('patriciomanri@gmail.com',3)
GO

INSERT INTO SOLICITUD VALUES ('nellycasa@gmail.com',4)
GO

INSERT INTO SOLICITUD VALUES ('francomatos@gmail.com',4)
GO

INSERT INTO SOLICITUD VALUES ('francomatos@gmail.com',9)
GO

INSERT INTO SOLICITUD VALUES ('francomatos@gmail.com',10)
GO


CREATE PROC USP_LOGIN_CANDIDATO
@PSEMAIL CHAR(50),
@PSPASSWORD CHAR(30)
AS
IF EXISTS (SELECT * FROM CANDIDATO WHERE SEMAIL = @PSEMAIL AND SPASSWORD=@PSPASSWORD)
BEGIN
	SELECT * FROM CANDIDATO WHERE SEMAIL = @PSEMAIL
END
ELSE
BEGIN
	PRINT 'NO EXISTE'
END
Go

CREATE PROC USP_LOGIN_EMPRESA
@PSEMAIL CHAR(50),
@PSPASSWORD CHAR(30)
AS
IF EXISTS (SELECT * FROM EMPRESA WHERE SEMAIL = @PSEMAIL AND SPASSWORD=@PSPASSWORD)
BEGIN
	SELECT * FROM EMPRESA WHERE SEMAIL = @PSEMAIL
END
ELSE
	BEGIN
		PRINT 'NO EXISTE'
	END


