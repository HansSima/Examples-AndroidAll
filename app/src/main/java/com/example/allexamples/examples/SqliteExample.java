package com.example.allexamples.examples;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allexamples.R;

public class SqliteExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3)) ");
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Jenda', 28)");
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Jan', 29)");
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Honza', 31)");

            Cursor c = myDatabase.rawQuery("SELECT * FROM users", null);

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");

            while(c.moveToNext()){
                Log.i("name", c.getString(nameIndex));
                Log.i("age", Integer.toString(c.getInt(ageIndex)));

            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}