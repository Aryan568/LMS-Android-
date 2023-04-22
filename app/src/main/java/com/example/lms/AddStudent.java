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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddStudent extends AppCompatActivity {
    Spinner s1,s2;
    EditText StudentID, FirstName, LastName;
    Button save, update, delete, clear, search;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        StudentID= findViewById(R.id.stID);
        FirstName= findViewById(R.id.fName);
        LastName= findViewById(R.id.lName);
        save= findViewById(R.id.b6);
        update= findViewById(R.id.b7);
        delete= findViewById(R.id.b8);
        clear= findViewById(R.id.b9);
        search= findViewById(R.id.b10);
        s1= findViewById(R.id.CourseSpin);
        s2= findViewById(R.id.YearSpin);

        String course[]={"course","MCA", "MTECH", "BCOM", "BBA", "MBA", "BSc", "MSc"};
        String year[]={"year","1st", "2nd", "3rd", "4th"};

        ArrayAdapter<String> course1 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, course);
        s1.setAdapter(course1);

        ArrayAdapter<String> year1= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, year);
        s2.setAdapter(year1);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudents();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStudent();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteStudent();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearStudent();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchStudent();
            }
        });

    }

    private void searchStudent() {
        String idAdd= StudentID.getText().toString();

        reference= FirebaseDatabase.getInstance().getReference();
        DatabaseReference child= reference.child("sID");
        DatabaseReference UserDB= child.child(idAdd);

        UserDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    FirstName.setText(snapshot.child("F Name").getValue().toString());
                    LastName.setText(snapshot.child("L Name").getValue().toString());
                    String spin1= snapshot.child("Course ").getValue().toString();
                    int spin1Position= ((ArrayAdapter<String>) s1.getAdapter()).getPosition(spin1);
                    s1.setSelection(spin1Position);

                    String spin2= snapshot.child("Year ").getValue().toString();
                    int spin2Position= ((ArrayAdapter<String>) s2.getAdapter()).getPosition(spin2);
                    s2.setSelection(spin2Position);
                }
                else Toast.makeText(AddStudent.this, "ID not found", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void clearStudent() {
        StudentID.setText("");
        FirstName.setText("");
        LastName.setText("");
        s1.setSelection(0);
        s2.setSelection(0);
    }

    private void deleteStudent() {
        if (StudentID.getText().toString().equals("")){
            StudentID.setError("Enter ID");
        } else if (FirstName.getText().toString().equals("")) {
            FirstName.setError("Enter First Name");
        } else if (LastName.getText().toString().equals("")) {
            LastName.setError("Enter First Name");
        } else if (s1.getSelectedItem().toString().equals("")) {
            ((TextView)s1.getSelectedView()).setError("Select Course");
        } else if (s2.getSelectedItem().toString().equals("")) {
            ((TextView)s2.getSelectedView()).setError("Select Year");
        }
        else {
            String stDelete = StudentID.getText().toString();

            reference = FirebaseDatabase.getInstance().getReference();
            DatabaseReference child = reference.child("sID");
            DatabaseReference UserDB = child.child(stDelete);

            UserDB.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(AddStudent.this, "Student deleted successfully", Toast.LENGTH_SHORT).show();
                    StudentID.setText("");
                    FirstName.setText("");
                    LastName.setText("");
                    s1.setSelection(0);
                    s2.setSelection(0);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddStudent.this, "Student deletion failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void updateStudent() {
        if (StudentID.getText().toString().equals("")){
            StudentID.setError("Enter ID");
        } else if (FirstName.getText().toString().equals("")) {
            FirstName.setError("Enter First Name");
        } else if (LastName.getText().toString().equals("")) {
            LastName.setError("Enter First Name");
        } else if (s1.getSelectedItem().toString().equals("")) {
            ((TextView)s1.getSelectedView()).setError("Select Course");
        } else if (s2.getSelectedItem().toString().equals("")) {
            ((TextView)s2.getSelectedView()).setError("Select Year");
        }
        else{
            String idAdd= StudentID.getText().toString();
            String fnameAdd= FirstName.getText().toString();
            String lnameAdd= LastName.getText().toString();
            String courseAdd= s1.getSelectedItem().toString();
            String yearAdd= s2.getSelectedItem().toString();

            reference= FirebaseDatabase.getInstance().getReference();
            DatabaseReference child= reference.child("sID");
            DatabaseReference UserDB= child.child(idAdd);


            UserDB.child("F Name").setValue(fnameAdd);
            UserDB.child("L Name").setValue(lnameAdd);
            UserDB.child("Course ").setValue(courseAdd);
            UserDB.child("Year ").setValue(yearAdd);

            Toast.makeText(AddStudent.this, "Student updated successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void addStudents() {
        if (StudentID.getText().toString().equals("")){
            StudentID.setError("Enter ID");
        } else if (FirstName.getText().toString().equals("")) {
            FirstName.setError("Enter First Name");
        } else if (LastName.getText().toString().equals("")) {
            LastName.setError("Enter First Name");
        } else if (s1.getSelectedItem().toString().equals("")) {
            ((TextView)s1.getSelectedView()).setError("Select Course");
        } else if (s2.getSelectedItem().toString().equals("")) {
            ((TextView)s2.getSelectedView()).setError("Select Year");
        }
        else{
            String idAdd= StudentID.getText().toString();
            String fnameAdd= FirstName.getText().toString();
            String lnameAdd= LastName.getText().toString();
            String courseAdd= s1.getSelectedItem().toString();
            String yearAdd= s2.getSelectedItem().toString();

            reference= FirebaseDatabase.getInstance().getReference();
            DatabaseReference child= reference.child("sID");
            DatabaseReference UserDB= child.child(idAdd);

            UserDB.child("ID").setValue(idAdd);
            UserDB.child("F Name").setValue(fnameAdd);
            UserDB.child("L Name").setValue(lnameAdd);
            UserDB.child("Course ").setValue(courseAdd);
            UserDB.child("Year ").setValue(yearAdd);

            Toast.makeText(AddStudent.this, "Student added successfully", Toast.LENGTH_SHORT).show();
        }
    }
}