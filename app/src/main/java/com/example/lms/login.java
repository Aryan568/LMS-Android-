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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.utilities.Validation;

import java.util.ArrayList;
import java.util.Objects;

public class login extends AppCompatActivity {
    EditText usernm, passin;
    Button login;
    TextView toRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernm= findViewById(R.id.edt5);
        passin= findViewById(R.id.edtp1);
        login= findViewById(R.id.btn_Dash);
        toRegister= findViewById(R.id.toReg);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validUsernm() | !validPasswrd()) {

                }
                else {
                    chkUser();
                }
            }
        });

        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, logup.class));
            }
        });
    }

    public boolean validUsernm(){
        String val= usernm.getText().toString();
        if (val.isEmpty()){
            usernm.setError("Username!!!!!!!!");
            return false;
        }
        else{
            usernm.setError(null);
            return true;
        }
    }

    public boolean validPasswrd(){
        String val= passin.getText().toString();
        if (val.isEmpty()){
            passin.setError("Password!!!!!!!!");
            return false;
        }
        else{
            passin.setError(null);
            return true;
        }
    }

    public void chkUser(){
        String user= usernm.getText().toString().trim();
        String pass= passin.getText().toString().trim();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");

        Query chkUserData= reference.orderByChild("username").equalTo(user);

        chkUserData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    usernm.setError(null);
                    String passwordFromDB = snapshot.child(user).child("password").getValue(String.class);

                    if (passwordFromDB.equals(pass)) {
                        usernm.setError(null);
//                        String nameFromDB = snapshot.child(user).child("name").getValue(String.class);
//                        String emailFromDB = snapshot.child(user).child("email").getValue(String.class);
                        String usernameFromDB = snapshot.child(user).child("username").getValue(String.class);
                        Intent intent = new Intent(login.this, Dashboard.class);
//                        intent.putExtra("name", nameFromDB);
//                        intent.putExtra("email", emailFromDB);
//                        intent.putExtra("username", usernameFromDB);
//                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);}
                    else {
                        passin.setError("Invalid");
                        passin.requestFocus();
                    }
                } else {
                    usernm.setError("User does not exist");
                    usernm.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}