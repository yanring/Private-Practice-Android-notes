package com.example.yanring.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yanring on 2016/1/28.
 */
public class ListViewDemoActivity extends Activity{

    private ListView mPhoneBookListView;
    private List<UserInfo> userInfos;
    private List<UserInfo> mUserInfos;
    private int N;
    private PhoneBookAdapter mPhoneBookAdapter;
    private EditText mEditText;
    private Button mInputButton;
    private SharedPreferences.Editor mEditor;
    private String mPreference_name="preference_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_demo);


        findViews();
        setData();
       // phoneBookAdapter.notifyDataSetChanged();//更改ListView的数据
        setListener();

    }

    private void setListener() {
        mPhoneBookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==0){
                    //新建另外一批数据
                    // 替换掉老的数据
                    // 刷新listview,让她更新自己
                    mUserInfos.clear();
                    mUserInfos.add(new UserInfo("Yanring1","2121"));
                    mUserInfos.add(new UserInfo("Turing2","318"));
                    mUserInfos.add(new UserInfo("Turing3","148"));
                    mPhoneBookAdapter.refreData(mUserInfos);
                }
                Toast.makeText(ListViewDemoActivity.this, mUserInfos.get(position).getmUserName()+"被我点击了",Toast.LENGTH_LONG).show();
            }
        });
        mInputButton.setOnClickListener(new View.OnClickListener() {

            private int mCount;

            @Override
            public void onClick(View view) {
                String CountString =  mEditText.getText().toString();
                mCount = Integer.valueOf(CountString);
                N = mCount;
                mUserInfos.clear();

                mPhoneBookAdapter.refreData(mUserInfos);
                saveData2Preference();
                setData();
            }
        });

        mPhoneBookListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewDemoActivity.this, mUserInfos.get(position).getmAge()+"被我点击了",Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    private void saveData2Preference() {
        //系统会自动帮我们创建一个XML文件,名字是"preference_name",
        mPreference_name = "preference_name";
        //SharedPreferences sharedPreference = ListViewDemoActivity.this.getSharedPreferences(mPreference_name, Context.MODE_PRIVATE);
        SharedPreferences sharedPreference = getSharedPreferences(mPreference_name, Context.MODE_PRIVATE);

        mEditor = sharedPreference.edit();

        mEditor.putInt("CountNumber",N);
        //和网络相关,和IO相关的,都要用异步后台写数据,另开线程

        mEditor.commit();

    }

    @NonNull
    private PhoneBookAdapter setData() {
        mUserInfos = new ArrayList<>();
        SharedPreferences sharedPreference = getSharedPreferences("preference_name", Context.MODE_PRIVATE);
        N = sharedPreference.getInt("CountNumber",2);
        mEditText.setText(""+N);
        for (int i = 0; i < N ; i++) {
            mUserInfos.add(new UserInfo("Yanring","21"));
        }
        //绑定
        mPhoneBookAdapter = new PhoneBookAdapter(ListViewDemoActivity.this, mUserInfos);
        mPhoneBookListView.setAdapter(mPhoneBookAdapter);
        return mPhoneBookAdapter;
    }

    private void findViews() {
        mPhoneBookListView = (ListView)findViewById(R.id.list_view);//把list_view给mPhoneBookListView
        mEditText = (EditText) findViewById(R.id.input);
        mInputButton = (Button) findViewById(R.id.input_button);
    }
    //刷新数据

}
