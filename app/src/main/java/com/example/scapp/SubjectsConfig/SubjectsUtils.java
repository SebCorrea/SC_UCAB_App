package com.example.scapp.SubjectsConfig;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.scapp.MainActivity;
import com.example.scapp.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class SubjectsUtils extends DialogFragment {

    private final String TAG = "SubjectsUtils";
    private static final List<String> subjectsNames = new ArrayList<>();

    public static List<String> getSubjectsNames(){
        return subjectsNames;
    }

    /*
    public SubjectsUtils(Button agregar_btn, EditText subject_EditText, TextInputLayout subject_TextInputLayout) {

    }

     */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subjects_alert_dialog, container, false);
        return view;
    }

    public static void SubjectAlertDialogOptions(@NonNull Button agregar_btn, @NonNull EditText subject_EditText, SubjectsAdapter subjectsAdapter, AlertDialog myDialog) {

        agregar_btn.setOnClickListener(v -> {
            if(!subject_EditText.getText().toString().trim().equals("")){
                subjectsNames.add(0,subject_EditText.getText().toString());
                subjectsAdapter.notifyItemInserted(0);
            }
            myDialog.dismiss();
        });

        subject_EditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
        //subject_EditText.setFilters(new InputFilter[] {new TextWatchers()});
    }

    /*
    private static void TextFilterOptions(){

        InputFilter filter = new InputFilter() {
            @Nullable
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetter(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };
        subject_EditText.setFilters(new InputFilter[] { filter });

    }

     */



}

class TextWatchers implements TextWatcher, InputFilter {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        for (int i = start; i < end; i++) {
            if (!Character.isLetterOrDigit(source.charAt(i))) {
                return "";
            }
        }
        return null;
    }

}
