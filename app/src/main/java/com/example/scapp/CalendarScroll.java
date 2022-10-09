package com.example.scapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CalendarScroll extends RecyclerView.OnScrollListener{

    String scroll;
    private int overallScroll;
    LinearLayoutManager layoutManager;
    private int position;
    private int totalItems;
    private int scrollOutItems;
    private CalendarAdapter calendarAdapter;
    private List<LocalDate[]> weeks;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");

    public CalendarScroll(LinearLayoutManager layoutManager, CalendarAdapter calendarAdapter, List<LocalDate[]> weeks) {
        this.weeks = weeks;
        this.layoutManager= layoutManager;
        this.calendarAdapter = calendarAdapter;
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
            MainActivity.monthYear_txtView.setText(CalendarUtils.monthYearFromDate(weeks.get(position)[0]));

            totalItems = layoutManager.getItemCount()-1;
            scrollOutItems = totalItems - position;
            if(overallScroll>0 && (position >= totalItems-3)){
                CalendarUtils.generatePlusWeeks2(calendarAdapter);
                calendarAdapter.notifyDataSetChanged();
                layoutManager.scrollToPosition(position-3);
            }else if(overallScroll<0 && position<=3){
                CalendarUtils.generateMinusWeeks2(calendarAdapter);
                calendarAdapter.notifyDataSetChanged();
                layoutManager.scrollToPosition(position+3);
            }
            overallScroll = 0;

            MainActivity.prueba.setText(String.valueOf(totalItems-position) + " totalItems: " + String.valueOf(totalItems) + " position: " + String.valueOf(position) + " Scroll: " + String.valueOf(overallScroll));
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        overallScroll = dx + overallScroll;
    }

}

