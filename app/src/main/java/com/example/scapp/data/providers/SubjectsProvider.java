package com.example.scapp.data.providers;

import androidx.annotation.NonNull;
import com.example.scapp.ui.subjectsUI.SubjectsAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubjectsProvider {

    private static final List<String> subjects = new ArrayList<>();
    private String subjectPercentage;
    private String subjectsHomeworks;
    private String subjectDates;

    public static List<String> getSubjects(){
        return subjects;
    }

    public static void addNewSubject(@NonNull String subjectName, @NonNull SubjectsAdapter subjectsAdapter){

        String[] wordsArray = subjectName.split(" ");
        List<String> wordsList = new ArrayList<>();
        Collections.addAll(wordsList, wordsArray);

        List<String> helpList = new ArrayList<>(wordsList);

        helpList.sort((o1, o2) -> { //Ordenamos la lista de menor a mayor en relación al número de caracteres
            int length1 = o1.length();
            int length2 = o2.length();
            return Integer.compare(length1, length2);
        });

        //Sin alterar el orden en el que entraron los datos quiero que solo se muestren
        //las 2 palabras mas largas por lo que removemos el resto de palabras del List
        wordsList.removeIf(n-> !((n.contains(helpList.get(helpList.size()-1))) || (n.contains(helpList.get(helpList.size()-2)))));

        subjects.add(0,String.join(" ", wordsList));
        subjectsAdapter.notifyItemInserted(0);
    }

    public static void removeSubject(@NonNull SubjectsAdapter subjectsAdapter, String subjectName) {
        subjects.remove(subjectName);
        subjectsAdapter.notifyItemRemoved(subjects.indexOf(subjectName));

    }

    public String getSubjectPercentage() {
        return subjectPercentage;
    }

    public void setSubjectPercentage(String subjectPercentage) {
        this.subjectPercentage = subjectPercentage;
    }

    public String getSubjectsHomeworks() {
        return subjectsHomeworks;
    }

    public void setSubjectsHomeworks(String subjectsHomeworks) {
        this.subjectsHomeworks = subjectsHomeworks;
    }

    public String getSubjectDates() {
        return subjectDates;
    }

    public void setSubjectDates(String subjectDates) {
        this.subjectDates = subjectDates;
    }


}
