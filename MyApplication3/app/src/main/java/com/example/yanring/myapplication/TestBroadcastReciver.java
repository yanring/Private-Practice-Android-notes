package com.example.yanring.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by Yanring on 2016/2/17.
 */
public class TestBroadcastReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
    //接收广播
        if (intent != null)
            if (TextUtils.equals(intent.getAction(),"com.yanring.broadcast")){
                String toastString = intent.getStringExtra("Toast");
                Toast.makeText(context,toastString,Toast.LENGTH_SHORT).show();
            }

    }

}
