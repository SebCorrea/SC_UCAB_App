package com.example.scapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {


    private final ArrayList<LocalDate> days;

    public CalendarAdapter(ArrayList<LocalDate> days) {
        //Recibimos los parametros
        this.days = days;
    }

    @Override
    public void onViewRecycled(@NonNull CalendarViewHolder holder) {
        super.onViewRecycled(holder);
    }

    //RecyclerView llama a este método cada vez que necesite crear un ViewHolder nuevo (Vista individual nueva)
    //El ViewHolder contiene el View el cual contiene el diseño del elemento individual de la vista
    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Con getContext obtenemos el contexto en el que se ejecuta la vista
        //Con from obtenemos el LayoutInflater del contexto dado
        //LayoutInflater instancia un archivo XML
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        //Infla una nueva jerarquía de vistas desde el XML especificado
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);

        //getLayoutParams obtiene los parametros asociados al View
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (parent.getWidth()/7);

        //Retornamos el objeto de tipo CalendarViewHolder
        return new CalendarViewHolder(view, days);
    }

    //Pasa por cada item de la lista
    //Recupera los datos correspondientes y los usa para completar el diseño del contenedor de vistas
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {

        final LocalDate date = days.get(position);

        if(date == null){
            holder.cellDate.setText("");
            holder.cellDay.setText("");
        }else{
            holder.cellDate.setText(String.valueOf(date.getDayOfMonth()));
            holder.cellDay.setText(CalendarUtils.Days(date));
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
