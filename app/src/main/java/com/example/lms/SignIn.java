package com.example.lms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
    EditText usernam, passwor;
    Button signInd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernam= findViewById(R.id.signin_username);
        passwor= findViewById(R.id.signin_password);
        signInd= findViewById(R.id.signinsss);

        signInd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usernam.getText().toString().equals("")){
                    usernam.setError("Enter username");
                } else if (passwor.getText().toString().equals("")) {
                    passwor.setError("Enter password");
                }

                else {
                    String usernamess= usernam.getText().toString();
                    String passwords= passwor.getText().toString();

                    DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Librarians");
                    Query chkLibrarianData= reference.orderByChild("Username").equalTo(usernamess);

                    chkLibrarianData.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                usernam.setError(null);
                                String passFromDB = snapshot.child(usernamess).child("Password").getValue(String.class);

                                assert passFromDB != null;
                                if (passFromDB.equals(passwords)) {
                                    usernam.setError(null);
                                    String usernameFromDB = snapshot.child(usernamess).child("Username").getValue(String.class);
                                    startActivity(new Intent(SignIn.this, Dashboard.class));
                                } else {
                                    passwor.setError("Invalid");
                                    passwor.requestFocus();
                                }
                            } else {
                                usernam.setError("User does not exist");
                                usernam.requestFocus();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}