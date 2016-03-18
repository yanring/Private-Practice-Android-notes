package com.example.yanring.myapplication;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

/**
 * Created by Yanring on 2016/2/14.
 */
public class MusicButtonActivity extends Activity implements View.OnClickListener{
    private MusicService mMusicService;
    private Button mStartButton;
    private Button mStopButton;
    private ServiceConnection mServiceConnection = new ServiceConnection()

    {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {//IBinder是service中onBind返回的
            MusicService.LocalBinder localBinder = (MusicService.LocalBinder) service;
            mMusicService = localBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_button);

        mStartButton = (Button) findViewById(R.id.start_button);
        mStopButton = (Button) findViewById(R.id.stop_button);

        mStartButton.setOnClickListener(this);
        mStopButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MusicButtonActivity.this,MusicService.class);
        intent.putExtra("2222",2);
        switch (view.getId()){
            case R.id.start_button:
                startService(new Intent(MusicButtonActivity.this,MusicService.class));
                bindService(new Intent(MusicButtonActivity.this,MusicService.class),mServiceConnection,BIND_AUTO_CREATE );//连接,只有关闭连接才能stop

                break;
            case R.id.stop_button:
                unbindService(mServiceConnection);
                stopService(new Intent(MusicButtonActivity.this,MusicService.class));
                break;
        }

    }
}
