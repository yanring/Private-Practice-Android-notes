package com.example.yanring.myapplication.provider;

import com.example.yanring.myapplication.database.DatabaseHelper;

/**
 * Created by Yanring on 2016/3/5.
 */
public class URIList {
    public static final  String CONTENT = "content://";
    public static final  String AUTHORITY= "com.example.yanring.myapplication";
    public static final  String USER_URI = CONTENT+AUTHORITY+"/"+ DatabaseHelper.USER_TABLE_NAME;
    public static final  String BOOK_URI = CONTENT+AUTHORITY+"/"+ DatabaseHelper.BOOK_TABLE_NAME;
}
