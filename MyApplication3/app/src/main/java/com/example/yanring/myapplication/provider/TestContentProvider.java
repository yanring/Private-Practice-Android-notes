package com.example.yanring.myapplication.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.yanring.myapplication.database.DatabaseHelper;

/**
 * Created by Yanring on 2016/3/2.
 */
public class TestContentProvider extends ContentProvider {

    private static UriMatcher sUriMatcher;
    public  static final  int URI_MATH_USER = 1;
    public  static final  int URI_MATCH_BOOK= 2;


    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(URIList.AUTHORITY, DatabaseHelper.USER_TABLE_NAME,1);
        sUriMatcher.addURI(URIList.AUTHORITY, DatabaseHelper.USER_TABLE_NAME,2);
    }

    private DatabaseHelper mDatabaseHelper;

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new DatabaseHelper(getContext());

        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)){
            return null;
        }
        Cursor cursor = mDatabaseHelper.getReadableDatabase().query(tableName,projection,selection,selectionArgs,null,null,sortOrder);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)){
            return null;
        }
        long id = mDatabaseHelper.getWritableDatabase().insert(tableName,null,contentValues);
        //insert will return a id, to recognize which item you inserted.

        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)){
            return -1;
        }
        int count = mDatabaseHelper.getWritableDatabase().delete(tableName,s,strings);
        return count;

    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)){
            return -1;
        }
        int count = mDatabaseHelper.getWritableDatabase().update(tableName,contentValues,s,strings);
        return count;
    }
    private String getTableName(Uri uri){
        int type = sUriMatcher.match(uri);
        String tableName="" ;
        switch (type){
            case URI_MATCH_BOOK:
                tableName = DatabaseHelper.BOOK_TABLE_NAME;
                break;
            case URI_MATH_USER:
                tableName = DatabaseHelper.USER_TABLE_NAME;
                break;

        }
        return tableName;
    }
}
