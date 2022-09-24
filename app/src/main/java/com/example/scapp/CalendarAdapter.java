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

//Clase que genera las vistas en conjunto del calendario
public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
    //Pasamos como parametro la clase CalendarViewHolder a la clase RecyclerView.Adapter
    //Heredamos los metodos de la clase RecyclerView.Adapter

    private final ArrayList<LocalDate> days;
    private final OnItemListener onItemListener;


    //Constructor
    public CalendarAdapter(ArrayList<LocalDate> days, OnItemListener onItemListener) {
        //Recibimos los parametros
        this.days = days;
        this.onItemListener = onItemListener;
    }

    //RecyclerView llama a este método cada vez que necesite crear un ViewHolder nuevo (Vista individual nueva)
    //El ViewHolder contiene el View el cual contiene el diseño del elemento individual de la vista
    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Con getContext obtenemos el contexto en el que se ejecuta la vista, donde podremos acceder al tema actual y sus recursos
        //Con from obtenemos el LayoutInflater del contexto dado
        //LayoutInflater instancia un archivo XML
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        //Infla una nueva jerarquía de vistas desde el XML especificado
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);

        //getLayoutParams obtiene los parametros asociados al View
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        if(days.size() > 15){
            layoutParams.height = (int)(parent.getHeight() * 0.16666666);
        }else{
            layoutParams.width =(int) (parent.getWidth());

        }

        //Retornamos el objeto de tipo CalendarViewHolder
        return new CalendarViewHolder(view, onItemListener, days);
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
            if(date.equals(CalendarUtils.selectedDate)){
                holder.parentView.setBackgroundColor(Color.BLUE);
            }
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
