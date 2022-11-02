package com.example.scapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.scapp.CalendarConfig.CalendarAdapter;
import com.example.scapp.CalendarConfig.CalendarScroll;
import com.example.scapp.CalendarConfig.CalendarUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private TextView monthYear_txtView;
    public static TextView prueba;
    private RecyclerView calendarRecyclerView;
    private Button addSubject_btn;
    private RecyclerView subjectRecyclerView;

    List<String> subjectsNames = new ArrayList<>();
    SubjectsAdapter subjectsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        recyclerCalendarConfig();
        recyclerSubjectsConfig();
        addSubject_btn.setOnClickListener(view -> showSubjectPopup());
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        subjectRecyclerView = findViewById(R.id.subjectRecyclerView);
        monthYear_txtView = findViewById(R.id.monthYear_txtView);
        prueba = findViewById(R.id.prueba);
        addSubject_btn = findViewById(R.id.addSubjects_btn);
    }

    private void recyclerCalendarConfig() {
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
        final int ACTUAL_WEEK = 6; // se generan 13 semanas donde la 6ta es la semana actual
        calendarRecyclerView.scrollToPosition(ACTUAL_WEEK);
        calendarRecyclerView.addOnScrollListener(new CalendarScroll(layoutManager, calendarAdapter, weeks, monthYear_txtView));
        //Scroll Animation
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(calendarRecyclerView); //El RecyclerView obtiene propiedades de ViewPager2
    }

    private void recyclerSubjectsConfig(){
        if(subjectsNames != null){

        }
        subjectsAdapter = new SubjectsAdapter(subjectsNames);
        subjectRecyclerView.setAdapter(subjectsAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        subjectRecyclerView.setLayoutManager(layoutManager);

    }

    private void showSubjectPopup() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.subjects_popup, null);
        builder.setView(view);
        AlertDialog myDialog = builder.create();
        myDialog.show();

        Button cancel_btn = view.findViewById(R.id.cancel_btn);
        Button agregar_btn = view.findViewById(R.id.agregar_btn);
        EditText subject_EditText = view.findViewById(R.id.subject_EditText);

        cancel_btn.setOnClickListener(v -> {
            prueba.setText("Cancelado");
            prueba.setText(agregar_btn.getText().toString());
            myDialog.dismiss();
        });

        agregar_btn.setOnClickListener(v -> {
            prueba.setText("Agregado");
            myDialog.dismiss();

            if(!subject_EditText.getText().toString().trim().equals("")){

                subjectsNames.add(0,subject_EditText.getText().toString());
                subjectsAdapter.notifyItemInserted(0);

            }
        });
    }

}