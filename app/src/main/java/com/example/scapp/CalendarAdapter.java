package com.example.scapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private final List<LocalDate[]> days;

    public CalendarAdapter(List<LocalDate[]> days) {
        //Recibimos los parametros
        this.days = days;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Infla una nueva jerarquía de vistas desde el XML especificado
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = parent.getWidth();

        //Retornamos el objeto de tipo CalendarViewHolder
        return new CalendarViewHolder(view);
    }

    //Pasa por cada item de la lista
    //Recupera los datos correspondientes y los usa para completar el diseño del contenedor de vistas
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {

        final LocalDate[] date1 = days.get(position);

        if(date1 == null){
            holder.sunDate.setText("");
            holder.monDate.setText("");
            holder.tueDate.setText("");
            holder.wedDate.setText("");
            holder.thurDate.setText("");
            holder.friDate.setText("");
            holder.satDate.setText("");

        }else{
            holder.sunDate.setText(String.valueOf(date1[0].getDayOfMonth()));
            holder.monDate.setText(String.valueOf(date1[1].getDayOfMonth()));
            holder.tueDate.setText(String.valueOf(date1[2].getDayOfMonth()));
            holder.wedDate.setText(String.valueOf(date1[3].getDayOfMonth()));
            holder.thurDate.setText(String.valueOf(date1[4].getDayOfMonth()));
            holder.friDate.setText(String.valueOf(date1[5].getDayOfMonth()));
            holder.satDate.setText(String.valueOf(date1[6].getDayOfMonth()));
        }
    }

    //RecyclerView llama a este metodo para obtener el tamaño del conjunto de datos
    @Override
    public int getItemCount() {
        return days.size();
    }

    //Creamos una interfaz
    public interface OnItemListener{
        //Metodo OnItemClick
        void onItemClick(int position, LocalDate date);

    }
}
