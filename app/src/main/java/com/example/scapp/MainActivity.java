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

    private TextView monthYear_txtView;
    public static TextView prueba;
    public static RecyclerView calendarRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();

        CalendarUtils.selectedDate = LocalDate.now();
        setWeekView();
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYear_txtView = findViewById(R.id.monthAndYear_txtView);
        prueba = findViewById(R.id.prueba);
    }

    private void setWeekView() {
        //Mostramos el año y el mes en el txtView
        monthYear_txtView.setText(CalendarUtils.monthYearFromDate(CalendarUtils.selectedDate));
        //Creamos un List que contiene los dias de la semana
        List<LocalDate[]> daysInWeek = CalendarUtils.daysInWeekArray(CalendarUtils.selectedDate);

        //Creamos e instanciamos una variable de tipo CalendarAdapter y pasamos el adaptador al RecyclerView
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInWeek);
        calendarRecyclerView.setAdapter(calendarAdapter);

        //Creamos e instanciamos el layoutManager pasamos el layoutManager al recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        calendarRecyclerView.setLayoutManager(layoutManager);

        calendarRecyclerView.scrollToPosition(1);
        calendarRecyclerView.addOnScrollListener(new CalendarScroll(this));
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();

        try{
            pagerSnapHelper.attachToRecyclerView(calendarRecyclerView);
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