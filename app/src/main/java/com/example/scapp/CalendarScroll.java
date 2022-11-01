package com.example.scapp;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.List;

public class CalendarScroll extends RecyclerView.OnScrollListener{

    private int position;
    private final LinearLayoutManager layoutManager;
    private final CalendarAdapter calendarAdapter;
    private final List<LocalDate[]> weeks;
    private final TextView monthYear_txtView;

    public CalendarScroll(LinearLayoutManager layoutManager, CalendarAdapter calendarAdapter, List<LocalDate[]> weeks, TextView monthYear_txtView) {
        this.weeks = weeks;
        this.layoutManager= layoutManager;
        this.calendarAdapter = calendarAdapter;
        this.monthYear_txtView = monthYear_txtView;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if(newState == RecyclerView.SCROLL_STATE_DRAGGING){ //Mientras se scrollea con el dedo (1)
            int totalItems = layoutManager.getItemCount()-1;
            if(position >= totalItems-3){
                CalendarUtils.generatePlusWeeks(calendarAdapter); //Se generan 3 semanas siguientes y se borra 3 anteriores
                layoutManager.scrollToPosition(position-3);
            }else if(position<=3){
                CalendarUtils.generateMinusWeeks(calendarAdapter); //Se generan 3 semenas anteriores y se borran 3 siguientes
                layoutManager.scrollToPosition(position+3);
            }
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        position = layoutManager.findFirstVisibleItemPosition();
        showMonthYear();
    }

    public void showMonthYear(){
        LocalDate scrollDate = weeks.get(position)[0];
        String scrollMonthYear = CalendarUtils.month(scrollDate) + " " +scrollDate.getYear();
        monthYear_txtView.setText(scrollMonthYear);
    }
}

