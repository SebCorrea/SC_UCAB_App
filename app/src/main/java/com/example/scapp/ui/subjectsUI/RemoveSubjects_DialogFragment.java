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
import com.example.scapp.databinding.RemoveSubjectsDialogFragmentBinding;
import com.example.scapp.viewmodel.SubjectsViewModel;

public class RemoveSubjects_DialogFragment extends DialogFragment {

    private final SubjectsAdapter subjectsAdapter;
    private Dialog dialog;
    private SubjectsViewModel subjectsViewModel;
    private RemoveSubjectsDialogFragmentBinding binding;


    public RemoveSubjects_DialogFragment(SubjectsAdapter subjectsAdapter) {
        this.subjectsAdapter = subjectsAdapter;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        subjectsViewModel = new ViewModelProvider(requireActivity()).get(SubjectsViewModel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.remove_subjects_dialog_fragment, null);
        binding = RemoveSubjectsDialogFragmentBinding.bind(view);
        builder.setView(view);
        dialog = builder.create();
        return dialog;
    }
}
