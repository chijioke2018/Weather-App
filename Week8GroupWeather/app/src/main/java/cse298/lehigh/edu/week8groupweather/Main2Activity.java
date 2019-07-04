package cse298.lehigh.edu.week8groupweather;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SharedPreferences myPreferences;
        myPreferences = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        String location = myPreferences.getString("place", "");

        WeatherTask task = new WeatherTask();
        //dont forget to add your api key
        task.execute("http://api.openweathermap.org/data/2.5/weather?q="+ location+"&APPID=9ead70dcaa0849477b1ed1a4f8811629");
        //task.execute("http://api.openweathermap.org/data/2.5/weather?q=");
    }

    public class WeatherTask  extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result ="";

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();

                while (data !=-1){
                    char curr = (char) data;
                    result += curr;
                    data = inputStreamReader.read();
                }
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s){

            super.onPostExecute(s);
            if (s != null) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String weather = jsonObject.getString("weather");
                    //convert string to json array .. note the '[' ']'
                    JSONArray main = new JSONArray(weather);
                    //convert main content into json objects
                    for(int i = 0; i < main.length(); i++){
                        JSONObject obj = main.getJSONObject(i);
                        String description = obj.getString("description");
                        Log.i("API result", description);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }

    }
}
