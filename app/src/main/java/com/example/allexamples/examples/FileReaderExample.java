package com.example.allexamples.examples;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allexamples.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReaderExample extends AppCompatActivity {

    private static final String FILE_NAME = "example.txt";
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_save_load);

        mEditText = findViewById(R.id.editText);
    }

    public void btnSave (View view) {
        String text = mEditText.getText().toString();
        FileOutputStream mfos = null;

        try {
            mfos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            mfos.write(text.getBytes());

            mEditText.getText().clear();
            Toast.makeText(this, "Succesfully save to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(mfos != null){
                try {
                    mfos.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }

    public void btnLoad (View view) {
        FileInputStream mfis = null;

        try {
            mfis = openFileInput(FILE_NAME);
            InputStreamReader misr = new InputStreamReader(mfis);
            BufferedReader mbr = new BufferedReader(misr);
            StringBuilder msb = new StringBuilder();
            String text;

            while((text = mbr.readLine()) != null){
                msb.append(text).append("\n");
            }

            mEditText.setText(msb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(mfis != null){
                try {
                    mfis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}