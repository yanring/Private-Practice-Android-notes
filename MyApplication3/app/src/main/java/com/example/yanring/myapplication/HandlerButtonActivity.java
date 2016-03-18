package com.example.yanring.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/**
 * Created by Yanring on 2016/2/7.
 */
public class HandlerButtonActivity extends Activity {

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //接收消息
            switch (msg.what){
                case 8888:
                    int value = (int) msg.obj;
                    mMax_second -=value;
                    mTextView.setText(String.valueOf(mMax_second));

                    msg = Message.obtain();
                    msg.arg1 = 0;
                    msg.arg2 = 1;
                    msg.what = 8888;//通过what来识别是哪个message
                    msg.obj = 1;
                    if(mMax_second > 0){
                        mHandler.sendMessageDelayed(msg,1000);
                    }
                    break;

            }
        }
    };
    private TextView mTextView;
    private int mMax_second;
    private TestHandler mTestHandler = new TestHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        mTextView = (TextView) findViewById(R.id.handler_text_view_text);
        mMax_second = 20;
        Message message = mHandler.obtainMessage();//官方推荐这样创建message,这样会自己维护它的销毁

        message.arg1 = 0;
        message.arg2 = 1;
        message.what = 8888;//通过what来识别是哪个message
        message.obj = 1;
        //mHandler.sendMessageDelayed(message,1000);//发出message,延迟1000ms,发出后handlerMessage会收到
        //如果这么写,当这个activity销毁后,内部类中的对象不会释放导致内存泄露
        mTestHandler.sendMessageDelayed(message,1000);//因此则要用外部类


    }
    public class TestHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //接收消息
            switch (msg.what){
                case 8888:
                    int value = (int) msg.obj;
                    mMax_second -=value;
                    mTextView.setText(String.valueOf(mMax_second));

                    msg = Message.obtain();
                    msg.arg1 = 0;
                    msg.arg2 = 1;
                    msg.what = 8888;//通过what来识别是哪个message
                    msg.obj = 2;
                    if(mMax_second > 0){
                        mTestHandler.sendMessageDelayed(msg,1000);
                    }
                    break;

            }
        }
    }
}
