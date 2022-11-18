package com.example.scapp.data;

import androidx.annotation.NonNull;
import com.example.scapp.SubjectsConfig.SubjectsAdapter;
import java.util.ArrayList;
import java.util.List;

public class SubjectsProvider {

    private static final List<String> subjects = new ArrayList<>();

    public static void addNewSubject(String subject, @NonNull SubjectsAdapter subjectsAdapter){
        subjects.add(0,subject);
        //subjectsAdapter.notifyItemInserted(0);
    }
}
