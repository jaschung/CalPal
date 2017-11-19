package com.example.zyne.testgets;
//

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    static String NAMES="";
    static String CALS = "";
    Button b1;
    EditText cals;
    EditText names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        cals = (EditText)findViewById(R.id.editText2);
        names = (EditText)findViewById(R.id.editText);
        b1 = (Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CALS = cals.getText().toString();
                NAMES = names.getText().toString();
                Intent mainIntent = new Intent(MainActivity.this, EnterActivity.class);
                startActivity(mainIntent);
            }
        });

    }
}
