package com.example.yanring.myapplication;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Yanring on 2016/2/16.
 */
public class TestIntenService extends IntentService{
    public TestIntenService(String name) {
        super(name);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //不能做耗时操作 如果超过十秒就会无响应
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //排队 ---》 像MessageQueue
        //同步操作:排队领书 处理Intent数据
    }
}
