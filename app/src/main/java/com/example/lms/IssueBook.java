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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IssueBook extends Fragment {

EditText bookid, title, author, category, studentid, name;
Spinner CourseSpin, YearSpin;
Button issue, clear;

    public IssueBook() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_issue_book, container, false);
        bookid= view.findViewById(R.id.etBookID);
        title= view.findViewById(R.id.etTitle);
        author= view.findViewById(R.id.etAuthor);
        category= view.findViewById(R.id.etCategory);
        studentid= view.findViewById(R.id.etStudentID);
        name= view.findViewById(R.id.etName);
        CourseSpin= view.findViewById(R.id.CourseSpin);
        YearSpin= view.findViewById(R.id.YearSpin);
        issue= view.findViewById(R.id.btnIssue);
        clear= view.findViewById(R.id.btnClear);

        String course[]={"course","MCA", "MTECH", "BCOM", "BBA", "MBA", "BSc", "MSc"};
        String year[]={"year","1st", "2nd", "3rd", "4th"};

        ArrayAdapter<String> course1 = new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, course);
        CourseSpin.setAdapter(course1);

        ArrayAdapter<String> year1= new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, year);
        YearSpin.setAdapter(year1);


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearDetails();
            }
        });

        issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                issueDetails();
            }
        });
        return view;
    }

    private void issueDetails() {
        if (bookid.getText().toString().equals("")){
            bookid.setError("Enter ID");
        } else if (title.getText().toString().equals("")) {
            title.setError("Enter First Name");
        } else if (author.getText().toString().equals("")) {
            author.setError("Enter First Name");
        } if (category.getText().toString().equals("")){
            category.setError("Enter ID");
        } else if (studentid.getText().toString().equals("")) {
            studentid.setError("Enter First Name");
        } else if (name.getText().toString().equals("")) {
            name.setError("Enter First Name");
        } else if (CourseSpin.getSelectedItem().toString().equals("")) {
            ((TextView)CourseSpin.getSelectedView()).setError("Select Course");
        } else if (YearSpin.getSelectedItem().toString().equals("")) {
            ((TextView)YearSpin.getSelectedView()).setError("Select Year");
        }
        else {
            String isuBook= bookid.getText().toString();
            String isuTitle=title.getText().toString();
            String isuAuthor= author.getText().toString();
            String isuCategory= category.getText().toString();
            String isuStudentid= studentid.getText().toString();
            String isuName= name.getText().toString();
            String isuCourseSpin=CourseSpin.getSelectedItem().toString();
            String isuYearSpin= YearSpin.getSelectedItem().toString();

            DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
            DatabaseReference child= reference.child("isuBook");
            DatabaseReference IsuDB= child.child(isuBook);

            IsuDB.child("BookID").setValue(isuBook);
            IsuDB.child("Title").setValue(isuTitle);
            IsuDB.child("Author").setValue(isuAuthor);
            IsuDB.child("Category").setValue(isuCategory);
            IsuDB.child("StudentID").setValue(isuStudentid);
            IsuDB.child("Stu Name").setValue(isuName);
            IsuDB.child("Course").setValue(isuCourseSpin);
            IsuDB.child("Year").setValue(isuYearSpin);

            Toast.makeText(getContext(), "Issued successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearDetails() {
        bookid.setText("");
        title.setText("");
        author.setText("");
        category.setText("");
        studentid.setText("");
        name.setText("");
        CourseSpin.setSelection(0);
        YearSpin.setSelection(0);
    }
}