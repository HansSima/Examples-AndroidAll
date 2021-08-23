package com.example.allexamples.examples;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.allexamples.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class GetInternetResource extends AppCompatActivity {

    ImageView downloadedImage;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.back_on_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

   /* public class DownloadContent extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... urlParam) {
            //Log.i("Parametr: ", kokot[0]);
            String result = "";
            URL url;
            HttpURLConnection connection = null;
            try {
                url = new URL(urlParam[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream inStream = connection.getInputStream();
                InputStreamReader inStrReader = new InputStreamReader(inStream);
                int data = inStrReader.read();
                while(data != -1){
                    char reader = (char) data;
                    result += reader;
                    data = inStrReader.read();
                }
            }catch(Exception e){
                e.printStackTrace();
                return "Cteni selhalo";
            }
            return result;
        }
    }*/

    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urlParam) {
            try {

                URL url = new URL(urlParam[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inStream);
                return myBitmap;
            }catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_internet_resource);
        downloadedImage = findViewById(R.id.downloadedImage);

        /*DownloadContent newDownloadClass = new DownloadContent();
        String vracenHodnota = null;
        try {
            vracenHodnota = newDownloadClass.execute("https://www.ecowebhosting.co.uk/").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("Stranka: ", vracenHodnota);*/
    }

    public void showImage(View view){

        Bitmap vracenyObrazek = null;
        GetInternetResource.DownloadImage newDownloadImageClass = new GetInternetResource.DownloadImage();
        vracenyObrazek = null;
        try {
            vracenyObrazek = newDownloadImageClass.execute("https://www.playzone.cz/sites/default/files/styles/290x330_profile_image/public/profile-images/290310/Dickhead.jpg?itok=qCKzHgb6").get();
            downloadedImage.setImageBitmap(vracenyObrazek);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}