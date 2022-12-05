package com.example.scapp.ui.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.scapp.ui.calendarUI.CalendarAdapter;
import com.example.scapp.ui.subjectsUI.RemoveSubjects_DialogFragment;
import com.example.scapp.ui.subjectsUI.SubjectsAdapter;
import com.example.scapp.ui.subjectsUI.AddSubjects_DialogFragment;
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
        recyclerCalendarConfig();
        recyclerSubjectsConfig();
        binding.addSubjectsBtn.setOnClickListener(view -> showSubjectAlertDialogConfig());
    }

    private void recyclerCalendarConfig() {

        calendarViewModel.generateInitialWeeks(); //Genera las primeras semanas que se muestran en el RecyclerView
        new PagerSnapHelper().attachToRecyclerView(binding.calendarRecyclerView); //Scroll Animation ViewPager

        calendarViewModel.getInitialWeeks().observe(this, initialWeeks->{
            CalendarAdapter calendarAdapter = new CalendarAdapter(initialWeeks);
            binding.calendarRecyclerView.setAdapter(calendarAdapter);
        });

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
                        calendarViewModel.generatePlusWeeks((CalendarAdapter) binding.calendarRecyclerView.getAdapter());
                    }else if(position<=3){
                        calendarViewModel.generateMinusWeeks((CalendarAdapter) binding.calendarRecyclerView.getAdapter());
                    }
                }
            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                position = layoutManager.findFirstVisibleItemPosition(); //Obtenemos la posición del adaptador
                calendarViewModel.setMonthAndYear(position);
            }
        });
    }

    private void recyclerSubjectsConfig(){

        subjectsViewModel.set_subjects();

        subjectsViewModel.get_Subjects().observe(this, subjects->{
            SubjectsAdapter subjectsAdapter = new SubjectsAdapter(subjectsButtonsActions, subjects);
            binding.subjectRecyclerView.setAdapter(subjectsAdapter);
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.subjectRecyclerView.setLayoutManager(layoutManager);
    }

    private void showSubjectAlertDialogConfig() {
        AddSubjects_DialogFragment addSubjectsDialogFragment = new AddSubjects_DialogFragment((SubjectsAdapter) binding.subjectRecyclerView.getAdapter());
        addSubjectsDialogFragment.show(getSupportFragmentManager(),"addSubjectsDialogFragment");
    }

    SubjectsAdapter.SubjectsButtonsActions subjectsButtonsActions = new SubjectsAdapter.SubjectsButtonsActions() {

        @Override
        public void onItemClickListener(String subjectName) {
            binding.prueba.setText(subjectName);
        }

        @Override
        public boolean onItemLongClickListener(String subjectName) {
            RemoveSubjects_DialogFragment removeSubjectsDialogFragment = new RemoveSubjects_DialogFragment((SubjectsAdapter) binding.subjectRecyclerView.getAdapter());
            removeSubjectsDialogFragment.show(getSupportFragmentManager(),"removeSubjectsDialogFragment");
            return true;
        }
    };

}