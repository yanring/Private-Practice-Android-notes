package com.example.yanring.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Yanring on 2016/1/25.
 */
public class Yanringtest extends Activity {
    public static final int REQUEST_CODE = 97999;
    public static final String TITLE = "title";
    public static final String USERINFO = "userinfo";
    Handler mHandler = new Handler();
    private static final String TAG = Yanringtest.class.getSimpleName();
    private TextView textView;
    private TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        view = (TextView) findViewById(R.id.Title_text_view);
        final String title = view.getText().toString();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //跳转到mainActivity
                UserInfo userInfo = new UserInfo("小名","12");
                Intent intent = new Intent(Yanringtest.this,MainActivity.class);
                intent.putExtra(TITLE,title);
                intent.putExtra(USERINFO,userInfo);
                startActivityForResult(intent, REQUEST_CODE );

            }
        },1000);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG,"requestcode:"+requestCode+",resultCode:"+resultCode );
        if (requestCode == REQUEST_CODE && resultCode == MainActivity.RESULT_CODE){
            if(data != null){
                String title = data.getStringExtra(TITLE)+"sssss";
                view.setText(title);
            }
        }
    }
}

