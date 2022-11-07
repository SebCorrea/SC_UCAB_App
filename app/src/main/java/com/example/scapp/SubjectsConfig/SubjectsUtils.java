package com.example.scapp.SubjectsConfig;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.scapp.R;
import java.util.ArrayList;
import java.util.List;

public class SubjectsUtils {

    public static List<String> subjectsNames = new ArrayList<>();
    Button agregar_btn;
    EditText subject_EditText;

    public SubjectsUtils(View view) {
        this.agregar_btn = view.findViewById(R.id.agregar_btn);
        this.subject_EditText = view.findViewById(R.id.subject_EditText);
    }

    public void SubjectAlertDialogOptions(AlertDialog myDialog, SubjectsAdapter subjectsAdapter) {

        agregar_btn.setOnClickListener(v -> {
            if(!subject_EditText.getText().toString().trim().equals("")){
                subjectsNames.add(0,subject_EditText.getText().toString());
                subjectsAdapter.notifyItemInserted(0);
            }
            myDialog.dismiss();

        });
    }

}
