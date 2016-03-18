package com.example.yanring.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yanring on 2016/2/25.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String USER_TABLE_NAME = "user";//创建数据库
    public static final String BOOK_TABLE_NAME  = "Book";
    private String Book_Name;
    private String Book_price;

    public DatabaseHelper(Context context) {
        super(context, "test.db", null,1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USER_TABLE_NAME + "(username varchar(20) not null , password varchar(60) not null);");
        Book_Name = "book_name";
        Book_price = "book_price";
        db.execSQL("create table " + BOOK_TABLE_NAME + "(" + Book_Name + " varchar(20) not null , " + Book_price + " varchar(60) not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
