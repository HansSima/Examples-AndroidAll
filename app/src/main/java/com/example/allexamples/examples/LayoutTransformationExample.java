package com.example.allexamples.examples;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allexamples.R;

public class LayoutTransformationExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_transformation);
        ImageView obrazekBarta = findViewById(R.id.bartImg);
        obrazekBarta.setTranslationX(1000f);
    }

    public void animation(View view){
        ImageView obrazekBarta = findViewById(R.id.bartImg);
        obrazekBarta.animate()
                .translationXBy(-1000f)
                .rotation(2700f)
                .scaleX(2f)
                .scaleY(2f)
                .setDuration(2000);
        //obrazekHomer.animate().alpha(1f).setDuration(2000);
        //ImageView obrazekHomer = (ImageView)findViewById(R.id.homerImg);

        }

}