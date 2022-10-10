package com.example.scapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.List;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final List<LocalDate[]> weeks;
    //public final TextView sunDate, monDate,tueDate,wedDate,thurDate,friDate,satDate;
    public String[] s;
    private final CalendarAdapter.OnItemListener onItemListener;
    TextView[] weeksDaysTxtViews = {itemView.findViewById(R.id.sunDate_txtView),
                                    itemView.findViewById(R.id.monDate_txtView),
                                    itemView.findViewById(R.id.tueDate_txtView),
                                    itemView.findViewById(R.id.wedDate_txtView),
                                    itemView.findViewById(R.id.thurDate_txtView),
                                    itemView.findViewById(R.id.friDate_txtView),
                                    itemView.findViewById(R.id.satDate_txtView)};

    //Constructor
    public CalendarViewHolder(List<LocalDate[]> weeks, @NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        this.weeks = weeks;


        TextView[] weeksDaysTxtViews = {itemView.findViewById(R.id.sunDate_txtView),
                                itemView.findViewById(R.id.monDate_txtView),
                                itemView.findViewById(R.id.tueDate_txtView),
                                itemView.findViewById(R.id.wedDate_txtView),
                                itemView.findViewById(R.id.thurDate_txtView),
                                itemView.findViewById(R.id.friDate_txtView),
                                itemView.findViewById(R.id.satDate_txtView)};

        /*
        sunDate = itemView.findViewById(R.id.sunDate_txtView);
        monDate = itemView.findViewById(R.id.monDate_txtView);
        tueDate = itemView.findViewById(R.id.tueDate_txtView);
        wedDate = itemView.findViewById(R.id.wedDate_txtView);
        thurDate = itemView.findViewById(R.id.thurDate_txtView);
        friDate = itemView.findViewById(R.id.friDate_txtView);
        satDate = itemView.findViewById(R.id.satDate_txtView);

         */
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(),weeks.get(getAdapterPosition())[0]);
    }
}
