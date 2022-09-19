package com.example.scapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView monthYear_txtView;
    private TextView prueba;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        //El metodo now obtiene la fecha actual del reloj del sistema en la zona horaria predeterminada
        selectedDate = LocalDate.now();
        setMonthView();
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYear_txtView = findViewById(R.id.monthAndYear_txtView);
        prueba = findViewById(R.id.prueba);

    }

    private void setMonthView() {
        //Mostramos el año y el mes en el txtView
        monthYear_txtView.setText(monthYearFromDate(selectedDate));

        //Creamos un ArrayList que contiene los dias del mes
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        //Creamos e instanciamos una variable de tipo CalendarAdapter
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        //Pasamos el adaptador al RecyclerView
        calendarRecyclerView.setAdapter(calendarAdapter);

        //Creamos e instanciamos el layoutManager
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        //Pasamos el layoutManager al recyclerView
        calendarRecyclerView.setLayoutManager(layoutManager);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();

        //El metodo from obtiene el mes y año a traves del TemporalAccessor especificado
        YearMonth yearMonth = YearMonth.from(date);

        //Metodo lengthOfMonth devuelve la longitud del mes
        int daysInMonth = yearMonth.lengthOfMonth();

        //Con el metodo withDayOfMonth obtenemos una copia de LocalDate con el día del mes alterado.
        //Obtenemos el primer dia del mes
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i=1; i <= 42; i++){
            if(i<=dayOfWeek || i>daysInMonth + dayOfWeek){
                daysInMonthArray.add("");
            }else{
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }
    public void PreviousMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void NextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}