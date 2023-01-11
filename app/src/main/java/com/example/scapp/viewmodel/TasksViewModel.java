package com.example.scapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.scapp.data.providers.Tasks;

public class TasksViewModel extends ViewModel {
    private final MutableLiveData<Tasks> tasks;
    private Tasks task;

    public TasksViewModel() {
        tasks = new MutableLiveData<>();
    }

}
