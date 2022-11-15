package com.example.scapp.SubjectsConfig;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.scapp.R;
import com.example.scapp.databinding.SubjectsDialogFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class SubjectsDialogFragment extends DialogFragment {

    private SubjectsDialogFragmentBinding binding;
    private static final List<String> subjectsNames = new ArrayList<>();
    private final SubjectsAdapter subjectsAdapter;

    public static List<String> getSubjectsNames(){
        return subjectsNames;
    }
    private Dialog dialog;


    public SubjectsDialogFragment(SubjectsAdapter subjectsAdapter) {
        this.subjectsAdapter = subjectsAdapter;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.subjects_dialog_fragment, null);
        binding = SubjectsDialogFragmentBinding.bind(view);
        builder.setView(view);

        dialog = builder.create();
        binding.agregarBtn.setOnClickListener(this::addSubject);

        return dialog;
    }


    public void addSubject(View v) {

        if(!binding.subjectEditText.getText().toString().trim().equals("")){
            subjectsNames.add(0,binding.subjectEditText.getText().toString());
            subjectsAdapter.notifyItemInserted(0);
            dialog.dismiss();

        }
    }


}
