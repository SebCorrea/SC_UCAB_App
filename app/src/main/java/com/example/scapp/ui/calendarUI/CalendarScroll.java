package com.example.scapp.ui.calendarUI;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.scapp.viewmodel.CalendarViewModel;
import java.time.LocalDate;


public class CalendarScroll extends RecyclerView.OnScrollListener {

    private int position;
    private final LinearLayoutManager layoutManager;
    private final CalendarAdapter calendarAdapter;
    private final CalendarViewModel viewModel;
    private final LifecycleOwner lifecycleOwner;
    private final CalendarAdapter.pruebita pruebita;

    public CalendarScroll(CalendarAdapter.pruebita pruebita, LifecycleOwner lifecycleOwner, CalendarViewModel viewModel, LinearLayoutManager layoutManager, CalendarAdapter calendarAdapter) {
        this.layoutManager= layoutManager;
        this.calendarAdapter = calendarAdapter;
        this.viewModel = viewModel;
        this.lifecycleOwner = lifecycleOwner;
        this.pruebita = pruebita;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if(newState == RecyclerView.SCROLL_STATE_DRAGGING){ //Mientras se scrollea con el dedo (1)
            int totalItems = layoutManager.getItemCount()-1;
            if(position >= totalItems-3){
                viewModel.generatePlusWeeks(calendarAdapter); //Se generan 3 semanas siguientes y se borra 3 anteriores
            }else if(position<=3){
                viewModel.generateMinusWeeks(calendarAdapter); //Se generan 3 semenas anteriores y se borran 3 siguientes
            }
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        position = layoutManager.findFirstVisibleItemPosition(); //Obtenemos la posición del adaptador

        viewModel.getWeeks().observe(lifecycleOwner, weeks->{
            LocalDate scrollDate = weeks.get(position)[0];
            viewModel.setMonthAndYear(scrollDate);
            viewModel.getMonthAndYear().observe(lifecycleOwner, pruebita::mostrarMes);
            CalendarScroll.penePrueba penePrueba;
        });
    }

    public interface penePrueba{
        void pene(String holi);
    }

}

