package sample;

import org.junit.jupiter.api.*;

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

    @AfterEach
    void next(){
        sql_ob.removeConnectionSQL();
        System.out.println();
    }

    @Test
    void writePatientListeTest() {
        sql_ob.makeConnectionSQL();
        sql_ob.writePatientListe(CPR);
        CPR="";
        sql_ob.writePatientListe(CPR);

    }

    @Test
    void createTableCPRPulsTest() {
        sql_ob.makeConnectionSQL();
        CPR="2103005000";
        sql_ob.createTableCPRPuls(CPR);
    }

    @Test
    void createTableCPREKGTest() {
        sql_ob.makeConnectionSQL();
        CPR="1234321567";
        sql_ob.createTableCPREKG(CPR);
    }

    @Test
    void writeToPatientMaalingPulsTest() {
        CPR="2103005000";
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
        CPR = "1234321567";
        int[] row;
        row = new int[sql_ob.rowCounter("patientMaalingEKG", CPR)];
        for (int i = 0; i < row.length; i++) {
            System.out.println(i + 1);
        }
    }

    @Test
    void doesPatientExsistTest() {
        CPR = "IDontExsist";
        Assertions.assertFalse(sql_ob.doesPatientExsist(CPR));
        CPR = "1111111111";
        Assertions.assertTrue(sql_ob.doesPatientExsist(CPR));
    }

    // har udført afprøvning på ovenstående metoder, mangler read metoderne.

    @Test
    void readDataEKGTest() {
        CPR="eksistere ikke";
       int[] tid_array1 = new int[500];
       double[] ekg_array1 = new double[500];
        try {
            sql_ob.readDataEKG(CPR,tid_array1,ekg_array1);
        } catch (SQLException throwables) {
            System.out.println("test blev fanget");
        }
        CPR="1234321567";
        int[] tid_array2 = new int[500];
        double[] ekg_array2 = new double[500];
        try {
            sql_ob.readDataEKG(CPR,tid_array2,ekg_array2);
            System.out.println("test bestod");
        } catch (SQLException throwables) {
            System.out.println("test fejlede");
        }
    }

    @Test
    void readDataPulsTest() {
        CPR="2103005000";
        int[] tid_array = new int[500];
        double[] pulsvalue = new double[500];
        double[] tempvalue = new double[500];
        double[] spo2value = new double[500];
        try {
            sql_ob.readDataPuls(CPR, tid_array, pulsvalue, tempvalue, spo2value);
            System.out.println("testen bestod");
        } catch (SQLException throwables) {
            System.out.println("Testen fejlede");
        }

        CPR="2103005000";
        try {
            sql_ob.readDataPuls(CPR, tid_array, pulsvalue, tempvalue, spo2value);
        } catch (SQLException throwables) {
            System.out.println("testen bestod");
        }
    }

    @Test
    void readDataLogininfoTest() {
        sql_ob.ReadDataLogininfo("DR");

    }


}