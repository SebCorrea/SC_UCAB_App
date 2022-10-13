package com.example.scapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.List;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final List<LocalDate[]> weeks;
    private final CalendarAdapter.OnItemListener onItemListener;
    TextView[] weeksDaysTxtViews = new TextView[7];

    public CalendarViewHolder(List<LocalDate[]> weeks, @NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        this.weeks = weeks;
        this.onItemListener = onItemListener;
        weeksDaysTxtViews[0] = itemView.findViewById(R.id.sunDate_txtView);
        weeksDaysTxtViews[1] = itemView.findViewById(R.id.monDate_txtView);
        weeksDaysTxtViews[2] = itemView.findViewById(R.id.tueDate_txtView);
        weeksDaysTxtViews[3] = itemView.findViewById(R.id.wedDate_txtView);
        weeksDaysTxtViews[4] = itemView.findViewById(R.id.thurDate_txtView);
        weeksDaysTxtViews[5] = itemView.findViewById(R.id.friDate_txtView);
        weeksDaysTxtViews[6] = itemView.findViewById(R.id.satDate_txtView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(),weeks.get(getAdapterPosition())[0]);
    }
}
