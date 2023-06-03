package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    Button SignuP, SigniN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SignuP= (Button)findViewById(R.id.Signupss);
        SigniN= (Button)findViewById(R.id.Signinss);

        SignuP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {startActivity(new Intent(MainActivity2.this, SignUp.class));}
        });

        SigniN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {startActivity(new Intent(MainActivity2.this, SignIn.class));}
        });

    }
}