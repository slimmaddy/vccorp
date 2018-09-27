package com.mycompany.app;

/**
 * Hello world!
 *
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class App {

    private static final String DB_DRIVER = "jdbc:mysql://localhost/";
    private static final String DB_NAME = "employees";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1";

    public static void main(String[] argv) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatementInsert = null;
        PreparedStatement preparedStatementUpdate = null;

        String insertTableSQL = "INSERT INTO titles (emp_no, title, from_date, to_date) VALUES (?, 'Senior Staff', curdate(), '9999-1-1')";
        String updateTableSQL = "UPDATE titles SET to_date = curdate() WHERE emp_no=?";

        try {
            dbConnection = getDBConnection();

            dbConnection.setAutoCommit(false);

            preparedStatementUpdate = dbConnection.prepareStatement(updateTableSQL);
            preparedStatementUpdate.setInt(1, 10002);
            preparedStatementUpdate.executeUpdate();

            preparedStatementInsert = dbConnection.prepareStatement(insertTableSQL);
            preparedStatementInsert.setInt(1, 10002);

            preparedStatementInsert.executeUpdate();


            dbConnection.commit();

            System.out.println("Done!");

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            dbConnection.rollback();

        } finally {

            if (preparedStatementInsert != null) {
                preparedStatementInsert.close();
            }

            if (preparedStatementUpdate != null) {
                preparedStatementUpdate.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }

    }

    private static Connection getDBConnection() {


        try {
            return DriverManager.getConnection(DB_DRIVER+DB_NAME+"?user="+DB_USER+"&password="+DB_PASSWORD);


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }


        return null;

    }

    private static java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

}
