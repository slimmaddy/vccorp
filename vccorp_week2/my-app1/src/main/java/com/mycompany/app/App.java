package com.mycompany.app;

/**
 * Hello world!
 *
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App 
{
    public static void main( String[] args )
    {
        try {
            Document doc = Jsoup.connect("http://dantri.com.vn").get();
            Date date = new Date() ;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
            File file = new File(dateFormat.format(date) + ".txt") ;
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(doc.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
