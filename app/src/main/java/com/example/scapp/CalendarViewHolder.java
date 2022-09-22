package com.example.scapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

//Clase que controla cada vista por individual del calendario
public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    //Clase que hereda a la clase RecyclerView.ViewHolder
    //RecyclerView.ViewHolder es una clase que amplia ViewHolder la cual sera utilizada por el Adaptador

    public final View parentView;
    public final TextView cellDate;
    public final TextView cellDay;
    private final CalendarAdapter.OnItemListener onItemListener;
    private final ArrayList<LocalDate> days;


    //Constructor
    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener,ArrayList<LocalDate> days) {

        //Con super creamos una instancia de la clase Padre RecyclerView y pasamos el parametro itemView
        //En la clase padre la variable itemView que pasamos como parametro se va a inicializar
        super(itemView);
        parentView = itemView.findViewById(R.id.parentView);
        cellDate = itemView.findViewById(R.id.cellDate_txtView);
        cellDay = itemView.findViewById(R.id.cellDay_txtView);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
        this.days = days;

    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(), days.get(getAdapterPosition()));
    }
}
