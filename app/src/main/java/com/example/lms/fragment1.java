package com.example.lms;

import android.os.Bundle;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class fragment1 extends Fragment {

    EditText bookid, title, author, category, studentid,name;
    Spinner CourseSpins, YearSpins;
    Button issue, clear;
    View v;
    DatabaseReference reference, reference1, reference2, reference3;
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
        CourseSpins= v.getRootView().findViewById(R.id.CourseSpin);
        YearSpins= v.findViewById(R.id.YearSpin);
        issue= v.findViewById(R.id.btnIssue);
        clear= v.findViewById(R.id.btnClear);

        String course[]={"course","MCA", "MTECH", "BCOM", "BBA", "MBA", "BSc", "MSc"};
        String year[]={"year","1st", "2nd", "3rd", "4th"};

        ArrayAdapter<String> course1 = new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, course);
        CourseSpins.setAdapter(course1);

        ArrayAdapter<String> year1= new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, year);
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

        issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validBID() | !validTitle() | !validAuthor() | !validCategory() | !validSID() | !validName() | !validCourse() | !validYear()){

                }
                else chkDetailsDB();
            }
        });
        return v;
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

        reference= FirebaseDatabase.getInstance().getReference("id");
        Query chkBookDB= reference.orderByChild("bookno").equalTo(isuBook);

        reference1= FirebaseDatabase.getInstance().getReference("sID");
        Query chkStudentDB= reference1.orderByChild("ID").equalTo(isuStudentid);

        reference2= FirebaseDatabase.getInstance().getReference();
        DatabaseReference child= reference2.child("IssuedBook");
        DatabaseReference issueDB= child.child(isuBook);

//        reference3= FirebaseDatabase.getInstance().getReference();
//        DatabaseReference childs= reference3.child("Issuer");
        DatabaseReference issueDBS= child.child(isuStudentid);

        chkBookDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    bookid.setError(null);
                    String bookDB= snapshot.child(isuBook).child("bookno").getValue(String.class);
                    String titleDB= snapshot.child(isuBook).child("title").getValue(String.class);
                    String authorDB= snapshot.child(isuBook).child("author").getValue(String.class);
                    String categoryDB= snapshot.child(isuBook).child("category").getValue(String.class);
                    if (titleDB.equals(isuTitle)){
                        bookid.setError(null);
                        if (authorDB.equals(isuAuthor)){
                            bookid.setError(null);
                            if (categoryDB.equals(isuCategory)){
                                bookid.setError(null);

                                issueDB.child("BookID").setValue(isuBook);
                                issueDB.child("Title").setValue(isuTitle);
                                issueDB.child("Author").setValue(isuAuthor);
                                issueDB.child("Category").setValue(isuCategory);
                            }
                            else{
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

        chkStudentDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    studentid.setError(null);
                    String studentDB= snapshot.child(isuStudentid).child("ID").getValue(String.class);
                    String nameDB= snapshot.child(isuStudentid).child("F Name").getValue(String.class);
                    String courseDB= snapshot.child(isuStudentid).child("Course").getValue(String.class);
                    String yearDB= snapshot.child(isuStudentid).child("Year").getValue(String.class);
                    if (nameDB.equals(isuName)){
                        studentid.setError(null);
                        issueDBS.child("StudentID").setValue(isuStudentid);
                        issueDBS.child("Name").setValue(isuName);
                        issueDBS.child("Course").setValue(isuCourseSpin);
                        issueDBS.child("Year").setValue(isuYearSpin);
//                        if (courseDB.equals(isuCourseSpin)){
//                            studentid.setError(null);
//                            if (yearDB.equals(isuYearSpin)){
//                                studentid.setError(null);
//
//                            } else{
//                                ((TextView)YearSpins.getSelectedView()).setError("not exist");
//                            }
//                        } else{
//                            ((TextView)CourseSpins.getSelectedView()).setError("not exist");
//                        }
                    } else{
                        name.setError("not exist");
                    }
                } else{
                    studentid.setError("not exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}