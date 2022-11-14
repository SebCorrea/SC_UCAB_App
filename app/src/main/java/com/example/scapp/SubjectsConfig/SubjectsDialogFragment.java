package com.example.scapp.SubjectsConfig;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.scapp.MainActivity;
import com.example.scapp.R;

public class SubjectsDialogFragment extends DialogFragment {

    Button pruebita;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subjects_alert_dialog,container,false);
        pruebita = view.findViewById(R.id.agregar_btn);

        pruebita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.prueba.setText("hablame manao");
            }
        });
        return view;
    }
}
