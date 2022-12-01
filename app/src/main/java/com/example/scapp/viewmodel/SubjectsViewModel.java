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
        subjects.setValue(SubjectsProvider.getSubjects());
    }

    public void addNewSubject(String subject, SubjectsAdapter subjectsAdapter){
        SubjectsProvider.addNewSubject(subject, subjectsAdapter);
    }
    
    public LiveData<List<String>> getSubjects(){
        return subjects;
    }

}