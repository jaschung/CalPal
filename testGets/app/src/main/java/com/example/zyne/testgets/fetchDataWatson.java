package com.example.zyne.testgets;

/**
 * Created by Zyne on 2017-11-18.
 */
import android.os.AsyncTask;

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
import android.util.Log;
//Fix this class into
public class fetchDataWatson extends AsyncTask<Void,Void,Void> {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    String helloDad = "ok";
    String BANANA = "Banana";
    public static String TYPEFOOD = "";
    @Override
    protected Void doInBackground(Void... voids) {

        try {
//            https://api.nutritionix.com/v1_1/search/cheddar%20cheese[YOURID]&appKey=[YOURKEY]
//            String user = "http://www.seriouseats.com/recipes/assets_c/2015/05/20150511-scrambled-eggs-vicky-wasik-17-thumb-1500xauto-423060.jpg";
//            String pass = "&classifier_ids=Breakfast_998219245&version=2016-05-20";
            URL url = new URL("https://watson-api-explorer.mybluemix.net/visual-recognition/api/v3/classify?api_key=8d7aced8efa9ce11cca985d203dce5989cc20148&url=https%3A%2F%2Fimg.purch.com%2Fh%2F1000%2FaHR0cDovL3d3dy5saXZlc2NpZW5jZS5jb20vaW1hZ2VzL2kvMDAwLzA2NS8xNDkvb3JpZ2luYWwvYmFuYW5hcy5qcGc%3D&classifier_ids=default&version=2016-05-20");

            //url+=URLEncoder.encode("http://www.seriouseats.com/recipes/assets_c/2015/05/20150511-scrambled-eggs-vicky-wasik-17-thumb-1500xauto-423060.jpg", "UTF-8");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject JA = new JSONObject(data);
            JSONArray imagesArr = JA.getJSONArray("images");
            JSONObject objOne = imagesArr.getJSONObject(0);

            JSONArray objClassifiers = objOne.getJSONArray("classifiers");
            JSONObject objClasses = objClassifiers.getJSONObject(0);
            JSONArray classesArr = objClasses.getJSONArray("classes");
            JSONObject finalObj = classesArr.getJSONObject(0);
            String foodString = (String)finalObj.get("class");
            helloDad = foodString;
            Log.i("WHAT  ", foodString);
            TYPEFOOD = foodString;


            fetchData process2 = new fetchData();
            process2.execute();



//            dataParsed = dataParsed + singleParsed +"\n" ;


//            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
//
//    @Override
//    protected void onPostExecute(Void aVoid) {
//        super.onPostExecute(aVoid);
//        MainActivity.data.setText(helloDad);
//
//    }
}