package com.example.scapp.SubjectsConfig;

import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.scapp.databinding.SubjectsBinding;

public class SubjectsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public Button subjects_btn;
    private final SubjectsAdapter.onItemListener onItemListener;

    public SubjectsViewHolder(@NonNull View itemView, SubjectsAdapter.onItemListener onItemListener) {
        super(itemView);
        SubjectsBinding binding = SubjectsBinding.bind(itemView);
        subjects_btn = binding.subjectsBtn;
        this.onItemListener = onItemListener;

        subjects_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClickListener(subjects_btn.getText().toString());
    }
}
