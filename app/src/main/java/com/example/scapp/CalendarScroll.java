package com.example.scapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarScroll extends RecyclerView.OnScrollListener{

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    private LocalDate selectedDate = LocalDate.now();
    String scroll;
    String direccion;



    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
            @Override protected int getHorizontalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };

        if(newState == recyclerView.SCROLL_STATE_IDLE){
            scroll= "Sin Scrollear";
        }else if(newState == recyclerView.SCROLL_STATE_SETTLING ){
            scroll= "Despues de Scrollear";
        }else if(newState == recyclerView.SCROLL_STATE_DRAGGING){
            scroll= "Scrolleando";
        }

        MainActivity.prueba.setText(scroll + direccion);
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        /*
        if(recyclerView.getScrollState() == recyclerView.SCROLL_STATE_IDLE){
            MainActivity.prueba.setText("Sin Scrollear");
        }else if(recyclerView.getScrollState() == recyclerView.SCROLL_STATE_DRAGGING){
            MainActivity.prueba.setText("Scrolleando");
        }else if(recyclerView.getScrollState() == recyclerView.SCROLL_STATE_SETTLING){
            MainActivity.prueba.setText("Despues de Scrollear");
        }
         */

        if(dx > 0){
            //MainActivity.prueba.setText("Adelante");
            /*
            smoothScroller.setTargetPosition(8);
            recyclerView.getLayoutManager().startSmoothScroll(smoothScroller);
            //calendarRecyclerView.getLayoutManager().scrollToPosition(1);
            selectedDate = selectedDate.plusWeeks(1);
             */
            direccion = " Derecha";
        }else{

            /*
            smoothScroller.setTargetPosition(-8);
            recyclerView.getLayoutManager().startSmoothScroll(smoothScroller);

            MainActivity.prueba.setText("Atras");
            selectedDate = selectedDate.minusWeeks(1);*/
            direccion = " Izquierda";

        }
    }
}
