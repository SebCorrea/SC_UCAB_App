package com.example.scapp.data.providers;

import androidx.annotation.NonNull;
import com.example.scapp.ui.subjectsUI.SubjectsAdapter;
import java.util.ArrayList;
import java.util.List;

public class SubjectsProvider {

    private static final List<String> subjects = new ArrayList<>();

    public static List<String> getSubjects(){
        return subjects;
    }

    public static void addNewSubject(String subject, @NonNull SubjectsAdapter subjectsAdapter){
        subjects.add(0,subject);
        subjectsAdapter.notifyItemInserted(0);
    }

}
