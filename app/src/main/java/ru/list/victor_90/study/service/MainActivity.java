package ru.list.victor_90.study.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String ACTION_MESSAGE = "ru.list.victor_90.study.service.ACTION_MESSAGE";
TextView textView;
    private  int get_i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(MainActivity.this, MyService.class));

        findViewById(R.id.button).setOnClickListener(this);
        textView = (TextView) findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v) {
        textView.setText(get_i + "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_MESSAGE);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if(action == ACTION_MESSAGE){
                get_i = intent.getIntExtra("int",0);
            }
        }
    };

}
