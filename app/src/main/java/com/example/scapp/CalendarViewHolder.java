package com.example.scapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

//Clase que controla cada vista por individual del calendario
public class CalendarViewHolder extends RecyclerView.ViewHolder{
    //Clase que hereda a la clase RecyclerView.ViewHolder
    //RecyclerView.ViewHolder es una clase que amplia ViewHolder la cual sera utilizada por el Adaptador

    public final View parentView;
    public final TextView cellDate;
    public final TextView cellDay;
    private final ArrayList<LocalDate> days;


    //Constructor
    public CalendarViewHolder(@NonNull View itemView,ArrayList<LocalDate> days) {
        super(itemView);

        parentView = itemView.findViewById(R.id.parentView);
        cellDate = itemView.findViewById(R.id.cellDate_txtView);
        cellDay = itemView.findViewById(R.id.cellDay_txtView);
        this.days = days;

    }

}
