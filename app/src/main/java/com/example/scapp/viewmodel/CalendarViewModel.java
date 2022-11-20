package com.example.scapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.scapp.ui.calendarUI.CalendarAdapter;
import com.example.scapp.data.providers.CalendarDatesProvider;
import java.time.LocalDate;
import java.util.List;

public class CalendarViewModel extends ViewModel{
    private final MutableLiveData<List<LocalDate[]>> weeks;
    private final MutableLiveData<String> monthAndYear;
    private final MutableLiveData<List<LocalDate[]>> weeks2;


    public CalendarViewModel() {
        weeks = new MutableLiveData<>();
        monthAndYear = new MutableLiveData<>();
        weeks2 = new MutableLiveData<>();

        weeks.setValue(CalendarDatesProvider.generateInitialWeeks());

    }

    public LiveData<List<LocalDate[]>> getWeeks(){
        return weeks;
    }

    public LiveData<String> getMonthAndYear() {
        return monthAndYear;
    }

    public void generatePlusWeeks(CalendarAdapter calendarAdapter){
        CalendarDatesProvider.generatePlusWeeks(calendarAdapter);
    }

    public void generatePlusWeeks2 (){
        weeks2.setValue(CalendarDatesProvider.generatePlusWeeks2());
    }

    public LiveData<List<LocalDate[]>> getWeeks2(){
        return weeks2;
    }
    public void generateMinusWeeks(CalendarAdapter calendarAdapter){
        CalendarDatesProvider.generateMinusWeeks(calendarAdapter);
    }

    public void setMonthAndYear(LocalDate scrollDate){
        monthAndYear.setValue(CalendarDatesProvider.month(scrollDate) + ", " + scrollDate.getYear());
    }
}
