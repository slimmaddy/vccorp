package com.mycompany.app;

/**
 * Hello world!
 *
 */

import java.util.ArrayList;
import com.google.common.cache.*;
import static spark.Spark.get;

public class App {
    public static void main(String[] args) {
        LoadingCache<Long, ArrayList<Long>> cache = Factorize.getLoadingCache();
        get("/:input", (request, response) -> {
            ArrayList<Long>tmp=cache.get(Long.parseLong(request.params(":input")));
            StringBuilder sb = new StringBuilder("Day so nguyen to: ");
            for(Long i:tmp){
                sb.append(i.toString()+" ");
            }
            return sb.toString();
        });
    }
}