package com.example.scapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.scapp.data.providers.CalendarDatesProvider;
import java.time.LocalDate;
import java.util.List;


public class CalendarViewModel extends ViewModel{

    private final MutableLiveData<List<LocalDate[]>> weeks, newPlusWeeks, newMinusWeeks;
    private final MutableLiveData<String> monthAndYear;

    public CalendarViewModel() {
        weeks = new MutableLiveData<>();
        monthAndYear = new MutableLiveData<>();
        newPlusWeeks = new MutableLiveData<>();
        newMinusWeeks = new MutableLiveData<>();

        weeks.setValue(CalendarDatesProvider.generateInitialWeeks());
    }

    public LiveData<List<LocalDate[]>> getWeeks(){
        return weeks;
    }


    public void generatePlusWeeks(){
        newPlusWeeks.setValue(CalendarDatesProvider.generatePlusWeeks());
    }

    public List<LocalDate[]> getNewPlusWeeks(){
        return newPlusWeeks.getValue();
    }


    public void generateMinusWeeks(){
        newMinusWeeks.setValue(CalendarDatesProvider.generateMinusWeeks());
    }

    public List<LocalDate[]> getNewMinusWeeks(){
        return newMinusWeeks.getValue();
    }

    public LiveData<String> getMonthAndYear() {
        return monthAndYear;
    }

    public void setMonthAndYear(LocalDate scrollDate){
        monthAndYear.setValue(CalendarDatesProvider.month(scrollDate) + ", " + scrollDate.getYear());
    }
}
