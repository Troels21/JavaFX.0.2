package sample;

import java.sql.*;

public class SQL {

    static String url = "jdbc:mysql://localhost:3306/login";
    static String user = "root";
    static String password = "1234";
    static Connection myConn;
    static Statement myStatement;

    static {
        try {
            myConn = DriverManager.getConnection(url, user, password);
            myStatement = myConn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        String CPR = "2222222222";
        writePatientListe(CPR);
        createTableCPREKG(CPR);
        createTableCPRPuls(CPR);
        writeToPatientMaalingPuls(CPR, 66,  37.5, 98.5);
        writeToPatientMaalingEKG(CPR,100);
        selectFrom("patientMaalingPuls", CPR, 4);
        System.out.println();
        selectFrom("patientMaalingEKG",CPR,2);
        System.out.println();
        selectFrom("loginInfo", "", 4);
        System.out.println();
        selectFrom("patientListe", "", 1);
    }

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



    static public void selectFrom(String Table, String CPR, int Columnlenght) throws SQLException {
        String sql_SelectFrom = "SELECT * FROM login." + Table + CPR;
        ResultSet rs = myStatement.executeQuery(sql_SelectFrom);
        while (rs.next()) {
            if (Columnlenght == 1) {
                System.out.println(rs.getString(1));
            }
            if (Columnlenght == 2) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
            if (Columnlenght == 3) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " "
                        + rs.getString(3));
            }
            if (Columnlenght == 4) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " "
                        + rs.getString(3) + " " + rs.getString(4));
            }
            if (Columnlenght == 5) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " "
                        + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
            }

        }
    }
}
