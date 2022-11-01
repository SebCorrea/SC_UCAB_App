package com.example.scapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import java.time.LocalDate;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private TextView monthYear_txtView;
    public static TextView prueba;
    private RecyclerView calendarRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        recyclerViewConfig();
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYear_txtView = findViewById(R.id.monthYear_txtView);
        prueba = findViewById(R.id.prueba);
    }

    private void recyclerViewConfig() {
        //Initial Config
        CalendarUtils.selectedDate = LocalDate.now();
        List<LocalDate[]> weeks = CalendarUtils.daysOfThisWeeks(CalendarUtils.selectedDate);
        //Adapter
        CalendarAdapter calendarAdapter = new CalendarAdapter(weeks);
        calendarRecyclerView.setAdapter(calendarAdapter);
        //Layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        calendarRecyclerView.setLayoutManager(layoutManager);
        //Scroll
        int ACTUAL_WEEK = 6; // se generan 13 semanas donde la 6ta es la semana actual
        calendarRecyclerView.scrollToPosition(ACTUAL_WEEK);
        calendarRecyclerView.addOnScrollListener(new CalendarScroll(layoutManager, calendarAdapter, weeks, monthYear_txtView));
        //Scroll Animation
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(calendarRecyclerView); //El RecyclerView obtiene propiedades de ViewPager2
    }

}