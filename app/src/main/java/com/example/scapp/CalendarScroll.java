package com.example.scapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CalendarScroll extends RecyclerView.OnScrollListener{

    String scroll;
    RecyclerView.SmoothScroller smoothScroller;
    private int overallScroll;
    LinearLayoutManager layoutManager;
    private int position;
    private int totalItems;
    private int scrollOutItems;
    private int generateNewWeeks;
    private CalendarAdapter calendarAdapter;
    LocalDate holi;
    int medida;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");


    public CalendarScroll(Context context, LinearLayoutManager layoutManager, CalendarAdapter calendarAdapter) {
        this.layoutManager= layoutManager;
        this.calendarAdapter = calendarAdapter;
        holi = LocalDate.now();
        smoothScroller = new LinearSmoothScroller(context) {
            @Override
            protected int getHorizontalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if(newState == recyclerView.SCROLL_STATE_IDLE){ //Termina la animación de scroll (3)
            scroll= "Sin Scrollear";
        }else if(newState == recyclerView.SCROLL_STATE_SETTLING){ //Mientras ocurre la animacion del scroll (2)
            scroll= "Despues de Scrollear";
        }else if(newState == recyclerView.SCROLL_STATE_DRAGGING){ //Mientras se scrollea con el dedo (1)
            scroll= "Scrolleando";
            position = layoutManager.findFirstVisibleItemPosition();
            totalItems = layoutManager.getItemCount()-1;
            scrollOutItems = totalItems - position;

            if(overallScroll>0 && (position >= totalItems-3)){

                CalendarUtils.generatePlusWeeks();
                calendarAdapter.notifyDataSetChanged();

            }else if(overallScroll<0 && position<=3){

                CalendarUtils.generateMinusWeeks();
                calendarAdapter.notifyDataSetChanged();
                layoutManager.scrollToPosition(position+3);
            }
            MainActivity.prueba.setText(String.valueOf(totalItems-position) + " totalItems: " + String.valueOf(totalItems) + " position: " + String.valueOf(position));
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        overallScroll = dx + overallScroll;
    }

}

