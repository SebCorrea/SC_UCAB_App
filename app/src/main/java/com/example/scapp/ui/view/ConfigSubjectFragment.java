package com.example.scapp.ui.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.scapp.R;
import com.example.scapp.databinding.FragmentSubjectConfigBinding;


public class ConfigSubjectFragment extends Fragment {

    private FragmentSubjectConfigBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject_config, container, false);
        binding = FragmentSubjectConfigBinding.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String subjectName = requireArguments().getString("prueba");
        binding.txtPrueba.setText(subjectName);
    }
}