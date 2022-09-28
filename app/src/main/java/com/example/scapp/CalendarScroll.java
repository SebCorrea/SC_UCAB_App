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
    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if( newState == recyclerView.SCROLL_STATE_IDLE){
            RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
                        @Override protected int getHorizontalSnapPreference() {
                            return LinearSmoothScroller.SNAP_TO_START;
                        }
                    };

            smoothScroller.setTargetPosition(8);
            recyclerView.getLayoutManager().startSmoothScroll(smoothScroller);

            selectedDate = selectedDate.plusWeeks(1);
            MainActivity.prueba.setText(selectedDate.format(formatter));
        }else if(newState == recyclerView.SCROLL_STATE_SETTLING ){

        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if(dx > 0){

            //calendarRecyclerView.getLayoutManager().scrollToPosition(1);


        }
    }
}
