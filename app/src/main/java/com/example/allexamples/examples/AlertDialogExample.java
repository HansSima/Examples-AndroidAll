package com.example.allexamples.examples;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.allexamples.R;

public class AlertDialogExample extends AppCompatActivity {

    TextView language;
    SharedPreferences sharedPreferences;
    String actualLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        language = findViewById(R.id.language);
        sharedPreferences = this.getSharedPreferences("com.example.usingalertdialogs", Context.MODE_PRIVATE);
        actualLanguage = sharedPreferences.getString("language", "JAZYK");
        language.setText(actualLanguage);

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_menu_month)
                .setTitle("Vyber jazyk")
                .setMessage("Vyber si svuj jazyk")
                .setPositiveButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlertDialogExample.this, "English", Toast.LENGTH_LONG).show();
                        sharedPreferences.edit().putString("language", "English").apply();
                        language.setText(actualLanguage = sharedPreferences.getString("language", "JAZYK"));

                    }
                })
                .setNegativeButton("Czech", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlertDialogExample.this, "Czech", Toast.LENGTH_LONG).show();
                        sharedPreferences.edit().putString("language", "Czech").apply();
                        language.setText(actualLanguage = sharedPreferences.getString("language", "JAZYK"));
                    }
                })
                .show();

    }

}