package com.example.scapp.SubjectsConfig;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.scapp.R;
import com.example.scapp.data.SubjectsProvider;
import com.example.scapp.databinding.SubjectsDialogFragmentBinding;
import com.example.scapp.ui.MainActivity;
import com.example.scapp.viewmodel.SubjectsViewModel;

import java.util.ArrayList;
import java.util.List;

public class SubjectsDialogFragment extends DialogFragment {

    private SubjectsDialogFragmentBinding binding;
    private static final List<String> subjectsNames = new ArrayList<>();
    private final SubjectsAdapter subjectsAdapter;
    private SubjectsViewModel viewModel;

    public static List<String> getSubjectsNames(){
        return subjectsNames;
    }
    private Dialog dialog;

    public SubjectsDialogFragment(SubjectsAdapter subjectsAdapter, ViewModelStoreOwner lifecycleOwner) {
        this.subjectsAdapter = subjectsAdapter;
        viewModel = new ViewModelProvider(lifecycleOwner).get(SubjectsViewModel.class);
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
            //subjectsNames.add(0,binding.subjectEditText.getText().toString());
            viewModel.addNewSubject(binding.subjectEditText.getText().toString(), subjectsAdapter);
            subjectsAdapter.notifyItemInserted(0);
            dialog.dismiss();
        }
    }


}
