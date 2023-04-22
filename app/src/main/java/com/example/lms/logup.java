package com.example.lms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.utilities.Validation;

import java.util.HashMap;

public class logup extends AppCompatActivity {

    EditText names, mails, usernames, passwords;
    Button registerup;
    TextView toLogin;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logup);

        names= findViewById(R.id.name);
        mails= findViewById(R.id.mail);
        usernames= findViewById(R.id.username);
        passwords= findViewById(R.id.password);
        registerup= findViewById(R.id.btn_Reg);
        toLogin= findViewById(R.id.already);

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(logup.this, login.class));
                finish();
            }
        });

        registerup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database= FirebaseDatabase.getInstance();
                reference= database.getReference("users");

                String name= names.getText().toString();
                String email= mails.getText().toString();
                String username= usernames.getText().toString();
                String password= passwords.getText().toString();

                HelperClass helperClass= new HelperClass(name, email, username, password);
                reference.child(username).setValue(helperClass);

                Toast.makeText(logup.this, "SignUp Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(logup.this, login.class));
            }
        });
    }
}