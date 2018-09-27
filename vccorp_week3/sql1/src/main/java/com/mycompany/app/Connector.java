package com.mycompany.app;
import java.sql.*;

public class Connector {
    private static final String DB_DRIVER = "jdbc:mysql://localhost/";
    private static final String DB_NAME = "employees";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1";
    private Connection conn = null;


    private Connection getDBConnection() {
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
    public boolean executeBai4(String emp_name, String dept_name, String title){
        conn = getDBConnection();
        CallableStatement cStmt= null;
        try {
            cStmt = conn.prepareCall("{call bai4(?, ?, ?)}");
            cStmt.setString(1,emp_name);
            cStmt.setString(2,dept_name);
            cStmt.setString(3,title);
            boolean hadResults = cStmt.execute();
            while (hadResults) {
                ResultSet rs = cStmt.getResultSet();
                while(rs.next()){
                    int id = rs.getInt("id");
                    String fullname = rs.getString("fullname");
                    String gender = rs.getString("gender");
                    String emp_title = rs.getString("title");
                    String deptname = rs.getString("dept_name");
                    System.out.println(id+" "+fullname+" "+gender+" "+emp_title+" "+deptname);
                }
                rs.close();
                hadResults = cStmt.getMoreResults();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                if(cStmt != null)
                    cStmt.close();

                if (conn != null)
                    conn.close();
            }catch (Exception e){
                System.out.println(e);
            }
        }

    }
}
