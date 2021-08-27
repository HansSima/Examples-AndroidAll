package com.example.allexamples.apps;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allexamples.R;

public class TicTacToeApp extends AppCompatActivity {

    private int player = 0;
    private int objectId;
    private int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    private int[][] winCombinations = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    private boolean gameIsActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
    }

    public void showToken(View view) {
        ImageView counter = (ImageView) view;
        objectId = Integer.valueOf(counter.getTag().toString());
        playerPickup(counter, objectId);
    }

    public void playAgain(View view) {
        LinearLayout hideLayout = (LinearLayout) findViewById(R.id.linearLayoutId);
        hideLayout.setVisibility(View.INVISIBLE);
        resetGameState();
        hideImage();
    }

    private void printText(String endGameText){
        gameIsActive = false;
        TextView winner = (TextView) findViewById(R.id.winnerMessage);
        winner.setText(endGameText);
        LinearLayout gameRepeatLayout = (LinearLayout) findViewById(R.id.linearLayoutId);
        gameRepeatLayout.setVisibility(View.VISIBLE);
    }

    private void playerPickup(ImageView counter, int objectId){
        if (gameState[objectId] == 2 && gameIsActive) {
            counter.setTranslationY(-1000f);
            gameState[objectId] = player;

            if (player == 0) {
                counter.setImageResource(R.drawable.yellow);
                counter.setScaleX(1f);
                counter.setScaleY(1f);
                player = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                counter.setScaleX(0.8f);
                counter.setScaleY(0.8f);
                player = 0;

            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(800);
            gameState();

        }
    }

    private void gameState(){
        for (int[] winCombination : winCombinations) {
            if (gameState[winCombination[0]] == gameState[winCombination[1]] &&
                    gameState[winCombination[1]] == gameState[winCombination[2]] &&
                    gameState[winCombination[0]] != 2) {

                printText("Player " + player + " wins");
                break;
            } else {
                boolean gameIsOver = true;
                for (int draw : gameState) {
                    if (draw == 2) {
                        gameIsOver = false;
                    }
                }
                if (gameIsOver) {
                    printText("Draw");
                }
            }
        }
    }

    private void hideImage(){
        androidx.gridlayout.widget.GridLayout gridImg = findViewById(R.id.gridLayer);
        for (int i = 0; i < gridImg.getChildCount(); i++) {
            ((ImageView) gridImg.getChildAt(i)).setImageResource(0);
        }
    }

    private void resetGameState(){
        player = 0;
        gameIsActive = true;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
    }

}