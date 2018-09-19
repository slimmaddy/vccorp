package com.mycompany.app;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 */
public class App {
    public static void main(String[] args) {
        //System.out.println(ClassLoader.getSystemResource("logback.xml"));
        final TimerTask task = new WriteLog("http://news.admicro.vn:10002/api/realtime?domain=kenh14.vn", "bai1");
        //Timer uploadCheckerTimer = new Timer(true);
       // uploadCheckerTimer.scheduleAtFixedRate(task, 0, 2 * 1000);
        Thread t = new Thread(){
            public void run() {
                while(true) {
                    task.run();
                    try {
                        Thread.sleep(2 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

}
