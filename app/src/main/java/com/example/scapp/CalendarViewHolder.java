package com.example.scapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

//Clase que controla cada vista por individual del calendario
public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //Clase que hereda a la clase RecyclerView.ViewHolder
    //RecyclerView.ViewHolder es una clase que amplia ViewHolder la cual sera utilizada por el Adaptador

    public final View parentView;
    public final TextView cellDate, monDate,tueDate,wedDate,thurDate,friDate,satDate;
    public final TextView cellDay;

    //Constructor
    public CalendarViewHolder(@NonNull View itemView) {
        super(itemView);

        parentView = itemView.findViewById(R.id.parentView);

        cellDate = itemView.findViewById(R.id.cellDate_txtView);
        monDate = itemView.findViewById(R.id.monDate_txtView);
        tueDate = itemView.findViewById(R.id.tueDate_txtView);
        wedDate = itemView.findViewById(R.id.wedDate_txtView);
        thurDate = itemView.findViewById(R.id.thurDate_txtView);
        friDate = itemView.findViewById(R.id.friDate_txtView);
        satDate = itemView.findViewById(R.id.satDate_txtView);

        cellDay = itemView.findViewById(R.id.cellDay_txtView);
        cellDay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        MainActivity.prueba.setText("holi");
    }
}
