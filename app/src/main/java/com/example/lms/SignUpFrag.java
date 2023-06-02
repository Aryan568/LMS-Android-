package com.example.lms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpFrag extends Fragment {

    EditText username, mail, phone, password;
    Button signUp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_sign_up, container, false);

        username= v.findViewById(R.id.signup_username);
        mail= v.findViewById(R.id.signup_email);
        phone= v.findViewById(R.id.signup_phone);
        password= v.findViewById(R.id.signup_password);
        signUp= v.findViewById(R.id.signup_button);

        return v;
    }
}