package com.example.lms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SubmitBooks extends AppCompatActivity {

    EditText bookid, title, author, category, studentid,name;
    Spinner CourseSpins, YearSpins;
    Button submit, clear;
    View v;
    DatabaseReference reference, reference1, reference2, reference3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_books);

        bookid= findViewById(R.id.etBookID);
        title= findViewById(R.id.etTitle);
        author= findViewById(R.id.etAuthor);
        category= findViewById(R.id.etCategory);
        studentid= findViewById(R.id.etStudentID);
        name= findViewById(R.id.etName);
        CourseSpins= findViewById(R.id.CourseSpin);
        YearSpins= findViewById(R.id.YearSpin);
        submit= findViewById(R.id.btnIssue);
        clear= findViewById(R.id.btnClear);

        String course[]={"course","MCA", "MTECH", "BCOM", "BBA", "MBA", "BSc", "MSc"};
        String year[]={"year","1st", "2nd", "3rd", "4th"};

        ArrayAdapter<String> course1 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, course);
        CourseSpins.setAdapter(course1);

        ArrayAdapter<String> year1= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, year);
        YearSpins.setAdapter(year1);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookid.setText("");
                title.setText("");
                author.setText("");
                category.setText("");
                studentid.setText("");
                name.setText("");
                CourseSpins.setSelection(0);
                YearSpins.setSelection(0);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validBID() | !validTitle() | !validAuthor() | !validCategory() | !validSID() | !validName() | !validCourse() | !validYear()){

                }
                else chkDetailsDB();
            }
        });
    }

    private boolean validBID() {
        String val= bookid.getText().toString();
        if (val.isEmpty()){
            bookid.setError("BookId!!!");
            return false;
        }
        else {
            bookid.setError(null);
            return true;
        }
    }

    private boolean validTitle() {
        String val= title.getText().toString();
        if (val.isEmpty()){
            title.setError("Title!!!");
            return false;
        }
        else {
            title.setError(null);
            return true;
        }
    }

    private boolean validAuthor() {
        String val= author.getText().toString();
        if (val.isEmpty()){
            author.setError("Author!!!");
            return false;
        }
        else {
            author.setError(null);
            return true;
        }
    }

    private boolean validCategory() {
        String val= category.getText().toString();
        if (val.isEmpty()){
            category.setError("Category!!!");
            return false;
        }
        else {
            category.setError(null);
            return true;
        }
    }

    private boolean validSID() {
        String val= studentid.getText().toString();
        if (val.isEmpty()){
            studentid.setError("StudentId!!!");
            return false;
        }
        else {
            studentid.setError(null);
            return true;
        }
    }

    private boolean validName() {
        String val= name.getText().toString();
        if (val.isEmpty()){
            name.setError("Name!!!");
            return false;
        }
        else {
            name.setError(null);
            return true;
        }
    }

    private boolean validCourse() {
        String val= CourseSpins.getSelectedItem().toString();
        if (val.equals("course")){
            ((TextView)CourseSpins.getSelectedView()).setError("Course!!!");
            return false;
        }
        else {
            ((TextView)CourseSpins.getSelectedView()).setError(null);
            return true;
        }
    }

    private boolean validYear() {
        String val= YearSpins.getSelectedItem().toString();
        if (val.equals("year")){
            ((TextView)YearSpins.getSelectedView()).setError("Year!!!");
            return false;
        }
        else {
            ((TextView)YearSpins.getSelectedView()).setError(null);
            return true;
        }
    }

    private void chkDetailsDB() {

        String isuBook= bookid.getText().toString();
        String isuTitle=title.getText().toString();
        String isuAuthor= author.getText().toString();
        String isuCategory= category.getText().toString();
        String isuStudentid= studentid.getText().toString();
        String isuName= name.getText().toString();
        String isuCourseSpin= CourseSpins.getSelectedItem().toString();
        String isuYearSpin= YearSpins.getSelectedItem().toString();

        reference= FirebaseDatabase.getInstance().getReference("IssuedBook");
        Query chkBookDB= reference.orderByChild("BookID").equalTo(isuBook);

        chkBookDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    bookid.setError(null);
                    String bookDB= snapshot.child(isuBook).child("BookID").getValue(String.class);
                    String titleDB= snapshot.child(isuBook).child("Title").getValue(String.class);
                    String authorDB= snapshot.child(isuBook).child("Author").getValue(String.class);
                    String categoryDB= snapshot.child(isuBook).child("Category").getValue(String.class);
                    String studentDB= snapshot.child(isuStudentid).child("StudentID").getValue(String.class);
                    String nameDB= snapshot.child(isuStudentid).child("Name").getValue(String.class);
                    String courseDB= snapshot.child(isuStudentid).child("Course").getValue(String.class);
                    String yearDB= snapshot.child(isuStudentid).child("Year").getValue(String.class);

                    if (titleDB.equals(isuTitle)){
                        bookid.setError(null);
                        if (authorDB.equals(isuAuthor)){
                            bookid.setError(null);
                            if (categoryDB.equals(isuCategory)){
                                bookid.setError(null);
                                if (studentDB.equals(isuStudentid)){
                                    bookid.setError(null);
                                    if (nameDB.equals(isuName)){
                                        deleteDetailsDB();
//                                        if (courseDB.equals(isuCourseSpin)){
//                                            bookid.setError(null);
//                                            if (yearDB.equals(isuYearSpin)){
//                                                bookid.setError(null);
//
//                                            } else {
//                                                ((TextView)YearSpins.getSelectedView()).setError("not exist");
//                                            }
//                                        } else {
//                                            ((TextView)CourseSpins.getSelectedView()).setError("not exist");
//                                        }
                                    } else{
                                        name.setError("not exist");
                                    }
                                } else{
                                    studentid.setError("not exist");
                                }
                            } else{
                                category.setError("not exist");
                            }
                        } else{
                            author.setError("not exist");
                        }
                    } else{
                        title.setError("not exist");
                    }
                } else{
                    bookid.setError("not exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void deleteDetailsDB() {
        String isuBook= bookid.getText().toString();

        reference= FirebaseDatabase.getInstance().getReference();
        reference1= reference.child("IssuedBook").child(isuBook);

        reference1.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                bookid.setText("");
                title.setText("");
                author.setText("");
                category.setText("");
                studentid.setText("");
                name.setText("");
                CourseSpins.setSelection(0);
                YearSpins.setSelection(0);
            }
        });
    }
}