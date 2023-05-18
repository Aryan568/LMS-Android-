package com.example.lms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class fragment1 extends Fragment {

    EditText bookid, title, author, category, studentid,name;
    Spinner CourseSpin, YearSpin;
    Button issue, clear;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_fragment1, container, false);

        bookid= v.findViewById(R.id.etBookID);
        title= v.findViewById(R.id.etTitle);
        author= v.findViewById(R.id.etAuthor);
        category= v.findViewById(R.id.etCategory);
        studentid= v.findViewById(R.id.etStudentID);
        name= v.findViewById(R.id.etName);
        CourseSpin= v.findViewById(R.id.CourseSpin);
        YearSpin= v.findViewById(R.id.YearSpin);
        issue= v.findViewById(R.id.btnIssue);
        clear= v.findViewById(R.id.btnClear);

        String course[]={"course","MCA", "MTECH", "BCOM", "BBA", "MBA", "BSc", "MSc"};
        String year[]={"year","1st", "2nd", "3rd", "4th"};

        ArrayAdapter<String> course1 = new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, course);
        CourseSpin.setAdapter(course1);

        ArrayAdapter<String> year1= new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, year);
        YearSpin.setAdapter(year1);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookid.setText("");
                title.setText("");
                author.setText("");
                category.setText("");
                studentid.setText("");
                name.setText("");
                CourseSpin.setSelection(0);
                YearSpin.setSelection(0);
            }
        });
        return v;
    }
}