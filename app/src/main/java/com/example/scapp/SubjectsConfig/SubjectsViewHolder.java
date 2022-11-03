package com.example.scapp.SubjectsConfig;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scapp.R;

public class SubjectsViewHolder extends RecyclerView.ViewHolder {

    public Button subjects_btn;
    public SubjectsViewHolder(@NonNull View itemView) {
        super(itemView);
        subjects_btn = itemView.findViewById(R.id.subjects_btn);

    }
}
