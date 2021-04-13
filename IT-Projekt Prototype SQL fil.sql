/DROP DATABASE login;
CREATE DATABASE login;/
USE login;

/*
CREATE TABLE loginInfo(
id INT NOT NULL AUTO_INCREMENT,
username VARCHAR(255) UNIQUE NOT NULL,
password VARCHAR(255) NOT NULL,
doctor INT NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE patientListe(
CPR VARCHAR(10) NOT NULL UNIQUE,
PRIMARY KEY(CPR)
);

CREATE TABLE patientMaalingPulsCPR(
timeaxis INT NOT NULL,
PulsValue DOUBLE NOT NULL,
TEMPValue DOUBLE NOT NULL,
SpO2Value DOUBLE NOT NULL,
PRIMARY KEY(timeaxis)
);

CREATE TABLE patientMaalingEKGCPR(
timeaxis INT NOT NULL,
EKGValue DOUBLE NOT NULL,
PRIMARY KEY(timeaxis)
);

INSERT INTO loginInfo(username,password,doctor)
VALUES  ('DR','password',1),
        ('nurse','password',0);

INSERT INTO patientListe(CPR)
VALUES  ('1234567890'),
        ('1111111111');

INSERT INTO patientMaalingPulsCPR(timeaxis,PulsValue,TEMPValue,SpO2Value)
VALUES  (1,66,37.5,98.5),
        (2,65,37.4,98.4);

INSERT INTO patientMaalingEKGCPR(timeaxis,EKGValue)
VALUES  (1,100),
        (2,50);
        /
    /DELETE FROM patientListe WHERE CPR='2222222222';*/
    SELECT * FROM patientListe;
        
        

