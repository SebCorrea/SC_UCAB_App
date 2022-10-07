package com.example.scapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//Clase que controla cada vista por individual del calendario
public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //Clase que hereda a la clase RecyclerView.ViewHolder
    //RecyclerView.ViewHolder es una clase que amplia ViewHolder la cual sera utilizada por el Adaptador

    public final View parentView;
    public final TextView sunDate, monDate,tueDate,wedDate,thurDate,friDate,satDate;

    //Constructor
    public CalendarViewHolder(@NonNull View itemView) {
        super(itemView);

        parentView = itemView.findViewById(R.id.parentView);

        sunDate = itemView.findViewById(R.id.sunDate_txtView);
        monDate = itemView.findViewById(R.id.monDate_txtView);
        tueDate = itemView.findViewById(R.id.tueDate_txtView);
        wedDate = itemView.findViewById(R.id.wedDate_txtView);
        thurDate = itemView.findViewById(R.id.thurDate_txtView);
        friDate = itemView.findViewById(R.id.friDate_txtView);
        satDate = itemView.findViewById(R.id.satDate_txtView);

    }

    @Override
    public void onClick(View v) {
        MainActivity.prueba.setText("holi");
    }
}
