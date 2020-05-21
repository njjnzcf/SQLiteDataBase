package com.example.administrator.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //https://blog.csdn.net/midnight_time/article/details/80834198
    private SQLiteDatabase mDb;
    private EditText mEditText04;
    private EditText mEditText01;
    private EditText mEditText02;
    private EditText mEditText03;
    private TextView mResult;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        mEditText01 = (EditText) findViewById (R.id.edit01);
        mEditText02 = (EditText) findViewById (R.id.edit02);
        mEditText03 = (EditText) findViewById (R.id.edit03);
        mEditText04 = (EditText) findViewById (R.id.edit04);
        mResult = (TextView) findViewById (R.id.text_result);

        Button insert = (Button) findViewById (R.id.btn_insert);
        Button clear = (Button) findViewById (R.id.btn_clear);
        Button update = (Button) findViewById (R.id.btn_update);
        Button delete = (Button) findViewById (R.id.btn_delete);
        Button clear_all = (Button) findViewById (R.id.btn_clear_all);
        Button find_all = (Button) findViewById (R.id.btn_find_all);

        insert.setOnClickListener (this);
        clear.setOnClickListener (this);
        update.setOnClickListener (this);
        delete.setOnClickListener (this);
        clear_all.setOnClickListener (this);
        find_all.setOnClickListener (this);


        DatabaseHelper dbhelper = new DatabaseHelper (MainActivity.this, "test_db", null, 1);
        mDb = dbhelper.getWritableDatabase ();


    }

    @Override
    public void onClick (View v) {
        switch (v.getId ()) {
            case R.id.btn_insert://插入
                String trim = mEditText01.getText ().toString ().trim ();
                ContentValues values = new ContentValues ();
                values.put ("name", trim);
                mDb.insert ("user", null, values);
                break;
            case R.id.btn_delete://删除
                String del = mEditText01.getText ().toString ().trim ();
                mDb.delete ("user", "name=?", new String[]{del});
                break;
            case R.id.btn_update://改
                String befor_update = mEditText02.getText ().toString ();
                String after_update = mEditText03.getText ().toString ();
                ContentValues value = new ContentValues ();
                value.put ("name", after_update);
                mDb.update ("user", value, "name=?", new String[]{befor_update});
                break;
            case R.id.btn_find_all://查
                Cursor cursor = mDb.query ("user", new String[]{"name"}, null, null, null, null, null);
                mResult.setText ("");
                while (cursor.moveToNext ()) {
                    mResult.append (cursor.getString (cursor.getColumnIndex ("name"))+"\n");
                }
                cursor.close ();
                break;
            case R.id.btn_clear_all://清除所有
                mDb.delete ("user", "", null);
                break;
        }
    }
}
