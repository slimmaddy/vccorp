package com.mycompany.app;

import java.sql.*;
import java.util.HashSet;

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

    public HashSet<Employee> executeBai7(long hiredate, int salary, String deptname, String title){

        HashSet<Employee> tmp = new HashSet<>();
        String s_hire_date_from = "Select e.emp_no, e.first_name, e.last_name, e.gender, e.hire_date from employees as e where hire_date > ?";
        String s_salary = "select es.emp_no, es.first_name,es.last_name,es.gender,es.hire_date from(\n" +
                "select e.emp_no,e.first_name,e.last_name,e.gender,e.hire_date, year(s.to_date) as y, sum(s.salary) as total from employees as e,\n" +
                " salaries as s \n" +
                " where e.emp_no=s.emp_no\n" +
                "group by  e.emp_no, y) as es\n" +
                "group by es.emp_no\n" +
                "having avg(es.total) >?";
        String s_dept_no ="select es.emp_no, es.first_name,es.last_name,es.gender,es.hire_date from\n" +
                "employees as es, departments as d, dept_emp as de\n" +
                "where es.emp_no = de.emp_no\n" +
                "and de.dept_no = d.dept_no\n" +
                "and d.dept_name = ?";
        String s_title = "select es.emp_no, es.first_name,es.last_name,es.gender,es.hire_date from\n" +
                "employees as es, titles as t\n" +
                "where es.emp_no = t.emp_no\n" +
                "and t.title = ?";
        String s_default = "Select e.emp_no, e.first_name, e.last_name, e.gender, e.hire_date from employees as e";
        PreparedStatement select1 = null;
        PreparedStatement select2 = null;
        PreparedStatement select3 = null;
        PreparedStatement select4 = null;
        Statement statement = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet def = null;
        try {
            conn = getDBConnection();
            statement=conn.createStatement();
            def=statement.executeQuery(s_default);
            if(hiredate!=-1){
                select1=conn.prepareStatement(s_hire_date_from);
                select1.setDate(1,new Date(hiredate));
                rs1 = select1.executeQuery();
            }
            if(salary!=-1){
                select2=conn.prepareStatement(s_salary);
                select2.setInt(1,salary);
                rs2 = select2.executeQuery();
            }
            if(deptname!=""){
                select3=conn.prepareStatement(s_dept_no);
                select3.setString(1,deptname);
                rs3 = select3.executeQuery();
            }
            if(title!=""){
                select4=conn.prepareStatement(s_title);
                select4.setString(1,title);
                rs4 = select4.executeQuery();
            }

            if(def!=null)
            tmp=GetResultSet(def);

            if(rs1!=null){
                HashSet<Employee> tmp1 = GetResultSet(rs1);
                tmp.retainAll(tmp1);
                System.out.println("p1:"+tmp1.size());
            }
            if(rs2!=null){
                HashSet<Employee> tmp2 = GetResultSet(rs2);
                tmp.retainAll(tmp2);
                System.out.println("p2:"+tmp2.size());
            }
            if(rs3!=null){
                HashSet<Employee> tmp3 = GetResultSet(rs3);
                tmp.retainAll(tmp3);
                System.out.println("p3:"+tmp3.size());
            }
            if(rs4!=null){
                HashSet<Employee> tmp4 = GetResultSet(rs4);
                tmp.retainAll(tmp4);
                System.out.println("p4:"+tmp4.size());
            }
            System.out.println("p:"+tmp.size());
            return tmp;

        }catch (Exception e){
            System.out.println(e);
        }finally {
            try{
               if(conn!=null)
                   conn.close();
               if(select1!=null)
                   select1.close();
                if(select2!=null)
                    select2.close();
                if(select3!=null)
                    select3.close();
                if(select4!=null)
                    select4.close();
                if(rs1!=null)
                    rs1.close();
                if(rs2!=null)
                    rs2.close();
                if(rs3!=null)
                    rs3.close();
                if(rs4!=null)
                    rs4.close();
                if(def!=null)
                    def.close();
                if(statement!=null)
                    statement.close();
            }catch (Exception e){
                System.out.println(e);
            }
        }
        System.out.println(tmp.size());
        return tmp;
    }
    private HashSet<Employee> GetResultSet(ResultSet rs4) throws SQLException {
        HashSet<Employee> tmp4 = new HashSet<>();
        while(rs4.next()){
            Employee e = new Employee(rs4.getInt(1),rs4.getString(2),rs4.getString(3),rs4.getString(4),new java.util.Date(rs4.getDate(5).getTime()));
            tmp4.add(e);
        }
        return tmp4;
    }
}
