package sample;

import java.sql.*;

public class SQL {
    package com.company;
import java.sql.*;

    public class Main {
        static String url = "jdbc:mysql://localhost:3306/login";
        static String user = "root";
        static String password = "";
        static Connection myConn;
        static Statement myStatement;

        static {
            try {
                myConn = DriverManager.getConnection(url,user,password);
                myStatement = myConn.createStatement();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        public static void main(String[] args) throws SQLException {
            String CPR="2222222223";
            createTableCPR(CPR);
            writeToPatientMaaling(CPR,66,100,37.5,98.5);
            selectFrom("patientMaaling",CPR,5);
            System.out.println();
            selectFrom("loginInfo","",4);
            System.out.println();
            selectFrom("patientListe","",1);
        }

        static public void createTableCPR(String CPR){
            String write_to_database1 ="insert into patientListe"+"(CPR) values(?);";
            String sql_CreateTable = "CREATE TABLE IF NOT EXISTS patientMaaling"+CPR+"(\n"
                    + "timeaxis INT PRIMARY KEY AUTO_INCREMENT,\n"
                    + "PulsValue DOUBLE,\n"
                    + "EKGValue DOUBLE,\n"
                    + "TEMPValue DOUBLE,\n"
                    + "SpO2Value DOUBLE);";
            try {
                PreparedStatement PP1 = myConn.prepareStatement(write_to_database1);

                PP1.setString(1, CPR);

                PP1.execute();

                myStatement.execute(sql_CreateTable);

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        public static void writeToPatientMaaling(String CPR, double Puls, double EKG, double Temp, double SpO2){
            try {
                String write_to_database2 ="insert into patientMaaling"+CPR+"(PulsValue, EKGValue, TEMPValue,SpO2Value) values(?, ?, ?, ?);";
                PreparedStatement PP2 = myConn.prepareStatement(write_to_database2);

                PP2.setDouble(1, Puls);
                PP2.setDouble(2, EKG);
                PP2.setDouble(3, Temp);
                PP2.setDouble(4, SpO2);

                PP2.execute();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        public static void writeToPatientMaaling(String CPR, double Puls, double EKG, double Temp, double SpO2){
            try {
                String write_to_database2 ="insert into patientMaaling"+CPR+"(PulsValue, EKGValue, TEMPValue,SpO2Value) values(?, ?, ?, ?);";
                PreparedStatement PP2 = myConn.prepareStatement(write_to_database2);

                PP2.setDouble(1, Puls);
                PP2.setDouble(2, EKG);
                PP2.setDouble(3, Temp);
                PP2.setDouble(4, SpO2);

                PP2.execute();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        static public void selectFrom(String Table,String CPR,int Columnlenght) throws SQLException {
            String sql_SelectFrom="SELECT * FROM login."+Table+CPR;
            ResultSet rs= myStatement.executeQuery(sql_SelectFrom);
            while (rs.next()){
                if (Columnlenght==1){
                    System.out.println(rs.getString(1));
                }
                if (Columnlenght==2){
                    System.out.println(rs.getString(1)+" "+rs.getString(2));
                }
                if (Columnlenght==3){
                    System.out.println(rs.getString(1)+" "+rs.getString(2)+" "
                            +rs.getString(3));
                }
                if (Columnlenght==4){
                    System.out.println(rs.getString(1)+" "+rs.getString(2)+" "
                            +rs.getString(3)+" "+ rs.getString(4));
                }
                if (Columnlenght==5){
                    System.out.println(rs.getString(1)+" "+rs.getString(2)+" "
                            +rs.getString(3)+" "+ rs.getString(4)+" "+rs.getString(5));
                }

            }
        }
    }

}
