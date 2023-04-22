package com.example.lms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageBooks extends AppCompatActivity {
    EditText books, titles, authors, categorys;
    Button save, update, delete, clear, search;
    DatabaseReference reference;
    ArrayList<HelperClassForBooks> idlist= new ArrayList<>();
    String bookup, titleup, authorup, categoryup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_books);

        books= findViewById(R.id.bookno);
        titles= findViewById(R.id.title);
        authors= findViewById(R.id.author);
        categorys= findViewById(R.id.category);
        save= findViewById(R.id.b1);
        update= findViewById(R.id.b2);
        delete= findViewById(R.id.b3);
        clear= findViewById(R.id.b4);
        search= findViewById(R.id.b5);

        showAllData();

//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                reference= FirebaseDatabase.getInstance().getReference("id");
//                String booknos= books.getText().toString();
//                String title= titles.getText().toString();
//                String author= authors.getText().toString();
//                String category= categorys.getText().toString();
//
//                HelperClassForBooks helperclass= new HelperClassForBooks(booknos, title, author, category);
//                reference.child(booknos).setValue(helperclass);
//
//                Toast.makeText(ManageBooks.this, "Students details added", Toast.LENGTH_SHORT).show();
//
//            }
//        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBooks();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchBooks();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteBook();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearBook();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBook();
            }
        });

    }

    private void updateBook() {
        if (books.getText().toString().equals("")){
            books.setError("Enter book number");
        } else if (titles.getText().toString().equals("")) {
            titles.setError("Enter book name");
        }
        else if (authors.getText().toString().equals("")) {
            authors.setError("Enter author name");
        }
        else if (categorys.getText().toString().equals("")) {
            categorys.setError("Enter category");
        }
        else {
            String bookAdd= books.getText().toString().trim();
            String titleAdd= titles.getText().toString().trim();
            String authorAdd= authors.getText().toString().trim();
            String categorysAdd= categorys.getText().toString().trim();

            reference= FirebaseDatabase.getInstance().getReference();
            DatabaseReference child= reference.child("id");
            DatabaseReference UserDB = child.child(bookAdd);


            UserDB.child("title").setValue(titleAdd);
            UserDB.child("author").setValue(authorAdd);
            UserDB.child("category").setValue(categorysAdd);

            Toast.makeText(this, "Book updated successfully", Toast.LENGTH_SHORT).show();

        }
    }
    private void clearBook() {
        books.setText("");
        titles.setText("");
        authors.setText("");
        categorys.setText("");
    }

    private void deleteBook() {

        String bookDelete= books.getText().toString().trim();

        reference= FirebaseDatabase.getInstance().getReference();
        DatabaseReference child= reference.child("id");
        DatabaseReference UserDB= child.child(bookDelete);

        UserDB.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ManageBooks.this, "Book deleted successfully", Toast.LENGTH_SHORT).show();

                books.setText("");
                titles.setText("");
                authors.setText("");
                categorys.setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ManageBooks.this, "Book deletion failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addBooks() {
        if (books.getText().toString().equals("")){
            books.setError("Enter book number");
        } else if (titles.getText().toString().equals("")) {
            titles.setError("Enter book name");
        }
        else if (authors.getText().toString().equals("")) {
            authors.setError("Enter author name");
        }
        else if (categorys.getText().toString().equals("")) {
            categorys.setError("Enter category");
        }
        else {
            String bookAdd= books.getText().toString().trim();
            String titleAdd= titles.getText().toString().trim();
            String authorAdd= authors.getText().toString().trim();
            String categorysAdd= categorys.getText().toString().trim();

            reference= FirebaseDatabase.getInstance().getReference();
            DatabaseReference child= reference.child("id");
            DatabaseReference UserDB = child.child(bookAdd);

            UserDB.child("bookno").setValue(bookAdd);
            UserDB.child("title").setValue(titleAdd);
            UserDB.child("author").setValue(authorAdd);
            UserDB.child("category").setValue(categorysAdd);

            Toast.makeText(this, "Book added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void searchBooks() {
        if (books.getText().toString().equals("")){
            books.setError("Enter book number");
        }
        else{
            String bookSearch= books.getText().toString().trim();
            String titleSearch= titles.getText().toString().trim();
            String authorSearch= authors.getText().toString().trim();
            String categorysSearch= categorys.getText().toString().trim();

            reference= FirebaseDatabase.getInstance().getReference();
            DatabaseReference child= reference.child("id");
            DatabaseReference UserDB= child.child(bookSearch);

            UserDB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        titles.setText(snapshot.child("title").getValue().toString());
                        authors.setText(snapshot.child("author").getValue().toString());
                        categorys.setText(snapshot.child("category").getValue().toString());

                        Toast.makeText(ManageBooks.this, "Book number found", Toast.LENGTH_SHORT).show();
                    }
                    else books.setError("Book number not found");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void showAllData() {
        Intent intent= getIntent();
        bookup= intent.getStringExtra("bookno");
        titleup= intent.getStringExtra("title");
        authorup= intent.getStringExtra("author");
        categoryup= intent.getStringExtra("category");

        books.setText(bookup);
        titles.setText(titleup);
        authors.setText(authorup);
        categorys.setText(categoryup);

    }

}