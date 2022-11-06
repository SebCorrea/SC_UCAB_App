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
import com.example.scapp.SubjectsConfig.SubjectsAdapter;

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

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        subjectRecyclerView = findViewById(R.id.subjectRecyclerView);
        monthYear_txtView = findViewById(R.id.monthYear_txtView);
        prueba = findViewById(R.id.prueba);
        addSubject_btn = findViewById(R.id.addSubjects_btn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //App configs
        initWidgets();
        recyclerCalendarConfig();
        recyclerSubjectsConfig();
        addSubject_btn.setOnClickListener(view -> showSubjectPopupConfig());
    }

    private void recyclerCalendarConfig() {
        CalendarUtils calendarUtils = new CalendarUtils(monthYear_txtView);
        //Initial Config
        CalendarUtils.actualDate = LocalDate.now();
        List<LocalDate[]> weeks = calendarUtils.daysOfThisWeeks(CalendarUtils.actualDate);
        //Adapter
        CalendarAdapter calendarAdapter = new CalendarAdapter(weeks);
        calendarRecyclerView.setAdapter(calendarAdapter);
        //Layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        calendarRecyclerView.setLayoutManager(layoutManager);
        //Scroll
         // se generan 13 semanas donde la 6ta es la semana actual
        calendarRecyclerView.scrollToPosition(CalendarUtils.ACTUAL_WEEK);
        calendarRecyclerView.addOnScrollListener(new CalendarScroll(layoutManager, calendarAdapter, calendarUtils));
        //Scroll Animation
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(calendarRecyclerView); //El RecyclerView obtiene propiedades de ViewPager2
    }

    private void recyclerSubjectsConfig(){
        subjectsAdapter = new SubjectsAdapter(subjectsNames);
        subjectRecyclerView.setAdapter(subjectsAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        subjectRecyclerView.setLayoutManager(layoutManager);
    }

    private void showSubjectPopupConfig() {

        //Initial Config to generate and show AlertDialog
        //SubjectsUtils subjectsUtils = new SubjectsUtils();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.subjects_popup, null);
        builder.setView(view);
        AlertDialog myDialog = builder.create();
        myDialog.show();

        Button cancel_btn = view.findViewById(R.id.cancel_btn);
        Button agregar_btn = view.findViewById(R.id.agregar_btn);
        EditText subject_EditText = view.findViewById(R.id.subject_EditText);

        cancel_btn.setOnClickListener(v -> myDialog.dismiss());

        agregar_btn.setOnClickListener(v -> {
            if(!subject_EditText.getText().toString().trim().equals("")){
                subjectsNames.add(0,subject_EditText.getText().toString());
                subjectsAdapter.notifyItemInserted(0);
            }
            myDialog.dismiss();

        });
    }

}