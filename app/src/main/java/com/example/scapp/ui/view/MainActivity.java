package com.example.scapp.ui.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.example.scapp.ui.calendarUI.CalendarAdapter;
import com.example.scapp.ui.subjectsUI.SubjectsAdapter;
import com.example.scapp.ui.subjectsUI.SubjectsDialogFragment;
import com.example.scapp.databinding.ActivityMainBinding;
import com.example.scapp.viewmodel.CalendarViewModel;
import com.example.scapp.viewmodel.SubjectsViewModel;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity{

    private CalendarViewModel calendarViewModel;
    private SubjectsViewModel subjectsViewModel;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        subjectsViewModel = new ViewModelProvider(this).get(SubjectsViewModel.class);
        calendarViewModel = new ViewModelProvider(this).get(CalendarViewModel.class);

        //App configs
        recyclerCalendarConfig();
        recyclerSubjectsConfig();
        binding.addSubjectsBtn.setOnClickListener(view -> showSubjectAlertDialogConfig());
    }

    private void recyclerCalendarConfig() {

        calendarViewModel.generateInitialWeeks(); //Genera las primeras semanas que se muestran en el RecyclerView
        new PagerSnapHelper().attachToRecyclerView(binding.calendarRecyclerView); //Scroll Animation ViewPager

        CalendarAdapter calendarAdapter = new CalendarAdapter(calendarViewModel.getInitialWeeks());
        binding.calendarRecyclerView.setAdapter(calendarAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.calendarRecyclerView.setLayoutManager(layoutManager);

        binding.calendarRecyclerView.scrollToPosition(6); //Se generan 13 semanas donde la 6ta es la semana actual
        calendarViewModel.getMonthAndYear().observe(MainActivity.this, monthAndYear-> binding.monthYearTxtView.setText(monthAndYear));

        binding.calendarRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int position;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_DRAGGING){ //Mientras se scrollea con el dedo (1)

                    int totalItems = layoutManager.getItemCount()-1;
                    if(position >= totalItems-3){
                        calendarViewModel.generatePlusWeeks(calendarAdapter);
                    }else if(position<=3){
                        calendarViewModel.generateMinusWeeks(calendarAdapter);
                    }
                }
            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                position = layoutManager.findFirstVisibleItemPosition(); //Obtenemos la posición del adaptador
                LocalDate firstDayOfWeek = calendarViewModel.getInitialWeeks().get(position)[0];
                calendarViewModel.setMonthAndYear(firstDayOfWeek);
            }
        });
    }

    private void recyclerSubjectsConfig(){
        SubjectsAdapter subjectsAdapter = new SubjectsAdapter(subjectsViewModel.getSubjects().getValue(), this::onItemClickListener);
        binding.subjectRecyclerView.setAdapter(subjectsAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.subjectRecyclerView.setLayoutManager(layoutManager);
    }

    private void showSubjectAlertDialogConfig() {
        SubjectsDialogFragment subjectsDialogFragment = new SubjectsDialogFragment((SubjectsAdapter) binding.subjectRecyclerView.getAdapter());
        subjectsDialogFragment.show(getSupportFragmentManager(),"SubjectUtils");
    }

    public void onItemClickListener(String subject) {
        binding.prueba.setText(subject);
    }
}