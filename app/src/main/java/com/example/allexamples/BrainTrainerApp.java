package com.example.allexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class BrainTrainerApp extends AppCompatActivity {

    private androidx.gridlayout.widget.GridLayout gridOfButtons;

    private TextView timerTextView;
    private TextView expressionTextView;
    private TextView scoreTextView;

    private Button goButton;
    private Button result0;
    private Button result1;
    private Button result2;
    private Button result3;

    private int result;
    private int randomPosition;
    private int score;
    private int numberOfQuestions = 1;
    private int[] arrayOfResults = {0, 0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_trainer_app);
        gridOfButtons = findViewById(R.id.gridView);
        timerTextView = findViewById(R.id.timerTextView);
        expressionTextView = findViewById(R.id.expressionTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        goButton = findViewById(R.id.GoButton);
        result0 = findViewById(R.id.result0);
        result1 = findViewById(R.id.result1);
        result2 = findViewById(R.id.result2);
        result3 = findViewById(R.id.result3);
        endGame();
    }

    public void startButton(View view) {
        gridOfButtons.setVisibility(View.VISIBLE);
        goButton.setVisibility(View.INVISIBLE);
        numberOfQuestions--;
        generator();
        timer();
    }

    public void answer(View view) {
        Button buttonObject = (Button) view;
        int buttonId = Integer.valueOf(buttonObject.getTag().toString());
        checkResults(buttonId);
    }

    private void generator() {
        Random randomNumber = new Random();
        for (int x = 0; x < 4; x++) {
            arrayOfResults[x] = randomNumber.nextInt(120);
            //Log.i("Button tag:", String.valueOf(poleVysledku[x]));
        }

        if (controlDuplicity(arrayOfResults)) {
            generator();
        }
        expressionCreator();

        numberOfQuestions++;
    }

    private boolean controlDuplicity(int[] my_array) {


        for (int i = 0; i < my_array.length - 1; i++) {
            for (int j = i + 1; j < my_array.length; j++) {
                if ((my_array[i] == my_array[j]) && (i != j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void expressionCreator() {
        Random randomNumber = new Random();
        randomPosition = randomNumber.nextInt(3);
        result = arrayOfResults[randomPosition];
        int secondNumber = randomNumber.nextInt(result+1);
        int firstNumber = result - secondNumber;
        expressionTextView.setText(Integer.toString(firstNumber) + " + " + Integer.toString(secondNumber) +" = ");
        listOfOptions();
    }

    private void listOfOptions() {
        result0.setText(Integer.toString(arrayOfResults[0]));
        result1.setText(Integer.toString(arrayOfResults[1]));
        result2.setText(Integer.toString(arrayOfResults[2]));
        result3.setText(Integer.toString(arrayOfResults[3]));
    }

    private void checkResults(int buttonId) {

        if (buttonId == randomPosition){
            score++;
        }

        scoreTextView.setText("Score: " + Integer.toString(score));
        generator();
    }

    private void endGame(){

        gridOfButtons.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);
        float ratio = (float)score/(float)numberOfQuestions*100;
        goButton.setText("GO!!!! \r\n Ratio: " + Float.toString(ratio) +" %");
        score=0;
        scoreTextView.setText(Integer.toString(score));
        expressionTextView.setText("");
        timerTextView.setText("");
        numberOfQuestions = 1;
    }

    private void timer() {

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(Long.toString(millisUntilFinished/1000) +" s");
            }

            @Override
            public void onFinish() {
                endGame();
            }
        }.start();
    }
}