package com.example.scapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import android.os.Bundle;
import com.example.scapp.ui.calendarUI.CalendarAdapter;
import com.example.scapp.ui.calendarUI.CalendarScroll;
import com.example.scapp.CalendarConfig.CalendarUtils;
import com.example.scapp.SubjectsConfig.SubjectsAdapter;
import com.example.scapp.SubjectsConfig.SubjectsDialogFragment;
import com.example.scapp.databinding.ActivityMainBinding;
import com.example.scapp.viewmodel.CalendarViewModel;

public class MainActivity extends AppCompatActivity implements SubjectsAdapter.onItemListener, CalendarAdapter.pruebita{

    private ActivityMainBinding binding;
    private CalendarViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(CalendarViewModel.class);

        //App configs
        recyclerCalendarConfig();
        recyclerSubjectsConfig();
        binding.addSubjectsBtn.setOnClickListener(view -> showSubjectAlertDialogConfig());
    }

    private void recyclerCalendarConfig() {

        //viewModel.generateInitialWeeks(LocalDate.now());

        CalendarAdapter calendarAdapter = new CalendarAdapter(viewModel.getWeeks().getValue());
        binding.calendarRecyclerView.setAdapter(calendarAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.calendarRecyclerView.setLayoutManager(layoutManager);

        //Scroll
        binding.calendarRecyclerView.scrollToPosition(CalendarUtils.ACTUAL_WEEK); //Se generan 13 semanas donde la 6ta es la semana actual
        binding.calendarRecyclerView.addOnScrollListener(new CalendarScroll(this,this,viewModel,layoutManager, calendarAdapter));

        //Scroll Animation
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(binding.calendarRecyclerView); //El RecyclerView obtiene propiedades de ViewPager2

        /*
        //Initial Config
        CalendarUtils.setActualDate(LocalDate.now());
        List<LocalDate[]> weeks = CalendarUtils.generateInitialWeeks();
        //Adapter
        CalendarAdapter calendarAdapter = new CalendarAdapter(weeks);
        binding.calendarRecyclerView.setAdapter(calendarAdapter);
        //Layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.calendarRecyclerView.setLayoutManager(layoutManager);
        //Scroll
        // se generan 13 semanas donde la 6ta es la semana actual
        binding.calendarRecyclerView.scrollToPosition(CalendarUtils.ACTUAL_WEEK);
        binding.calendarRecyclerView.addOnScrollListener(new CalendarScroll(layoutManager, calendarAdapter, binding.monthYearTxtView));
         */
    }

    private void recyclerSubjectsConfig(){
        //Adapter
        SubjectsAdapter subjectsAdapter = new SubjectsAdapter(SubjectsDialogFragment.getSubjectsNames(), this);
        binding.subjectRecyclerView.setAdapter(subjectsAdapter);
        //Layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.subjectRecyclerView.setLayoutManager(layoutManager);
    }

    //Generamos el AlertDialog
    private void showSubjectAlertDialogConfig() {
        //Initial Config to generate and show AlertDialog
        SubjectsDialogFragment subjectsDialogFragment = new SubjectsDialogFragment((SubjectsAdapter) binding.subjectRecyclerView.getAdapter() );
        subjectsDialogFragment.show(getSupportFragmentManager(),"SubjectUtils");
    }

    @Override
    public void onItemClickListener(String dayText) {
        binding.prueba.setText(dayText);
    }


    @Override
    public void mostrarMes(String month) {
        binding.monthYearTxtView.setText(month);
    }
}