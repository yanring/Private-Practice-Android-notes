package com.example.yanring.myapplication;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yanring.myapplication.database.DatabaseHelper;
import com.example.yanring.myapplication.provider.URIList;

/**
 * Created by Yanring on 2016/2/25.
 */
public class DatabaseButtonActivity extends AppCompatActivity{

    private SQLiteDatabase mSqLiteDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        mSqLiteDatabase = databaseHelper.getWritableDatabase();

        //ADD
        Button button = (Button) findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }

            private void addData() {
                ContentValues contentValues = new ContentValues();
                contentValues.put("username","极客班");
                contentValues.put("password","123");

                long rowNumber  = mSqLiteDatabase.insert(DatabaseHelper.USER_TABLE_NAME,null,contentValues);
                if (rowNumber != -1){
                    Toast.makeText(DatabaseButtonActivity.this,"ok!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // query :

        //游标
        Cursor cursor = mSqLiteDatabase.query(DatabaseHelper.USER_TABLE_NAME,null,null,null,null,null,null);

        if (cursor.moveToFirst()){
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                String userName = cursor.getString(cursor.getColumnIndexOrThrow("username"));
                Log.i(MainActivity.class.getSimpleName(),i+":"+userName+".");
            }
        }

        //delete
        findViewById(R.id.delet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whereClauseString = "username =?";
                String[] whereArgsString = {"极客班"};
                mSqLiteDatabase.delete(DatabaseHelper.USER_TABLE_NAME,whereClauseString,whereArgsString);
            }
        });
        // update

        ContentValues contentValues = new ContentValues();
        contentValues.put("password","100岁");
        String whereClauseString = "username=?";
        String[] whereArgsString = {"极客班"};
        mSqLiteDatabase.update(DatabaseHelper.USER_TABLE_NAME,contentValues,whereClauseString,whereArgsString);
        //用原生语句insert
        mSqLiteDatabase.execSQL("insert into user(username,password) values ('yanzijie','19岁')");


        //用事务来进行大批量的数据插入,可以省时间

        //开始事务 此时db会被锁定,其他对数据库的操作将无法完成
        mSqLiteDatabase.beginTransaction();
        try {
            //做你的操作

            mSqLiteDatabase.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mSqLiteDatabase.endTransaction();
        }



        ContentResolver contentResolver = getContentResolver();//内容解析器
        Cursor user_cursor = contentResolver.query(Uri.parse(URIList.USER_URI),null,null,null,null);
        Uri uri = Uri.parse("content://com.android.contacts/contacts");
        //Uri是通用资源标识符,Url是网络路径,在安卓中各种资源都可以用uri表示
        Uri uri2 = Uri.parse("content://com.example.yanring.myapplication/table_name/10/user");
        //com.example.yanring.myapplication包中的table_name这个表的第10条的user

        //Cursor cursor1 = contentResolver.query(uri,null,null,null,null);//这里uri指的是一整个数据表



    }







}
