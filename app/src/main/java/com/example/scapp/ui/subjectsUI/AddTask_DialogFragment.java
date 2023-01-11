package com.example.scapp.ui.subjectsUI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.scapp.R;
import com.example.scapp.data.providers.Tasks;
import com.example.scapp.databinding.AddTasksDialogFragmentBinding;

import java.util.List;

public class AddTask_DialogFragment extends DialogFragment {

    private AddTasksDialogFragmentBinding binding;
    private Dialog dialog;
    private List<Tasks> tasks;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_tasks_dialog_fragment, null);
        binding = AddTasksDialogFragmentBinding.bind(view);
        builder.setView(view);
        dialog = builder.create();
        binding.saveTaskBtn.setOnClickListener(this::saveTasks);
        return dialog;
    }

    private void saveTasks(View view) {

        Tasks task = new Tasks();
        task.setTaskName(binding.taskNameTextInputLayout.getEditText().getText().toString());
        task.setTaskDate(binding.taskDateTextInputLayout.getEditText().getText().toString());
        task.setTaskPercent(binding.taskPercentTextInputLayout.getEditText().getText().toString());
        //tasks.add(task);

    }

}
