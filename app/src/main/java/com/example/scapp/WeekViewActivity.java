package com.example.scapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeekViewActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {


    private TextView monthYear_txtView;
    private TextView prueba;
    private RecyclerView calendarRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);

        initWidgets();
        //El metodo now obtiene la fecha actual del reloj del sistema en la zona horaria predeterminada
        setMonthView();
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYear_txtView = findViewById(R.id.monthAndYear_txtView);
        prueba = findViewById(R.id.prueba);
    }

    private void setMonthView() {
        //Mostramos el año y el mes en el txtView
        monthYear_txtView.setText(CalendarUtils.monthYearFromDate(CalendarUtils.selectedDate));

        //Creamos un ArrayList que contiene los dias del mes
        ArrayList<LocalDate> days = CalendarUtils.daysInMonthArray(CalendarUtils.selectedDate);

        //Creamos e instanciamos una variable de tipo CalendarAdapter
        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        //Pasamos el adaptador al RecyclerView
        calendarRecyclerView.setAdapter(calendarAdapter);

        //Creamos e instanciamos el layoutManager
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        //Pasamos el layoutManager al recyclerView
        calendarRecyclerView.setLayoutManager(layoutManager);

    }


    public void PreviousMonthAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();

    }

    public void NextMonthAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    //Metodo implementado de la interfaz
    @Override
    public void onItemClick(int position, LocalDate date) {
        if(date != null){
            CalendarUtils.selectedDate = date;
            setMonthView();
        }

    }
}