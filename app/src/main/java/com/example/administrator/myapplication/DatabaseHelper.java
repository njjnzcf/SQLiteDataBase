package com.example.administrator.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2020/4/20 0020.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super (context, name, factory, version);
    }

    @Override
    public void onCreate (SQLiteDatabase sqLiteDatabase) {
            String sql="create table user(name varchar(20))";
            sqLiteDatabase.execSQL (sql);
    }

    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
