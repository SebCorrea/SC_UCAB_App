package com.example.scapp.CalendarConfig;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarScroll extends RecyclerView.OnScrollListener{

    private int position;
    private final LinearLayoutManager layoutManager;
    private final CalendarAdapter calendarAdapter;
    private final CalendarUtils calendarUtils;

    public CalendarScroll(LinearLayoutManager layoutManager, CalendarAdapter calendarAdapter, CalendarUtils calendarUtils) {
        this.layoutManager= layoutManager;
        this.calendarAdapter = calendarAdapter;
        this.calendarUtils = calendarUtils;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if(newState == RecyclerView.SCROLL_STATE_DRAGGING){ //Mientras se scrollea con el dedo (1)
            int totalItems = layoutManager.getItemCount()-1;
            if(position >= totalItems-3){
                calendarUtils.generatePlusWeeks(calendarAdapter); //Se generan 3 semanas siguientes y se borra 3 anteriores
            }else if(position<=3){
                calendarUtils.generateMinusWeeks(calendarAdapter); //Se generan 3 semenas anteriores y se borran 3 siguientes
            }
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        position = layoutManager.findFirstVisibleItemPosition();
        calendarUtils.showMonthAndYear(position);
    }


}

