package com.example.yanring.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Yanring on 2016/3/8.
 */
public class NetworkActivity extends Activity {

    private EditText mEditText;
    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_page);
        findViews();
        setListeners();

    }

    private void setListeners() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getEditTextUrl();
//                String data  = requestData(url);

                new RequestNetworkDataTask().execute(url);
//                mTextView.setText(data);
            }
        });
    }

    private String requestData(String urlString) {
        //1.申请权限
        //2.一定要用异步
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(30000);
            connection.setRequestMethod("GET");

            connection.connect();
            int responseCode = connection.getResponseCode();
            String responseMessage = connection.getResponseMessage();

            InputStream inputStream = connection.getInputStream();

            Reader reader = new InputStreamReader(inputStream,"UTF-8");
            char[] buffer = new char[1024];
            reader.read(buffer);
            String content = new String(buffer);

            return content;



        } catch (MalformedURLException e) {
            e.printStackTrace();
            Toast.makeText(NetworkActivity.this,"invaild url",Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(NetworkActivity.this,"invaild url",Toast.LENGTH_SHORT).show();

        }
        return null;
    }


    private void findViews(){
        mEditText = (EditText)findViewById(R.id.editText);
        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button)findViewById(R.id.button);

    }
    private String getEditTextUrl() {
        return  mEditText != null?mEditText.getText().toString():"";
    }
//异步任务处理
    class RequestNetworkDataTask extends AsyncTask<String ,Integer,String>{

        //在后台work之前
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //主线程
            //UI Loading
        }

        @Override
            protected String doInBackground(String... params) {
            String result = requestData(params[0]);

                return result;
            }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //执行完后在主线程中
            mTextView.setText(result);
        }
    }

}
