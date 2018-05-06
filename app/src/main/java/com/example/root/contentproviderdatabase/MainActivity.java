package com.example.root.contentproviderdatabase;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText name_et,phone_et;
    private MyOpenHelper mMyHelper;
    private SQLiteDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_et=(EditText)findViewById(R.id.name_et);
        phone_et=(EditText)findViewById(R.id.phone_et);

        mMyHelper=new MyOpenHelper(MainActivity.this,"EMPDB",null,1);
        mDB=mMyHelper.getWritableDatabase();
    }

    public void insert(View view)
    {
        ContentValues cv=new ContentValues();
        cv.put("name",name_et.getText().toString());
        cv.put("phone",phone_et.getText().toString());
        long id=mDB.insert("employee",null,cv);
        Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
    }

    public void read(View view)
    {
        Cursor c=mDB.query("employee",null,null,null,null,null,null,null);
        while (c.moveToNext())
        {
            Toast.makeText(this, c.getString(1)+" : "+c.getString(2), Toast.LENGTH_SHORT).show();
        }
    }
}
