package sample;

import java.sql.*;

public class SQL {

    static String url = "jdbc:mysql://localhost:3306/login";
    static String user = "root";
    static String password = "1234";
    static Connection myConn;
    static Statement myStatement;

   // public static void main(String[] args) throws SQLException {
    //}

    // konstruktør der opretter forbindelse til databsen
    public SQL() {
        try {
            myConn = DriverManager.getConnection(url, user, password);
            myStatement = myConn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //metode til der samler oprettelse af tabeller og opdatering af patientliste.
    public static void createNewPatient(String CPR){
        writePatientListe(CPR);
        createTableCPREKG(CPR);
        createTableCPRPuls(CPR);
    }


    // Write metoden, som skriver CPR ind i patientlisten
    public static void writePatientListe(String CPR){
        String write_to_database1 = "INSERT INTO patientListe " + "(CPR) values(?);";
        PreparedStatement PP1;
        try {
            PP1 = myConn.prepareStatement(write_to_database1);
            PP1.setString(1, CPR);
            PP1.execute();
        } catch (SQLException throwables) {
            System.out.println("CPR eksisterer allerede i systemet.");
        }


    }
    // create metode som laver en tabel som indeholder tid, puls, temp og spo2.
    static public void createTableCPRPuls(String CPR) {
        String sql_CreateTable = "CREATE TABLE IF NOT EXISTS patientMaalingPuls" + CPR + "(\n"
                + "timeaxis INT PRIMARY KEY AUTO_INCREMENT,\n"
                + "PulsValue DOUBLE,\n"
                + "TEMPValue DOUBLE,\n"
                + "SpO2Value DOUBLE);";
        try {
            myStatement.execute(sql_CreateTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // create metode som laver en tabel som indeholder tid og ekg.
    static public void createTableCPREKG(String CPR) {
        String sql_CreateTable = "CREATE TABLE IF NOT EXISTS patientMaalingEKG" + CPR + "(\n"
                + "timeaxis INT PRIMARY KEY AUTO_INCREMENT,\n"
                + "EKGValue DOUBLE);";
        try {

            myStatement.execute(sql_CreateTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // write metode som skriver puls temp og spo2 ind i en tabel, som den identificere med CPR.
    public static void writeToPatientMaalingPuls(String CPR, double Puls, double Temp, double SpO2) {
            try {
                String write_to_database2 = "insert into patientMaalingPuls" + CPR + "(PulsValue, TEMPValue,SpO2Value) values(?, ?, ?)";
                PreparedStatement PP2 = myConn.prepareStatement(write_to_database2);

                PP2.setDouble(1, Puls);
                PP2.setDouble(2, Temp);
                PP2.setDouble(3, SpO2);

                PP2.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    //write metode som skriver ekg ind i en tabel, som den identificere med EKG.
    public static void writeToPatientMaalingEKG(String CPR, double EKG) {
        try {
            String write_to_database2 = "insert into patientMaalingEKG" + CPR + "(EKGValue) values(?)";
            PreparedStatement PP2 = myConn.prepareStatement(write_to_database2);

            PP2.setDouble(1, EKG);

            PP2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //row tæller
    static public int rowCounter(String Table, String CPR){
        String sql_Count = "SELECT COUNT(*) FROM "+ Table + CPR;
        ResultSet rs = null;
        try {
            rs = myStatement.executeQuery(sql_Count);
            rs.next();
            int count =rs.getInt(1);
            return count;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    // Read metode som læser data fra ekg tabel
    static public void readDataEKG(String CPR, int[] tid_array, double[] ekg_array) throws SQLException {
        String sql_SelectFrom = "SELECT * FROM login.patientMaalingEKG" + CPR;
        ResultSet rs = myStatement.executeQuery(sql_SelectFrom);
        int i = 0;
        while (rs.next()) {
            //System.out.print(rs.getInt("timeaxis"));
            //System.out.println(rs.getDouble("EKGValue"));
            tid_array[i] = rs.getInt("timeaxis");
            ekg_array[i] = rs.getDouble("EKGValue");

            i++;
        }
    }


    // read metode som læser data fra Puls tabel
    static public void readDataPuls(String CPR,int[] tid_array, double[] puls_array,double[] temp_array,double[] SpO2_array) throws SQLException {
        String sql_SelectFrom = "SELECT * FROM login.patientMaalingPuls"+ CPR;
        ResultSet rs = myStatement.executeQuery(sql_SelectFrom);
        int i=0;
        while (rs.next()) {
            tid_array[i] = rs.getInt("timeaxis");
            puls_array[i] = rs.getDouble("PulsValue");
            temp_array[i] = rs.getDouble("TEMPValue");
            SpO2_array[i] = rs.getDouble("SpO2Value");
            i++;
        }
    }

    // read metode som læser data fra logininfo
    static public void Read_data_logininfo(String CPR) throws SQLException {
        String sql_SelectFrom = "SELECT * FROM login.logininfo" + CPR;
        ResultSet rs = myStatement.executeQuery(sql_SelectFrom);

        while (rs.next()) {
            System.out.print(rs.getInt("id")+" ");
            System.out.print(rs.getString("username")+" ");
            System.out.print(rs.getString("password")+" ");
            System.out.println(rs.getInt("doctor"));
        }
    }

    // read metode som læser data fra patient liste
    static public void Read_data_patientliste(String CPR) throws SQLException {
        String sql_SelectFrom = "SELECT * FROM login.patientListe" + CPR;
        ResultSet rs = myStatement.executeQuery(sql_SelectFrom);

        while (rs.next()) {
            System.out.print(rs.getString("CPR")+" ");
        }
    }

    static public boolean doesPatientExsist(String CPR) throws SQLException {
        String findPatient = "SELECT CPR\n"
                +" FROM login.patientListe\n"
                +"WHERE EXISTS\n"
                +"(SELECT CPR FROM patientListe WHERE CPR="+CPR+");";
        PreparedStatement PP2 = myConn.prepareStatement(findPatient);
        return PP2.execute();
    }



}
