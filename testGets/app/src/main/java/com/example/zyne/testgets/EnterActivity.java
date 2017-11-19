package com.example.zyne.testgets;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.HttpURLConnection;

public class EnterActivity extends AppCompatActivity {
    Button res;

    Button button;
    ImageView imageView;
    Bitmap newBitMap;
    String name;
    RequestQueue rq;

    int TOTALCALORIES = 0;
    public static String CALORIES;
    public static String TYPE_OF_FOOD;
    FileOutputStream hello;

    Uri selectedImage;
    private Uri imageUri;
    OutputStream hi;
    public  static TextView data;
    Button bt2;
    TextView textVieww;


    static final int CAM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
//        HttpURLConnection connection;

//        data = (TextView) findViewById(R.id.textView);



        res = (Button)findViewById(R.id.buttonR);
        bt2=(Button)findViewById(R.id.addCal);
        button = (Button) findViewById(R.id.snap);
        imageView = (ImageView) findViewById(R.id.imageView);


        textVieww=(TextView)findViewById(R.id.textView);


        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newF = new Intent(EnterActivity.this, MainActivity.class);
                startActivity(newF);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, CAM_REQUEST);
                }
                bt2.setVisibility(View.VISIBLE);
                textVieww.setVisibility(View.INVISIBLE);



//                try {
//                    MainActivity.goingThrough();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

//
                fetchDataWatson process = new fetchDataWatson();
                process.execute();

            }

        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent1 = new Intent(EnterActivity.this, NewActivity.class);
                startActivity(myIntent1);

            }
        });






    }


    private static Bitmap codec(Bitmap src, Bitmap.CompressFormat format,
                                int quality) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        src.compress(format, quality, os);

        byte[] array = os.toByteArray();
        return BitmapFactory.decodeByteArray(array, 0, array.length);
    }


    private File getFile() {

        File folder = new File("sdcard/camera_app");

        if (!folder.exists()) {
            folder.mkdir();
        }

        File image_file = new File(folder, "cam_image.jpg");
        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();


            Bitmap imageBitMap = (Bitmap) data.getExtras().get("data");

//            imageView.setImageBitmap(imageBitMap);


            newBitMap = imageBitMap;

            Bitmap bJPGcompress = codec(newBitMap, Bitmap.CompressFormat.PNG, 100);

            imageView.setImageBitmap(bJPGcompress);
//            MediaStore.Images.Media.insertImage(getContentResolver(), imageBitMap, "Yup" ,"Description");
//            newBitMap.compress(Bitmap.CompressFormat.PNG, 100,hi);
        }

    }
}
