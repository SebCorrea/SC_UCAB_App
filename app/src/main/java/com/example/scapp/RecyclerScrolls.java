package com.example.scapp;

import android.view.View;
import android.view.animation.Interpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerScrolls extends RecyclerView.OnScrollListener {
    TextView prueba;
    RecyclerView calendarRecyclerView;

    public void onScrolled(RecyclerView calendarRecyclerView, int dx, int dy){


        if(dx > 0){

            //calendarRecyclerView.getLayoutManager().scrollToPosition(1);

            RecyclerView.SmoothScroller smoothScroller = new
                    LinearSmoothScroller(calendarRecyclerView.getContext()) {
                        @Override protected int getVerticalSnapPreference() {
                            return LinearSmoothScroller.SNAP_TO_START;
                        }
                    };

            smoothScroller.setTargetPosition(5);
            calendarRecyclerView.getLayoutManager().startSmoothScroll(smoothScroller);
        }
    }


}
