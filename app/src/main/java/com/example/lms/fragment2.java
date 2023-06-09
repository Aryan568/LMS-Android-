package com.example.lms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class fragment2 extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference reference;
    RVAdapter rvAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment2, container, false);

        recyclerView= v.findViewById(R.id.userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<rvModel> options =
                new FirebaseRecyclerOptions.Builder<rvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("IssuedBook"), rvModel.class)
                        .build();

        rvAdapter= new RVAdapter(options);
        recyclerView.setAdapter(rvAdapter);

//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    rvGetterSetter rv= dataSnapshot.getValue(rvGetterSetter.class);
//                    list.add(rv);
//                }
//                rvAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        rvAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        rvAdapter.stopListening();
    }
}