package com.example.zyne.testgets;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class NewActivity extends AppCompatActivity {
    private TextToSpeech textToSpeech;
    private EditText editText_text;
    private Button button;
    private RadioButton radio_en;
    private RadioButton radio_fr;
    RadioGroup radioGroup;
    TextView numCal;// = (TextView) findViewById(R.id.addCal);

    Button moreFood;
    Button reset;

    private boolean ready;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);



        reset = (Button)findViewById(R.id.resetMe);
        numCal = (TextView)findViewById(R.id.numberCal);
        String CALSME1 = MainActivity.CALS;
        String minusCals1 = fetchData.calories;
        final double fullNum1 = Double.parseDouble(CALSME1) - Double.parseDouble(minusCals1);



        moreFood = (Button)findViewById(R.id.buttonAdd);
        moreFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent veryIntent = new Intent(NewActivity.this, EnterActivity.class);
                startActivity(veryIntent);
            }
        });





        editText_text = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                Log.e("TTS", "TextToSpeech.OnInitListener.onInit...");
                printOutSupportedLanguages();
                setTextToSpeechLanguage();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainInt = new Intent(NewActivity.this, MainActivity.class);
                startActivity(mainInt);
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setTextToSpeechLanguage();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numCal.setText(Double.toString(fullNum1));

                speakOut();

            }
        });
    }
    private void printOutSupportedLanguages()  {
        // Supported Languages
        Set<Locale> supportedLanguages = textToSpeech.getAvailableLanguages();
        if(supportedLanguages!= null) {
            for (Locale lang : supportedLanguages) {
                Log.e("TTS", "Supported Language: " + lang);
            }
        }
    }


    @Override
    public void onPause() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }

    private void speakOut() {
        String NAMEME = MainActivity.NAMES;
        String CALSME = MainActivity.CALS;
        String minusCals = fetchData.calories;
        double fullNum = Double.parseDouble(CALSME) - Double.parseDouble(minusCals);
//        int realNum = (int)fullNum;
        String fruit = fetchDataWatson.TYPEFOOD;

        if (!ready) {
            Toast.makeText(this, "Text to Speech not ready", Toast.LENGTH_LONG).show();
            return;
        }
        // Text to Speak
        List<String> listMe = new ArrayList<>(6);


        String string0 = ("Hello " + NAMEME + " You now have " + Double.toString(fullNum) + "calories Left.   Hope you enjoyed your " + fruit);
        listMe.add(string0);
        String string1 = ("Hey there " + NAMEME + " You now have " + Double.toString(fullNum) + "calories Left.  I hope your " + fruit + " was delicious.");
        listMe.add(string1);
        String string2  = ("Whats up  " + NAMEME + " You now have "  + Double.toString(fullNum) + "calories Left.   hope that  " + fruit + " was blessed.");
        listMe.add(string2);
        String string3    =   ("Hi " + NAMEME + " You now have "  + Double.toString(fullNum) + "calories Left.   I really hope that " + fruit + " was tasty");
        listMe.add(string3);
        String string4   = ("Hey " + NAMEME + " You now have "  + Double.toString(fullNum) + "calories Left.   I guarantee that" + fruit + "was nice and filling");
        listMe.add(string4);
        String string5    =("Wuttup " + NAMEME + " You now have "  + Double.toString(fullNum) + "calories Left.   Hope your day was just as amazing as your  " + fruit);
        listMe.add(string5);

//        int hey = new Random().nextInt(6);

//        String newString = listMe.get(new Random().nextInt(6));

        String toSpeak = listMe.get(new Random().nextInt(6));
        Toast.makeText(this, toSpeak, Toast.LENGTH_SHORT).show();
        // A random String (Unique ID).
        String utteranceId = UUID.randomUUID().toString();
        textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }

    private Locale getUserSelectedLanguage() {
//        int checkedRadioId = this.radioGroup.getCheckedRadioButtonId();
//        if (checkedRadioId == R.id.radio_en) {
            return Locale.ENGLISH;
//        } else if (checkedRadioId == R.id.radio_fr) {
//            return Locale.FRANCE;
//        }
//        return null;
    }


    private void setTextToSpeechLanguage() {
        Locale language = this.getUserSelectedLanguage();
        if (language == null) {
            this.ready = false;
            Toast.makeText(this, "Not language selected", Toast.LENGTH_SHORT).show();
            return;
        }
        int result = textToSpeech.setLanguage(language);
        if (result == TextToSpeech.LANG_MISSING_DATA) {
            this.ready = false;
            Toast.makeText(this, "Missing language data", Toast.LENGTH_SHORT).show();
            return;
        } else if (result == TextToSpeech.LANG_NOT_SUPPORTED) {
            this.ready = false;
            Toast.makeText(this, "Language not supported", Toast.LENGTH_SHORT).show();
            return;
        } else {
            this.ready = true;
            Locale currentLanguage = textToSpeech.getVoice().getLocale();
            Toast.makeText(this, "Language " + currentLanguage, Toast.LENGTH_SHORT).show();
        }
    }
}
