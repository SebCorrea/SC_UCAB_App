package com.example.scapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
    //Pasamos como parametro la clase CalendarViewHolder a la clase RecyclerView.Adapter
    //Heredamos los metodos de la clase RecyclerView.Adapter

    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;

    //Constructor
    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener) {
        //Recibimos los parametros
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
    }

    //RecyclerView llama a este método cada vez que necesite crear un ViewHolder nuevo
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
        layoutParams.height = (int)(parent.getHeight() * 0.16666666);

        //Retornamos el objeto de tipo CalendarViewHolder
        return new CalendarViewHolder(view, onItemListener);
    }

    //RecyclerView llama a este metodo para asociar un ViewHolder con los datos
    //Recupera los datos correspondientes y los usa para completar el diseño del contenedor de vistas
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        //El textView muestra la posicion del ArrayList
        holder.daysOfMonth.setText(daysOfMonth.get(position));
    }


    //RecyclerView llama a este metodo para obtener el tamaño del conjunto de datos
    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    //Creamos una interfaz
    public interface OnItemListener{
        //Metodo OnItemClick
        void onItemClick(int position, String dayText);
    }
}
