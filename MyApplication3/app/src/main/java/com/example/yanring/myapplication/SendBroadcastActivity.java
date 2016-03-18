package com.example.yanring.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Yanring on 2016/2/17.
 */
public class SendBroadcastActivity extends Activity {
    TestBroadcastReciver mTestBroadcastReciver = new TestBroadcastReciver();
    private Button mSendBroadcastButton;
    private IntentFilter mIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_broadcast);
        mIntentFilter = new IntentFilter();
        //动态注册广播

        mSendBroadcastButton = (Button) findViewById(R.id.send_broadcast_button);
        mSendBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.yanring.broadcast");
                intent.putExtra("Toast","This is my toast of broadcast");

                sendBroadcast(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mIntentFilter.addAction("com.yanring.broadcast");
        registerReceiver(mTestBroadcastReciver,mIntentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mTestBroadcastReciver);
    }
}
