package com.example.scapp;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarScroll extends RecyclerView.OnScrollListener implements View.OnScrollChangeListener{

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

        if(newState == recyclerView.SCROLL_STATE_IDLE){ //Termina la animación de scroll (3)
            scroll= "Sin Scrollear";

            //smoothScroller.setTargetPosition(8);
            //recyclerView.getLayoutManager().startSmoothScroll(smoothScroller);



        }else if(newState == recyclerView.SCROLL_STATE_SETTLING){ //Mientras ocurre la animacion del scroll (2)
            scroll= "Despues de Scrollear";


            //smoothScroller.setTargetPosition(8);
            //recyclerView.getLayoutManager().startSmoothScroll(smoothScroller);


        }else if(newState == recyclerView.SCROLL_STATE_DRAGGING){ //Mientras se scrollea con el dedo (1)
            scroll= "Scrolleando";
            selectedDate = selectedDate.plusWeeks(1);
            //MainActivity.prueba.setText(selectedDate.format(formatter));
        }


        //MainActivity.prueba.setText(scroll + direccion);
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        recyclerView.setOnScrollChangeListener(this);
        /*
        if(recyclerView.getScrollState() == recyclerView.SCROLL_STATE_IDLE){
            MainActivity.prueba.setText("Sin Scrollear");
        }else if(recyclerView.getScrollState() == recyclerView.SCROLL_STATE_DRAGGING){
            MainActivity.prueba.setText("Scrolleando");
        }else if(recyclerView.getScrollState() == recyclerView.SCROLL_STATE_SETTLING){
            MainActivity.prueba.setText("Despues de Scrollear");
        }
         */

        //MainActivity.prueba.setText(String.valueOf(recyclerView.computeHorizontalScrollOffset()));

        if(dx > 0){
            /*

            MainActivity.prueba.setText("Adelante");
            //calendarRecyclerView.getLayoutManager().scrollToPosition(1);
            selectedDate = selectedDate.plusWeeks(1);
             */
            direccion = " Derecha";


        }else{

            /*
            MainActivity.prueba.setText("Atras");
            smoothScroller.setTargetPosition(-8);
            recyclerView.getLayoutManager().startSmoothScroll(smoothScroller);
            selectedDate = selectedDate.minusWeeks(1);*/
            direccion = " Izquierda";
            //selectedDate = selectedDate.minusWeeks(1);
        }

    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

    }
}

