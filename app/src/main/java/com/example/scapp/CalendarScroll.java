package com.example.scapp;

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

    public CalendarScroll(LinearLayoutManager layoutManager, CalendarAdapter calendarAdapter, List<LocalDate[]> weeks) {
        this.weeks = weeks;
        this.layoutManager= layoutManager;
        this.calendarAdapter = calendarAdapter;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if(newState == RecyclerView.SCROLL_STATE_DRAGGING){ //Mientras se scrollea con el dedo (1)
            //scrollOutItems = totalItems - position;
            int totalItems = layoutManager.getItemCount()-1;
            if(position >= totalItems-3){

                CalendarUtils.generatePlusWeeks(calendarAdapter);
                layoutManager.scrollToPosition(position-3);

            }else if(position<=3){

                CalendarUtils.generateMinusWeeks(calendarAdapter);
                layoutManager.scrollToPosition(position+3);
            }
            //calendarAdapter.notifyDataSetChanged();
            //MainActivity.prueba.setText(String.valueOf(totalItems-position) + " totalItems: " + String.valueOf(totalItems) + " position: " + String.valueOf(position) + " Scroll: " + String.valueOf(overallScroll));
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        //overallScroll = dx + overallScroll;
        position = layoutManager.findFirstVisibleItemPosition();
        LocalDate scrollDate = weeks.get(position)[0];
        String scrollMonthYear = CalendarUtils.month(scrollDate) + scrollDate.getYear();
        MainActivity.monthAndYearTxtView(scrollMonthYear);
    }
}

