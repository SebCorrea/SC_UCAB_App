package com.example.scapp;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CalendarScroll extends RecyclerView.OnScrollListener{

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    private LocalDate selectedDate = LocalDate.now();
    String scroll;
    String direccion;
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

            if(overallScroll < 0 && overallScroll < (-medida) ){

                /*
                CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
                CalendarUtils.setWeekView2();

                 */
                smoothScroller.setTargetPosition(0);
                recyclerView.getLayoutManager().startSmoothScroll(smoothScroller);

            }else if(overallScroll<0 && overallScroll >= (-medida)){

                smoothScroller.setTargetPosition(7);
                recyclerView.getLayoutManager().startSmoothScroll(smoothScroller);

            }else if(overallScroll>0 && overallScroll<= medida){

                smoothScroller.setTargetPosition(7);
                recyclerView.getLayoutManager().startSmoothScroll(smoothScroller);

            }else if(overallScroll>0 && overallScroll> medida){

                /*
                CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
                CalendarUtils.setWeekView2();
                 */
                smoothScroller.setTargetPosition(14);
                recyclerView.getLayoutManager().startSmoothScroll(smoothScroller);
            }

        }else if(newState == recyclerView.SCROLL_STATE_SETTLING){ //Mientras ocurre la animacion del scroll (2)
            scroll= "Despues de Scrollear";

            /*
            smoothScroller.setTargetPosition(0);
            recyclerView.getLayoutManager().startSmoothScroll(smoothScroller);

             */

        }else if(newState == recyclerView.SCROLL_STATE_DRAGGING){ //Mientras se scrollea con el dedo (1)
            scroll= "Scrolleando";

        }

        MainActivity.prueba.setText(scroll);
    }

    private void newScrollWeek(){

    }
    private void ScrollAnimation(RecyclerView recyclerView){


    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        overallScroll = dx + overallScroll;
        //MainActivity.prueba.setText(scroll + "  " + String.valueOf(overallScroll));

    }

}


