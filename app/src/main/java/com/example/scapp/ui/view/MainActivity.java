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
        calendarViewModel.setWeeks();
        recyclerCalendarConfig();
        recyclerSubjectsConfig();
        binding.addSubjectsBtn.setOnClickListener(view -> showSubjectAlertDialogConfig());
    }

    private void recyclerCalendarConfig() {
        CalendarAdapter calendarAdapter = new CalendarAdapter(calendarViewModel.getWeeks().getValue());
        binding.calendarRecyclerView.setAdapter(calendarAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.calendarRecyclerView.setLayoutManager(layoutManager);
        new PagerSnapHelper().attachToRecyclerView(binding.calendarRecyclerView); //Scroll Animation
        final int ACTUALWEEK = 6;
        binding.calendarRecyclerView.scrollToPosition(ACTUALWEEK); //Se generan 13 semanas donde la 6ta es la semana actual

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

                calendarViewModel.getWeeks().observe(MainActivity.this, weeks-> calendarViewModel.setMonthAndYear(weeks.get(position)[0]));
                //calendarViewModel.getMonthAndYear().observe(MainActivity.this, monthAndYear-> binding.monthYearTxtView.setText(monthAndYear));


                /*
                calendarViewModel.getWeeks().observe(MainActivity.this, new Observer<List<LocalDate[]>>() {
                    @Override
                    public void onChanged(List<LocalDate[]> weeks) {
                        LocalDate scrollDate = weeks.get(position)[0];
                        calendarViewModel.setMonthAndYear(scrollDate);
                    }
                });

                calendarViewModel.getMonthAndYear().observe(MainActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        binding.monthYearTxtView.setText(s);

                    }
                });
                 */

                /*
                calendarViewModel.getWeeks().observe(MainActivity.this, weeks->{
                    LocalDate scrollDate = weeks.get(position)[0];
                    calendarViewModel.setMonthAndYear(scrollDate);
                    calendarViewModel.getMonthAndYear().observe(MainActivity.this, monthAndYear -> binding.monthYearTxtView.setText(monthAndYear));
                });

                 */
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