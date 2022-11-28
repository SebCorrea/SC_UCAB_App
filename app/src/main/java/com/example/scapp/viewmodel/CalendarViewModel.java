package com.example.scapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.scapp.data.providers.CalendarDatesProvider;
import com.example.scapp.ui.calendarUI.CalendarAdapter;

import java.time.LocalDate;
import java.util.List;


public class CalendarViewModel extends ViewModel{

    private final MutableLiveData<List<LocalDate[]>> weeks;
    private final MutableLiveData<String> monthAndYear;

    public CalendarViewModel() {
        weeks = new MutableLiveData<>();
        monthAndYear = new MutableLiveData<>();
    }

    public LiveData<List<LocalDate[]>> getWeeks(){
        return weeks;
    }

    public void setWeeks(){
        weeks.setValue(CalendarDatesProvider.generateInitialWeeks());
    }

    public void generatePlusWeeks(CalendarAdapter calendarAdapter){
        CalendarDatesProvider.generatePlusWeeks(calendarAdapter);
    }

    public void generateMinusWeeks(CalendarAdapter calendarAdapter){
        CalendarDatesProvider.generateMinusWeeks(calendarAdapter);
    }

    public LiveData<String> getMonthAndYear() {
        return monthAndYear;
    }

    public void setMonthAndYear(LocalDate scrollDate){
        monthAndYear.setValue(CalendarDatesProvider.month(scrollDate) + ", " + scrollDate.getYear());
    }
}
