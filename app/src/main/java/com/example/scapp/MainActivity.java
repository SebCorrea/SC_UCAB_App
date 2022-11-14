package com.example.scapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.example.scapp.CalendarConfig.CalendarAdapter;
import com.example.scapp.CalendarConfig.CalendarScroll;
import com.example.scapp.CalendarConfig.CalendarUtils;
import com.example.scapp.SubjectsConfig.SubjectsAdapter;
import com.example.scapp.SubjectsConfig.SubjectsDialogFragment;
import com.example.scapp.SubjectsConfig.SubjectsUtils;

import java.time.LocalDate;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SubjectsAdapter.onItemListener{

    private TextView monthYear_txtView;
    public static TextView prueba;
    private RecyclerView calendarRecyclerView;
    private Button addSubject_btn;
    private RecyclerView subjectRecyclerView;

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
        addSubject_btn.setOnClickListener(view -> showSubjectAlertDialogConfig());
    }

    private void recyclerCalendarConfig() {
        //Initial Config
        CalendarUtils.setActualDate(LocalDate.now());
        List<LocalDate[]> weeks = CalendarUtils.generateInitialWeeks();
        //Adapter
        CalendarAdapter calendarAdapter = new CalendarAdapter(weeks);
        calendarRecyclerView.setAdapter(calendarAdapter);
        //Layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        calendarRecyclerView.setLayoutManager(layoutManager);
        //Scroll
         // se generan 13 semanas donde la 6ta es la semana actual
        calendarRecyclerView.scrollToPosition(CalendarUtils.ACTUAL_WEEK);
        calendarRecyclerView.addOnScrollListener(new CalendarScroll(layoutManager, calendarAdapter, monthYear_txtView));
        //Scroll Animation
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(calendarRecyclerView); //El RecyclerView obtiene propiedades de ViewPager2
    }

    private void recyclerSubjectsConfig(){
        //Adapter
        SubjectsAdapter subjectsAdapter = new SubjectsAdapter(SubjectsUtils.getSubjectsNames(), this);
        subjectRecyclerView.setAdapter(subjectsAdapter);
        //Layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        subjectRecyclerView.setLayoutManager(layoutManager);
    }

    //Generamos el AlertDialog
    private void showSubjectAlertDialogConfig() {
        //Initial Config to generate and show AlertDialog
        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.subjects_alert_dialog, null);
        builder.setView(view);
        AlertDialog myDialog = builder.create();
        myDialog.show();

        Button agregar_btn = view.findViewById(R.id.agregar_btn);
        EditText subject_EditText = view.findViewById(R.id.subject_EditText);
        TextInputLayout subject_TextInputLayout = view.findViewById(R.id.subject_TextInputLayout);

        //new SubjectsUtils(agregar_btn, subject_EditText,subject_TextInputLayout);
        SubjectsUtils.SubjectAlertDialogOptions(agregar_btn, subject_EditText, (SubjectsAdapter) subjectRecyclerView.getAdapter(), myDialog );

         */

        SubjectsDialogFragment subjectsDialogFragment = new SubjectsDialogFragment();
        subjectsDialogFragment.show(getSupportFragmentManager(),"SubjectUtils");


    }

    @Override
    public void onItemClickListener(String dayText) {
        prueba.setText(dayText);
    }
}