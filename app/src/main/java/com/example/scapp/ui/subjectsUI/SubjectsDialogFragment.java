package com.example.scapp.ui.subjectsUI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.scapp.R;
import com.example.scapp.databinding.SubjectsDialogFragmentBinding;
import com.example.scapp.viewmodel.SubjectsViewModel;

public class SubjectsDialogFragment extends DialogFragment {

    private SubjectsDialogFragmentBinding binding;
    private final SubjectsAdapter subjectsAdapter;
    private SubjectsViewModel subjectsViewModel;
    private Dialog dialog;

    public SubjectsDialogFragment(SubjectsAdapter subjectsAdapter) {
        this.subjectsAdapter = subjectsAdapter;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        subjectsViewModel = new ViewModelProvider(requireActivity()).get(SubjectsViewModel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.subjects_dialog_fragment, null);
        binding = SubjectsDialogFragmentBinding.bind(view);
        builder.setView(view);
        dialog = builder.create();
        binding.agregarBtn.setOnClickListener(this::addSubject);
        return dialog;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void addSubject(View v) {

        String subject = binding.subjectEditText.getText().toString();
        if(!subject.trim().equals("")){
            //subjectsNames.add(0,binding.subjectEditText.getText().toString());
            subjectsViewModel.addNewSubject(subject, subjectsAdapter);
            //subjectsAdapter.notifyItemInserted(0);
            dialog.dismiss();
        }
    }


}
