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
import com.example.scapp.databinding.AddSubjectsDialogFragmentBinding;
import com.example.scapp.viewmodel.SubjectsViewModel;

public class AddSubjects_DialogFragment extends DialogFragment {

    private AddSubjectsDialogFragmentBinding binding;
    private Dialog dialog;
    private final SubjectsAdapter subjectsAdapter;
    private SubjectsViewModel subjectsViewModel;

    public AddSubjects_DialogFragment(SubjectsAdapter subjectsAdapter) {
        this.subjectsAdapter = subjectsAdapter;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        subjectsViewModel = new ViewModelProvider(requireActivity()).get(SubjectsViewModel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_subjects_dialog_fragment, null);
        binding = AddSubjectsDialogFragmentBinding.bind(view);
        builder.setView(view);
        dialog = builder.create();
        binding.agregarBtn.setOnClickListener(this::addSubject);
        return dialog;
    }

    public void addSubject(View v) {

        String subjectName = binding.subjectEditText.getText().toString();
        if(subjectName.trim().isEmpty()){
            binding.subjectTextInputLayout.setError("Campo vacío");

            return;
        }
        subjectsViewModel.addNewSubject(subjectName, subjectsAdapter);
        dialog.dismiss();
    }
}
