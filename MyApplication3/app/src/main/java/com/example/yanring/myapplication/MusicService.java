package com.example.yanring.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Yanring on 2016/2/13.
 */
public class MusicService extends Service{

    private MediaPlayer mMediaPlayer;
    private IBinder mIBinder;
    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = MediaPlayer.create(this, R.raw.music);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mMediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mMediaPlayer.stop();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }//把mIBinder return进connection里

    public class LocalBinder extends Binder{

        MusicService getService(){
            return MusicService.this;
        }
    }
    public int getMusiPlayProgress(){
        return 18;
    }

}
