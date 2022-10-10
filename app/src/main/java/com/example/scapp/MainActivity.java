package com.example.scapp;

import androidx.annotation.NonNull;
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

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private static TextView monthYear_txtView;
    public static TextView prueba;
    private RecyclerView calendarRecyclerView;
    private final int ACTUAL_WEEK = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        recyclerViewConfig();
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYear_txtView = findViewById(R.id.monthAndYear_txtView);
        prueba = findViewById(R.id.prueba);
    }

    private void recyclerViewConfig() {
        //Initial Config
        CalendarUtils.selectedDate = LocalDate.now();
        List<LocalDate[]> weeks = CalendarUtils.daysOfThisWeeks(CalendarUtils.selectedDate);
        //Adapter
        CalendarAdapter calendarAdapter = new CalendarAdapter(weeks, this);
        calendarRecyclerView.setAdapter(calendarAdapter);
        //Layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        calendarRecyclerView.setLayoutManager(layoutManager);
        //Scroll
        calendarRecyclerView.scrollToPosition(ACTUAL_WEEK);
        calendarRecyclerView.addOnScrollListener(new CalendarScroll((LinearLayoutManager)layoutManager, calendarAdapter, weeks));
        //Scroll Animation
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(calendarRecyclerView);

    }

    public static void monthAndYearTxtView(@NonNull String scrollMonth, @NonNull String scrollYear){
        monthYear_txtView.setText(scrollMonth + " " + scrollYear);
    }

    public void WeeklyAction(View view) {
        startActivity(new Intent(this, WeekViewActivity.class));
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        String message = "Selected Day";
        prueba.setText(String.valueOf(date.getDayOfMonth()));
    }
}