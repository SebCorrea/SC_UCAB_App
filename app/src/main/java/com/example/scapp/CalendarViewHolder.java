package com.example.scapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //Clase que hereda a la clase RecyclerView.ViewHolder
    //RecyclerView.ViewHolder es una clase que amplia ViewHolder la cual sera utilizada por el Adaptador

    public final TextView daysOfMonth;
    private final CalendarAdapter.OnItemListener onItemListener;

    //Constructor
    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {

        //Con super creamos una instancia de la clase Padre RecyclerView y pasamos el parametro itemView
        //En la clase padre la variable itemView que pasamos como parametro se va a inicializar
        super(itemView);
        daysOfMonth = itemView.findViewById(R.id.cellDay_txtView);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Llamamos al metodo onItemClick
        //getAdapterPosition devuelve la posición del adaptador del elemento representado por este ViewHolder.
        onItemListener.onItemClick(getAdapterPosition(), (String) daysOfMonth.getText());
    }
}
