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

    public void WeeklyAction(View view) {
        startActivity(new Intent(this, WeekViewActivity.class));
    }

    public static TextView monthYear_txtView;
    public static TextView prueba;
    public static RecyclerView calendarRecyclerView;

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
        List<LocalDate[]> daysInWeek = CalendarUtils.daysOfThisWeeks(CalendarUtils.selectedDate);
        //Adapter
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInWeek);
        calendarRecyclerView.setAdapter(calendarAdapter);
        //Layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        calendarRecyclerView.setLayoutManager(layoutManager);
        //Scroll
        calendarRecyclerView.scrollToPosition(6);
        calendarRecyclerView.addOnScrollListener(new CalendarScroll((LinearLayoutManager)layoutManager, calendarAdapter, daysInWeek));
        //Config Scroll
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(calendarRecyclerView);
    }

    private void setWeekView() {
        //Mostramos el año y el mes en el txtView
        monthYear_txtView.setText(CalendarUtils.monthYearFromDate(CalendarUtils.selectedDate));
        //Creamos un List que contiene los dias de la semana

        //Creamos e instanciamos el layoutManager pasamos el layoutManager al recyclerView

        try{
        }catch (Exception e){

        }
    }

    public void PreviousWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void NextWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

}