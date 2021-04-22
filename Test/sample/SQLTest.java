package sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SQLTest {
    SQL sql_ob = new SQL();
    GenMetoder gm = new GenMetoder();
    String  CPR;
    double Puls, Temp, SpO2, EKG;

    @BeforeEach
    void setUp() {
        CPR="2103005445";
    }

    @Test
    void writePatientListeTest() {
        sql_ob.writePatientListe(CPR);
        CPR="1234500000";
        sql_ob.writePatientListe(CPR);
    }

    @Test
    void createTableCPRPulsTest() {
        CPR="2103005000";
        sql_ob.createTableCPRPuls(CPR);
    }

    @Test
    void createTableCPREKGTest() {
        CPR="1234321567";
        sql_ob.createTableCPREKG(CPR);
    }

    @Test
    void writeToPatientMaalingPulsTest() {
        CPR = "2103005000";
        Temp=37;
        Puls=67;
        SpO2=99;
        sql_ob.writeToPatientMaalingPuls(CPR,Puls,Temp,SpO2);

    }

    @Test
    void writeToPatientMaalingEKGTest() {
        CPR="1234321567";
        EKG = 23;
        sql_ob.writeToPatientMaalingEKG(CPR,EKG);
    }

    @Test
    void rowCounterTest() {
        CPR="1234321567";
        int[] row;
        row = new int[sql_ob.rowCounter("patientMaalingEKG",CPR)];
        for(int i =0; i<row.length; i++) {
            System.out.println(i + 1);
        }

    }

    @Test
    void readDataEKGTest() {
        CPR="1234321567";
        try {
            sql_ob.readDataEKG(CPR,gm.EKGTime,gm.EKGValue);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void readDataPulsTest() {
        try {
            sql_ob.readDataPuls(CPR, gm.PulseTime, gm.PulseValue, gm.TempValue, gm.SpO2Value);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void readDataLogininfoTest() {
        sql_ob.ReadDataLogininfo("DR");

    }

    @Test
    void doesPatientExsistTest() {
        CPR = "IDontExsist";
        Assertions.assertFalse(sql_ob.doesPatientExsist(CPR));
        CPR = "1111111111";
        Assertions.assertTrue(sql_ob.doesPatientExsist(CPR));

    }
}