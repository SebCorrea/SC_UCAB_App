package com.example.scapp.ui.subjectsUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.scapp.R;
import com.example.scapp.databinding.SubjectsBinding;
import java.util.List;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsViewHolder> {

    private final SubjectsButtonsActions subjectsButtonsActions;
    private final List<String> subjects;

    public SubjectsAdapter(SubjectsButtonsActions onItemListener, List<String> subjects) {
        this.subjectsButtonsActions = onItemListener;
        this.subjects = subjects;
    }

    @NonNull
    @Override
    public SubjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.subjects,parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (ViewGroup.LayoutParams.WRAP_CONTENT);
        return new SubjectsViewHolder(view, subjectsButtonsActions);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectsViewHolder holder, int position) {
        holder.binding.subjectsBtn.setText(subjects.get(position));
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public interface SubjectsButtonsActions {
        void onItemClickListener(String subjectName);
        boolean onItemLongClickListener(String subjectName);
    }
}

class SubjectsViewHolder extends RecyclerView.ViewHolder {

    public SubjectsBinding binding;

    public SubjectsViewHolder(@NonNull View itemView, SubjectsAdapter.SubjectsButtonsActions subjectsButtonsActions) {
        super(itemView);
        binding = SubjectsBinding.bind(itemView);

        binding.subjectsBtn.setOnClickListener(v -> subjectsButtonsActions.onItemClickListener(binding.subjectsBtn.getText().toString()));
        binding.subjectsBtn.setOnLongClickListener(v-> subjectsButtonsActions.onItemLongClickListener(binding.subjectsBtn.getText().toString()));
    }
}