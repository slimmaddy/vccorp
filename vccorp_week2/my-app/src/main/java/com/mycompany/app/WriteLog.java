package com.mycompany.app;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class WriteLog extends TimerTask {
    private long startTime;
    private String url;
    private static final Logger logger= LoggerFactory.getLogger(WriteLog.class);
    private static final String path = "";
    private String filename;
    public WriteLog(String url, String filename) {
        this.url = url;
        this.filename= filename;
        startTime = System.currentTimeMillis();
    }

    public void run() {

        try {
            JSONObject data= JsonReader.readJsonFromUrl(url);

            Integer newuser = data.getInt("user");

            String logline = ReadLogfile.tail(new File(path+filename));

            StringTokenizer st = new StringTokenizer(logline," ,!=+-—");
            Integer user;
            String tmp;
            if(st.hasMoreTokens()){
                tmp= st.nextToken();
                tmp = st.nextToken();
                user = Integer.parseInt(st.nextToken());
            }else{
                user = 0;
            }

            if((newuser-user)>user*0.5/100){
                logger.info(newuser.toString());
            }
            else if (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()-startTime)>=12){
                startTime = System.currentTimeMillis();
                logger.debug(newuser.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
