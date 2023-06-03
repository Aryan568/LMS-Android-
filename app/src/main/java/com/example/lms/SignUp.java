package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText username, mail, phone, password;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username= findViewById(R.id.signup_username);
        mail= findViewById(R.id.signup_email);
        phone= findViewById(R.id.signup_phone);
        password= findViewById(R.id.signup_password);
        signUp= findViewById(R.id.signup_button);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (username.getText().toString().equals("")){
                    username.setError("Enter username");
                } else if (mail.getText().toString().equals("")) {
                    mail.setError("Enter email");
                } else if (phone.getText().toString().equals("")) {
                    phone.setError("Enter phone no.");
                } else if (password.getText().toString().equals("")) {
                    password.setError("Enter password");
                }

                else {

                    String unames= username.getText().toString();
                    String emails= mail.getText().toString();
                    String phones= phone.getText().toString();
                    String passwords= password.getText().toString();

                    DatabaseReference reff= FirebaseDatabase.getInstance().getReference("Librarians").child(unames);

                    reff.child("Username").setValue(unames);
                    reff.child("Email").setValue(emails);
                    reff.child("Phone").setValue(phones);
                    reff.child("Password").setValue(passwords);

                    Toast.makeText(SignUp.this, "Student added successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUp.this, SignIn.class));

                }
            }
        });
    }
}