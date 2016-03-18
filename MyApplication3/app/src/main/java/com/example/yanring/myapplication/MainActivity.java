package com.example.yanring.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Environment;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TITLE = "title";
    public static final String USERINFO = "userinfo";
    public static final String 名字是 = "名字是:";
    public static final int RESULT_CODE = 23563;
    //private static final String EXTRA_MESSAGE ="com.example.myapplication.MESSAGE" ;
    public static final String EXTRA_MESSAGE ="1"  ;
    public static final String SSSSS = "sssss";
    private Button mToastButton;
    private static final String TAG = MainActivity.class.getSimpleName();
    private LayoutInflater mLayoutInflater;
    /*private View.OnClickListener mClickLitener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button1:
                   //Intent intent = new Intent();
                    //intent.putExtra(Yanringtest.TITLE,"我是主页,我送礼回来了");
                    //setResult(RESULT_CODE,intent);
                    //intent.putExtra(Yanringtest.TITLE,);
                    //finish();

                    Intent intent =new Intent(MainActivity.this , ListViewDemoActivity.class);
                    startActivity(intent);
                    break;

                case R.id.Hello_World:
                    intent = new Intent(MainActivity.this,Yanringtest.class);
                    startActivity(intent);
                    break;
                case R.id.button3:
                    intent = new Intent(MainActivity.this,ListViewDemoActivity.class);
                    startActivity(intent);
                    break;


            }
        }
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreat");
//        --------------------获取mLayoutInflater实例的三个方法---------------------------
        mLayoutInflater = getLayoutInflater();
        mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        mLayoutInflater = LayoutInflater.from(MainActivity.this);//参数是context
//        --------------------获取mLayoutInflater实例的三个方法---------------------------
        //把一个XML解析为View
        View view =mLayoutInflater.inflate(R.layout.activity_main,null);
        view.findViewById(R.id.editText3);//再通过ID进行操作

        setContentView(R.layout.activity_main);
        mToastButton = (Button)findViewById(R.id.button1) ;
        mToastButton.setOnClickListener(this);
        mToastButton = (Button)findViewById(R.id.button3) ;
        mToastButton.setOnClickListener(this);
        TextView helloWorld = (TextView) findViewById(R.id.Hello_World);
        helloWorld.setOnClickListener(this);
        Intent intent = getIntent();
        EditText editText = (EditText) findViewById(R.id.editText3);
        editText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        //创建方法
        testFileDemo();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                   Log.i(TAG,"s:"+s.toString()+",start:"+start+"count:"+count+"after:"+after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG,"s:"+s.toString()+",start:"+start+"count:"+count+"after:"+before);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()>5){
                    Toast.makeText(MainActivity.this,"Over 5 words",Toast.LENGTH_LONG).show();
                }
                Log.i(TAG,"s:"+s.toString());
                Toast.makeText(MainActivity.this,"Over 555 words",Toast.LENGTH_LONG).show();
            }
        });

    }
    /*
    the demo for test file
     */
    private void testFileDemo() {

        //create a new file in internal storage
        File file = new File(getFilesDir(),"test.txt");
        try {//create test.txt file
            boolean isSuccess = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String string = "Test file 001";
        try {
            FileOutputStream fileOutputStream = openFileOutput("test2.txt", Context.MODE_PRIVATE);
            try {
                fileOutputStream.write(string.getBytes());
                fileOutputStream.close();

            }catch (IOException e){
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //获得状态,check external storage
        String state = Environment.getExternalStorageState();
        deleteFile("test2.txt");

        if (TextUtils.equals(state,Environment.MEDIA_MOUNTED));

    }
    private void testGson(){
        InputStream is = getResources().openRawResource(R.raw.json);
        String jsonString = getStringByInputStream(is);
        Gson gson = new Gson();
        UserData userData = gson.fromJson(jsonString,UserData.class);

    }
    private String getStringByInputStream(InputStream is) {
        return null;
    }

    void testAssets(){//测试assets里的资源

        //读取文件,第一种,直接读路径
        WebView webView = new WebView(this);
        webView.loadUrl("file:///android_asset/test.html");

        try {//open的只能是文件
            InputStream inputStream = getResources().getAssets().open("test.html");
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"文件读取异常",Toast.LENGTH_SHORT).show();
        }
        //读列表
        try {
            String[] filnames = getAssets().list("images");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //getAssets会返回本身的assets
        //读图片
        try {
            InputStream inputStream = getAssets().open("images/test2.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读音乐
        try {
            AssetFileDescriptor assetFileDescriptor = getAssets().openFd("music.mp3");
            MediaPlayer player = new MediaPlayer();
            player.reset();
            //设置起始和长度
            player.setDataSource(assetFileDescriptor.getFileDescriptor(),assetFileDescriptor.getStartOffset(),assetFileDescriptor.getLength());
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void testResFile(){//测试res里的资源
        InputStream inputStream = getResources().openRawResource(R.raw.music);
        //在对stream操作

        getResources().getColor(R.color.blue);//还可以getString之类的
    }
    void testSDcard(){
        //获取外部存储的地址
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Environment.getDataDirectory();//获取Android的data数据目录
        Environment.getDownloadCacheDirectory();
        Environment.getExternalStorageDirectory();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart");
    }

    public void Button2_OnClick(View view) {
        Intent intent = new Intent(this,TestViewFragmentActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText4);
//        String message = editText.getText().toString();
//        intent.putExtra(SSSSS,message);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                Intent intent =new Intent(MainActivity.this ,MusicButtonActivity.class);
                startActivity(intent);
                break;

            case R.id.Hello_World:
                intent = new Intent(MainActivity.this,DatabaseButtonActivity.class);
                startActivity(intent);
                break;
            case R.id.button3:
                intent = new Intent(MainActivity.this,NetworkActivity.class);
                startActivity(intent);
                break;


        }
    }
};


