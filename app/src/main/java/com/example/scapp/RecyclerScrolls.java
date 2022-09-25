package com.example.scapp;

import android.view.View;
import android.view.animation.Interpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerScrolls extends RecyclerView.OnScrollListener {
    TextView prueba;
    RecyclerView calendarRecyclerView;

    public RecyclerScrolls(RecyclerView calendarRecyclerView) {

        this.calendarRecyclerView = calendarRecyclerView;
    }

    public void onScrolled(RecyclerView calendarRecyclerView, int dx, int dy){


        if(dx > 0){
            calendarRecyclerView.smoothScrollToPosition(6);
        }
    }


}
