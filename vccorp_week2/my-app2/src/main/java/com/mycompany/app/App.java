package com.mycompany.app;

/**
 * Hello world!
 *
 */
import java.util.ArrayList;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        get("/:input", (request, response) -> {
            ArrayList<Long>tmp=Factorize.FactorizeNumber(Long.parseLong(request.params(":input")));
            StringBuilder sb = new StringBuilder("Day so nguyen to: ");
            for(Long i:tmp){
                sb.append(i.toString()+" ");
            }
            return sb.toString();
        });
    }
}