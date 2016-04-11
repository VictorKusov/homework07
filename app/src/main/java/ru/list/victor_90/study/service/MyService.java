package ru.list.victor_90.study.service;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service{

    private  int i = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        final Intent intent = new Intent(MainActivity.ACTION_MESSAGE);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {

                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                    intent.putExtra("int", i);
                    sendBroadcast(intent);
                }
            }
        });
        thread.start();
    }
}
