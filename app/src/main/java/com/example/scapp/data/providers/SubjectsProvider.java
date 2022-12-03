package com.example.scapp.data.providers;

import androidx.annotation.NonNull;
import com.example.scapp.ui.subjectsUI.SubjectsAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubjectsProvider {

    private static final List<String> subjects = new ArrayList<>();

    public static List<String> getSubjects(){
        return subjects;
    }

    public static void addNewSubject(String subjectName, @NonNull SubjectsAdapter subjectsAdapter){



        String[] wordsArray = subjectName.split(" ");

        List<String> wordsList = new ArrayList<>();

        Collections.addAll(wordsList, wordsArray);

        final List<String> helpList = new ArrayList<>(wordsList);

        helpList.sort((o1, o2) -> {
            int larg1=o1.length();
            int larg2 = o2.length();
            return Integer.compare(larg1, larg2);
        });

        wordsList.removeIf(n-> !((n.contains(helpList.get(helpList.size()-1))) || (n.contains(helpList.get(helpList.size()-2)))));

        subjects.add(0,String.join(" ", wordsList));
        subjectsAdapter.notifyItemInserted(0);
    }

}
