package com.example.scapp.ui.subjectsUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.scapp.R;
import com.example.scapp.databinding.SubjectsBinding;

import java.util.List;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsViewHolder> {

    private final List<String> subjectsNames;
    private final onItemListener onItemListener;

    public SubjectsAdapter(List<String> subjectsNames, onItemListener onItemListener) {
        this.subjectsNames = subjectsNames;
        this.onItemListener = onItemListener;
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
        String subjectName = subjectsNames.get(position);
        if(subjectName!= null){
            holder.subjects_btn.setText(String.valueOf(subjectName.charAt(0)));
        }
    }

    @Override
    public int getItemCount() {
        return subjectsNames.size();
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