package com.example.zyne.testgets;
import android.os.AsyncTask;
import android.util.Log;

//import org.json.JSONArray;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class fetchData extends AsyncTask<Void,Void,Void> {
    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    static String calories;
    String NEW_FOOD = fetchDataWatson.TYPEFOOD;



    @Override


    protected Void doInBackground(Void... voids) {
//        Runnable networkThread = new Runnable() {
//            public void run() {



        try {
            String user = "7ffa11b1";
            String pass = "ad4de70ff471c9505ca6e784a317aefc";
            URL url = new URL("https://api.nutritionix.com/v1_1/search/banana?fields=item_name%2Citem_id%2Cbrand_name%2Cnf_calories%2Cnf_total_fat&appId=" + user + "&appKey=" + pass);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject JA = new JSONObject(data);
            JSONArray hitsArr = JA.getJSONArray("hits");
            JSONObject subGroup = hitsArr.getJSONObject(0);
            JSONObject fieldsObj = subGroup.getJSONObject("fields");
            double calNum = (double)fieldsObj.get("nf_calories");

            calories = Double.toString(calNum);

            Log.i("OMG PLEASE DAD! THIS IS IT: ", calories);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//            }


//        };
//        Thread thread = new Thread(networkThread);
//        thread.start();

        return null;
    }


}