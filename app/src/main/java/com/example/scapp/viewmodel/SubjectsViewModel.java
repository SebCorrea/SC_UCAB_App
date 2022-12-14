package com.example.scapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.scapp.ui.subjectsUI.SubjectsAdapter;
import com.example.scapp.data.providers.SubjectsProvider;
import java.util.List;

public class SubjectsViewModel extends ViewModel {

    private final MutableLiveData<List<String>> subjects;

    public SubjectsViewModel() {
        subjects = new MutableLiveData<>();
    }

    public void set_subjects(){
        subjects.postValue(SubjectsProvider.getSubjects());
    }

    public void addNewSubject(String subjectName, SubjectsAdapter subjectsAdapter){
        SubjectsProvider.addNewSubject(subjectName,subjectsAdapter);
        subjects.postValue(SubjectsProvider.getSubjects());
    }

    public LiveData<List<String>> get_Subjects(){
        return subjects;
    }

    public void removeSubject(SubjectsAdapter subjectsAdapter, String subjectName) {
        SubjectsProvider.removeSubject(subjectsAdapter, subjectName);
        subjects.postValue(SubjectsProvider.getSubjects());

    }
}