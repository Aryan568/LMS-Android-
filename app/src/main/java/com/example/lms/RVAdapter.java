package com.example.lms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

public class RVAdapter extends FirebaseRecyclerAdapter<rvModel, RVAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RVAdapter(@NonNull FirebaseRecyclerOptions<rvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull rvModel model) {
        holder.BookID.setText(model.getBookID());
        holder.Title.setText(model.getTitle());
        holder.StudentID.setText(model.getStudentID());
        holder.Name.setText(model.getName());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("IssuedBook").child(getRef(position).getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView BookID, Title, StudentID, Name;
        Button delete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            BookID= itemView.findViewById(R.id.rvBookID);
            Title= itemView.findViewById(R.id.rvTitle);
            StudentID= itemView.findViewById(R.id.rvStudentID);
            Name= itemView.findViewById(R.id.rvName);
            delete= itemView.findViewById(R.id.btnDelete);
        }
    }

}

