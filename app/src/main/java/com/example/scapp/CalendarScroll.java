package com.example.scapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;


public class CalendarScroll extends RecyclerView.OnScrollListener{

    String scroll;
    RecyclerView.SmoothScroller smoothScroller;
    private int overallScroll;

    public CalendarScroll(Context context) {
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

        int medida = recyclerView.getWidth()/2;
        if(newState == recyclerView.SCROLL_STATE_IDLE){ //Termina la animación de scroll (3)
            scroll= "Sin Scrollear";

        }else if(newState == recyclerView.SCROLL_STATE_SETTLING){ //Mientras ocurre la animacion del scroll (2)
            scroll= "Despues de Scrollear";

        }else if(newState == recyclerView.SCROLL_STATE_DRAGGING){ //Mientras se scrollea con el dedo (1)
            scroll= "Scrolleando";

        }

        MainActivity.prueba.setText(String.valueOf(overallScroll));

    }


    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        overallScroll = dx + overallScroll;
        //MainActivity.prueba.setText(scroll + "  " + String.valueOf(overallScroll));


    }

}

