package com.example.scapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.scapp.SubjectsConfig.SubjectsAdapter;
import com.example.scapp.data.CalendarDatesProvider;
import com.example.scapp.data.SubjectsProvider;

import java.util.List;

public class SubjectsViewModel extends ViewModel {

    private final MutableLiveData<List<String>> subjects;

    public SubjectsViewModel() {
        subjects = new MutableLiveData<>();
    }

    public void addNewSubject(String subject, SubjectsAdapter subjectsAdapter){
        SubjectsProvider.addNewSubject(subject, subjectsAdapter);
        //subjects.setValue(SubjectsProvider.addNewSubject(subject));
    }

    public LiveData<List<String>> getSubject(){
        return subjects;
    }
}
