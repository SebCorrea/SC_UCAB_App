package com.example.scapp.ui.subjectsUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scapp.R;
import com.example.scapp.data.providers.Tasks;
import com.example.scapp.databinding.TasksBinding;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksViewHolder> {

    private final List<Tasks> tasks;

    public TasksAdapter(List<Tasks> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.tasks, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (ViewGroup.LayoutParams.WRAP_CONTENT);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {

        holder.binding.taskNameTxtView.setText(tasks.get(position).getTaskName());
        holder.binding.taskDateTxtView.setText(tasks.get(position).getTaskDate());
        holder.binding.taskPercentTxtView.setText(tasks.get(position).getTaskPercent());

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}

class TasksViewHolder extends RecyclerView.ViewHolder{

    public TasksBinding binding;
    public TasksViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = TasksBinding.bind(itemView);
    }
}