/*DROP DATABASE login;
CREATE DATABASE login;
*/USE login;/*


CREATE TABLE loginInfo(
id INT NOT NULCPRCPRCPRL AUTO_INCREMENT,
username VARCHAR(255) UNIQUE NOT NULL,
password VARCHAR(255) NOT NULL,
doctor INT NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE patientListe(
CPR VARCHAR(10) NOT NULL UNIQUE,
PRIMARY KEY(CPR)
);

CREATE TABLE patientMaalingCPR(
timeaxis INT NOT NULL,
PulsValue DOUBLE NOT NULL,
EKGValue DOUBLE NOT NULL,
TEMPValue DOUBLE NOT NULL,
SpO2Value DOUBLE NOT NULL,
PRIMARY KEY(timeaxis)
);

INSERT INTO loginInfo(username,password,doctor)
VALUES  ('DR','password',1),
		('nurse','password',0);
        
        INSERT INTO patientListe(CPR)
VALUES  ('1234567890'),
		('1111111111');
        
        INSERT INTO patientMaalingCPR(timeaxis,PulsValue,EKGValue,TEMPValue,SpO2Value)
VALUES  (1,66,100,37.5,98.5),
		(2,65,90,37.4,98.4);*/
        
SELECT * FROM patientmaaling2222222223;

        
        

