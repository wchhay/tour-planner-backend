package at.technikum.tourplannerbackend.database;

import javax.management.Query;
import java.sql.*;
import java.util.List;

public class Config implements DAO {

    private final static String url = "jdbc:postgresql://localhost/TourPlanner";
    private final static String user = "postgres";
    private final static String password = "kat88r";

    public static void main(String[] args) {
        DAO dao = new Config();
    //    System.out.println(function());

    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }






}
