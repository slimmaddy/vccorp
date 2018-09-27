package com.mycompany.app;

/**
 * Hello world!
 *
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        get("/", (request, response) -> {
            String dept_name="", title="";
            int salary=-1;
            long hiredate = -1;
            if(request.queryParams("salary")!=null && isInteger(request.queryParams("salary"),10))
                salary=Integer.parseInt(request.queryParams("salary"));

            if(request.queryParams("hiredate")!=null && isValidDate(request.queryParams("hiredate")))
                hiredate=DateToLong(request.queryParams("hiredate"));
            if(request.queryParams("dept_name")!=null)
                dept_name=request.queryParams("dept_name");
            if(request.queryParams("title")!=null)
                title=request.queryParams("title");
            System.out.println(hiredate);
            Connector con = new Connector();
            HashSet<Employee> hs = con.executeBai7(hiredate,salary,dept_name,title);
            String result= ObjectToJson.Convert(hs);
            return result;
        });
    }
    public static boolean isInteger(String s, int radix) {
        Scanner sc = new Scanner(s.trim());
        if(!sc.hasNextInt(radix)) return false;
        // we know it starts with a valid int, now make sure
        // there's nothing left!
        sc.nextInt(radix);
        return !sc.hasNext();
    }
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
    public static long DateToLong(String inDate) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy/mm/dd");
        try {
            Date d = f.parse(inDate);
            long milliseconds = d.getTime();
            return milliseconds;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

    }
}