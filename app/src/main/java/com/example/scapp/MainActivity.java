package com.example.scapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{


    public void WeeklyAction(View view) {
        startActivity(new Intent(this, WeekViewActivity.class));
    }

    private TextView monthYear_txtView;
    private TextView prueba;
    private RecyclerView calendarRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        //El metodo now obtiene la fecha actual del reloj del sistema en la zona horaria predeterminada
        setWeekView();

//        calendarRecyclerView.scrollToPosition(2);

    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYear_txtView = findViewById(R.id.monthAndYear_txtView);
        prueba = findViewById(R.id.prueba);
    }

    private void setWeekView() {
        //Mostramos el año y el mes en el txtView
        monthYear_txtView.setText(CalendarUtils.monthYearFromDate(CalendarUtils.selectedDate));

        //Creamos un ArrayList que contiene los dias del mes
        ArrayList<LocalDate> daysInWeek = CalendarUtils.daysInWeekArray(CalendarUtils.selectedDate);

        //Creamos e instanciamos una variable de tipo CalendarAdapter
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInWeek, this);
        //Pasamos el adaptador al RecyclerView
        calendarRecyclerView.setAdapter(calendarAdapter);

        //Creamos e instanciamos el layoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        //Pasamos el layoutManager al recyclerView
        calendarRecyclerView.setLayoutManager(layoutManager);
    }

    public void PreviousWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void NextWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        CalendarUtils.selectedDate = date;
        setWeekView();
    }


}