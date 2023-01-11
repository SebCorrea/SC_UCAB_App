package com.example.scapp.ui.subjectsUI;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TasksAdapter extends RecyclerView.Adapter<TasksViewHolder> {
    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class TasksViewHolder extends RecyclerView.ViewHolder{

    public TasksViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}