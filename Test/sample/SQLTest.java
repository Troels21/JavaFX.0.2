package sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SQLTest {
    SQL sql_ob = new SQL();
    String CPR;

    @BeforeEach
    void setUp() {
        ;
        CPR="";
    }

    @Test
    void writePatientListeTest() {
        sql_ob.writePatientListe(CPR);
        CPR="1234512345";
        sql_ob.writePatientListe(CPR);
    }

    @Test
    void createTableCPRPulsTest() {
    }

    @Test
    void createTableCPREKGTest() {
    }

    @Test
    void writeToPatientMaalingPulsTest() {
    }

    @Test
    void writeToPatientMaalingEKGTest() {
    }

    @Test
    void rowCounterTest() {
    }

    @Test
    void readDataEKGTest() {
    }

    @Test
    void readDataPulsTest() {
    }

    @Test
    void readDataLogininfoTest() {
    }

    @Test
    void doesPatientExsistTest() {
        CPR = "IDontExsist";
        Assertions.assertFalse(sql_ob.doesPatientExsist(CPR));
        CPR = "1111111111";
        Assertions.assertTrue(sql_ob.doesPatientExsist("1111111111"));

    }
}