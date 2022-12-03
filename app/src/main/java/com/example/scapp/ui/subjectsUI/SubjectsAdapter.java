package com.example.scapp.ui.subjectsUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.example.scapp.R;
import com.example.scapp.databinding.SubjectsBinding;
import com.example.scapp.viewmodel.SubjectsViewModel;

import java.util.ArrayList;
import java.util.List;


public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsViewHolder> {

    private final onItemListener onItemListener;
    private final SubjectsViewModel subjectsViewModel;
    private final LifecycleOwner lifecycleOwner;

    public SubjectsAdapter(SubjectsViewModel subjectsViewModel, LifecycleOwner lifecycleOwner, onItemListener onItemListener) {
        this.onItemListener = onItemListener;
        this.subjectsViewModel = subjectsViewModel;
        this.lifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public SubjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.subjects,parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (ViewGroup.LayoutParams.WRAP_CONTENT);
        return new SubjectsViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectsViewHolder holder, int position) {
        subjectsViewModel.getSubjects().observe(lifecycleOwner, subjectsNames->
                holder.subjects_btn.setText(subjectsNames.get(position)));
    }

    @Override
    public int getItemCount() {
        return subjectsViewModel.getSubjects().getValue().size();
    }

    public interface onItemListener{
        void onItemClickListener(String dayText);
    }
}

class SubjectsViewHolder extends RecyclerView.ViewHolder {

    public Button subjects_btn;

    public SubjectsViewHolder(@NonNull View itemView, SubjectsAdapter.onItemListener onItemListener) {
        super(itemView);
        SubjectsBinding binding = SubjectsBinding.bind(itemView);
        subjects_btn = binding.subjectsBtn;
        subjects_btn.setOnClickListener(v -> onItemListener.onItemClickListener(subjects_btn.getText().toString()));
    }
}