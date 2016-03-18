package com.example.yanring.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yanring on 2016/1/28.
 */
public class GridViewDemoActivity extends Activity{

    private GridView mGridView;
    private List<UserInfo> userInfos;
    private List<UserInfo> mUserInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview_demo);


        mGridView = (GridView)findViewById(R.id.grid_view);//把list_view给mPhoneBookListView
        mUserInfos = new ArrayList<>();
        mUserInfos.add(new UserInfo("Yanring","21"));
        mUserInfos.add(new UserInfo("Turing","18"));
        mUserInfos.add(new UserInfo("Turing","18"));
        mUserInfos.add(new UserInfo("Turing","18"));
        mUserInfos.add(new UserInfo("Turing","18"));
        mUserInfos.add(new UserInfo("Turing","18"));
        mUserInfos.add(new UserInfo("Turing","18"));
        mUserInfos.add(new UserInfo("Turing","18"));
        mUserInfos.add(new UserInfo("Turing","18"));
        mUserInfos.add(new UserInfo("Turing","18"));
        mUserInfos.add(new UserInfo("Turing","18"));

        final GridViewAdapter gridViewAdapter = new GridViewAdapter(GridViewDemoActivity.this, mUserInfos);//绑定
        mGridView.setAdapter(gridViewAdapter);
       // phoneBookAdapter.notifyDataSetChanged();//更改ListView的数据
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                    gridViewAdapter.refreData(mUserInfos);
                }
                Toast.makeText(GridViewDemoActivity.this, mUserInfos.get(position).getmUserName()+"被我点击了",Toast.LENGTH_LONG).show();
            }
        });
        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridViewDemoActivity.this, mUserInfos.get(position).getmAge()+"被我点击了",Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }
    //刷新数据

}
