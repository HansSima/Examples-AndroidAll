package com.example.allexamples.apps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.allexamples.R;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuessCelebrityApp extends AppCompatActivity {
    
    ArrayList<String> supNameList = new ArrayList<String>();
    ArrayList<String> supNameListCopy = new ArrayList<String>();
    ArrayList<String> supImgList = new ArrayList<String>();
    ArrayList<String> supImgListCopy = new ArrayList<String>();

    int [] supNameArray = {0,0,0,0};
    int winningPosition = 0;
    int buttonId;
    int intScore;
    int numberOfQuestions;

    Button btnSuperHeroName0;
    Button btnSuperHeroName1;
    Button btnSuperHeroName2;
    Button btnSuperHeroName3;
    Button btnPlay;

    LinearLayout buttonLayout;
    ConstraintLayout playLayout;

    ImageView superheroPic;

    TextView textScore;
    TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_celebrity);

        btnSuperHeroName0 = findViewById(R.id.btnSuperHeroName0);
        btnSuperHeroName1 = findViewById(R.id.btnSuperHeroName1);
        btnSuperHeroName2 = findViewById(R.id.btnSuperHeroName2);
        btnSuperHeroName3 = findViewById(R.id.btnSuperHeroName3);
        btnPlay = findViewById(R.id.play);

        buttonLayout = findViewById(R.id.buttonLayout);
        playLayout = findViewById(R.id.playLayout);

        superheroPic = findViewById(R.id.superheroPic);

        textScore = findViewById(R.id.score);
        timer = findViewById(R.id.timer);

        DownloadContent newDownloadClass = new DownloadContent();
        String superheroHtml= null;
        try {
            superheroHtml = newDownloadClass.execute("https://www.comicbasics.com/ranked-the-100-greatest-superheroes-in-the-history-of-comic-books/").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        superheroArray(superheroHtml);

    }

    public class DownloadContent extends AsyncTask<String,Void, String> {
        @Override
        protected String doInBackground(String... superheroUrl) {
            String result = "";
            URL url;
            HttpURLConnection connection = null;
            try {
                url = new URL(superheroUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();
                while (data != -1) {
                    char reader = (char) data;
                    result += reader;
                    data = inputStreamReader.read();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Connection failed";
            }
            return result;
        }
    }

    public class DownloadedImage extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urlPic) {
            Bitmap myBitmap = null;
            try {
                URL url=new URL(urlPic[0]);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                myBitmap = BitmapFactory.decodeStream(inputStream);
            }catch (Exception e){
                e.printStackTrace();
            }
            return myBitmap;
        }
    }

    public void pressToPlay(View view){
        playLayout.setVisibility(View.INVISIBLE);
        buttonLayout.setVisibility(View.VISIBLE);
        textScore.setVisibility(View.VISIBLE);
        textScore.setText("0");
        timer.setVisibility(View.VISIBLE);
        timer.setText("00");
        superheroPic.setVisibility(View.VISIBLE);

        supNameListCopy.addAll(0, supNameList);
        supImgListCopy.addAll(0, supImgList);

        try {
            setSupButton();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timer();
    }

    public void getButtonId(View view){
        Button buttonObject = (Button) view;
        buttonId = Integer.valueOf(buttonObject.getTag().toString());
        checkResultCounter();
    }

    public void showImage(int winningPic) throws ExecutionException, InterruptedException {

        Bitmap supImageByUrl =null;
        DownloadedImage newImage = new DownloadedImage();
        try {
            supImageByUrl = newImage.execute(supImgListCopy.get(supNameArray[winningPic])).get();
            superheroPic.setImageBitmap(supImageByUrl);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void setSupButton () throws ExecutionException, InterruptedException {
        generator();
        Random randomWinPos = new Random();
        winningPosition = randomWinPos.nextInt(4);

        btnSuperHeroName0.setText(supNameListCopy.get(supNameArray[0]));
        btnSuperHeroName1.setText(supNameListCopy.get(supNameArray[1]));
        btnSuperHeroName2.setText(supNameListCopy.get(supNameArray[2]));
        btnSuperHeroName3.setText(supNameListCopy.get(supNameArray[3]));
        showImage(winningPosition);
    }

    private void checkResultCounter() {
        if(winningPosition == buttonId){
            intScore++;
            supNameListCopy.remove(supNameArray[winningPosition]);
            supImgListCopy.remove(supNameArray[winningPosition]);
        }

        numberOfQuestions++;
        textScore.setText(Integer.toString(intScore));

        try {
            setSupButton();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void generator() {
        Random randomSupName = new Random();
        for(int x = 0; x<4; x++){
            supNameArray[x] = randomSupName.nextInt(supNameListCopy.size());
        }

        if(controlDuplicity(supNameArray)){
            generator();
        }
    }

    private boolean controlDuplicity(int[] my_array){


        for (int i = 0; i < my_array.length-1; i++)
        {
            for (int j = i+1; j < my_array.length; j++)
            {
                if ((my_array[i] == my_array[j]) && (i != j))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private void superheroArray(String superheroHtml){
        Pattern imgPattern = Pattern.compile("data-lazy-src=\"(.*?)\"");
        Matcher imgMatcher = imgPattern.matcher(superheroHtml);

        Pattern namePattern = Pattern.compile(". (.*?)</h3>");
        Matcher nameMatcher = namePattern.matcher(superheroHtml);

        while(nameMatcher.find() && imgMatcher.find()){
            supNameList.add(nameMatcher.group(1));
            supImgList.add(imgMatcher.group(1));
            //Log.i("Superhero: ",nameMatcher.group(1)+ " " + imgMatcher.group(1));
        }

        supImgList.remove(54);
        supNameList.remove(54);

    }

    private void timer() {

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(Long.toString(millisUntilFinished/1000) +" s");
            }

            @Override
            public void onFinish() {
                endGame();
            }
        }.start();
    }

    private void endGame(){
        playLayout.setVisibility(View.VISIBLE);
        buttonLayout.setVisibility(View.INVISIBLE);
        textScore.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.INVISIBLE);
        superheroPic.setVisibility(View.INVISIBLE);
        float ratio = (float)intScore/(float)numberOfQuestions*100;
        btnPlay.setText("Press to play! \r\n Ratio: " + Float.toString(ratio) +"%");
        intScore=0;
        numberOfQuestions=0;
        supNameListCopy.clear();
        supImgListCopy.clear();
    }

}