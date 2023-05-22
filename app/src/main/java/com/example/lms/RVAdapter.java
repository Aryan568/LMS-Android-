package com.example.lms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {

    Context context;
    ArrayList<rvGetterSetter> list;

    public RVAdapter(Context context, ArrayList<rvGetterSetter> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        rvGetterSetter rv= list.get(position);
        holder.bid.setText(rv.getBookID());
        holder.bid.setText(rv.getTitle());
        holder.bid.setText(rv.getStudentID());
        holder.bid.setText(rv.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bid, bTitle, sid, sName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            bid= itemView.findViewById(R.id.rvBookID);
            bTitle= itemView.findViewById(R.id.rvTitle);
            sid= itemView.findViewById(R.id.rvStudentID);
            sName= itemView.findViewById(R.id.rvName);
        }
    }
}

