package com.example.allexamples.examples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.allexamples.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class JSONExample extends AppCompatActivity {

    private String apiKey = "5c3b8bfd2fbfedaec4f61b76a1e199af";
    public String weatherStatus = "";

    EditText city;
    TextView weatherInCity;
    //http://api.openweathermap.org/data/2.5/weather?q=Nov%C3%A1%20Ves,cz&APPID=5c3b8bfd2fbfedaec4f61b76a1e199af

    public class JsonTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... url) {
            String result = "";
            URL jsonUrl;
            HttpURLConnection connection = null;

            try {

                jsonUrl = new URL(url[0]);
                connection = (HttpURLConnection)jsonUrl.openConnection();
                InputStream inStream = connection.getInputStream();
                InputStreamReader inReader = new InputStreamReader(inStream);
                int data = inReader.read();

                while(data != -1){
                    char reader = (char)data;
                    result += reader;
                    data = inReader.read();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject weather = new JSONObject(result);
                String weatherInfo = weather.getString("weather");
                Log.i("Weather:", weatherInfo);
                JSONArray jsonArray = new JSONArray(weatherInfo);

                for (int i= 0; i<jsonArray.length(); i++){
                    JSONObject obj = jsonArray.getJSONObject(i);
                    Log.i("Object:", obj.getString("main"));
                    weatherStatus = obj.getString("description");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            weatherInCity.setText(weatherStatus);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_demo);

        city = findViewById(R.id.editTextCity);
        weatherInCity = findViewById(R.id.weatherStatus);
    }

    private void weatherApi (String url){
        String weatherString = "";
        JsonTask myJson = new JsonTask();
        try {
            weatherString = myJson.execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void showWeather(View view) {
        String cityString = city.getText().toString();
        String weatherUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + cityString + "&APPID=" + apiKey;
        weatherApi (weatherUrl);
    }
}