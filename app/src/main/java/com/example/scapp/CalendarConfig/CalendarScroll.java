package com.example.scapp.CalendarConfig;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarScroll extends RecyclerView.OnScrollListener{

    private int position;
    private final LinearLayoutManager layoutManager;
    private final CalendarAdapter calendarAdapter;
    private final TextView monthYearTxt_View;

    public CalendarScroll(LinearLayoutManager layoutManager, CalendarAdapter calendarAdapter, TextView monthYearTxt_View) {
        this.layoutManager= layoutManager;
        this.calendarAdapter = calendarAdapter;
        this.monthYearTxt_View = monthYearTxt_View;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if(newState == RecyclerView.SCROLL_STATE_DRAGGING){ //Mientras se scrollea con el dedo (1)
            int totalItems = layoutManager.getItemCount()-1;
            if(position >= totalItems-3){
                CalendarUtils.generatePlusWeeks(calendarAdapter); //Se generan 3 semanas siguientes y se borra 3 anteriores
            }else if(position<=3){
                CalendarUtils.generateMinusWeeks(calendarAdapter); //Se generan 3 semenas anteriores y se borran 3 siguientes
            }
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        position = layoutManager.findFirstVisibleItemPosition(); //Obtenemos la posición del adaptador
        CalendarDesing.showMonthAndYearTxt_View(position,monthYearTxt_View);
    }


}

