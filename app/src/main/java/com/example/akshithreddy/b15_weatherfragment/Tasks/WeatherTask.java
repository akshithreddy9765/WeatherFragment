package com.example.akshithreddy.b15_weatherfragment.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.akshithreddy.b15_weatherfragment.Interfaces.WeatherTaskCallback;
import com.example.akshithreddy.b15_weatherfragment.Parser.Info;
import com.example.akshithreddy.b15_weatherfragment.Parser.ParserUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by akshithreddy on 9/29/2015.
 */
public class WeatherTask extends AsyncTask<String,Void,Info> {
    private WeatherTaskCallback weatherTaskCallback;

    public WeatherTask(WeatherTaskCallback weatherTaskCallback) {
        this.weatherTaskCallback = weatherTaskCallback;
    }

    private final String TAG = WeatherTask.class.getSimpleName();

    //runs in non ui thread
    @Override
    protected Info doInBackground(String... params) {

        String link = params[0];

        Log.i(TAG, "link=" + link);
        InputStream is = null;
        HttpURLConnection conn = null;

        try {
            URL url = new URL(link);

            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(70000);
            conn.setRequestMethod("GET");
            conn.connect();

            is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String data = null;
            String rawJson = "";


            while ((data = reader.readLine()) != null) {
                rawJson += data + "\n";
            }
            Log.i(TAG, "Response from the server is" + rawJson);
            Info info = ParserUtil.getParsedData(rawJson);
            return info;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (conn != null) {
                    conn.disconnect();
                }

            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Info info) {
        if (weatherTaskCallback != null) {
            weatherTaskCallback.getWeatherData(info);

        }
    }
}
