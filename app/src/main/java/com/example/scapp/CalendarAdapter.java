package com.example.scapp;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private final List<LocalDate[]> weeks;
    private final CalendarAdapter.OnItemListener onItemListener;

    public CalendarAdapter(List<LocalDate[]> days, OnItemListener onItemListener) {
        this.weeks = days;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Infla una nueva jerarquía de vistas desde el XML especificado
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height =(int) (ViewGroup.LayoutParams.WRAP_CONTENT);

        return new CalendarViewHolder(weeks, view, onItemListener);
    }

    //Pasa por cada item de la lista
    //Recupera los datos correspondientes y los usa para completar el diseño del contenedor de vistas
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {

        final LocalDate[] week = weeks.get(position);
        for(int i = 0; i<week.length; i++){
            holder.weeksDaysTxtViews[i].setText(String.valueOf(week[i].getDayOfMonth()));
            ViewGroup view = (ViewGroup) holder.weeksDaysTxtViews[i].getParent();
            if (week[i].equals(CalendarUtils.selectedDate)){
                view.setBackgroundColor(Color.BLUE);
            }else{
                //view.setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

    //RecyclerView llama a este metodo para obtener el tamaño del conjunto de datos
    @Override
    public int getItemCount() {
        return weeks.size();
    }

    public interface OnItemListener{
        //Metodo OnItemClick
        void onItemClick(int position, LocalDate date);

    }
}
