package com.example.scapp;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private final List<LocalDate[]> weeks;
    private final CalendarAdapter.OnItemListener onItemListener;
    Drawable calendarBackground;

    public CalendarAdapter(List<LocalDate[]> days, OnItemListener onItemListener) {
        this.weeks = days;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height =(int) (ViewGroup.LayoutParams.WRAP_CONTENT);

        return new CalendarViewHolder(weeks, view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {

        final LocalDate[] week = weeks.get(position);

        for(int i = 0; i<week.length; i++){
            holder.weeksDaysTxtViews[i].setText(String.valueOf(week[i].getDayOfMonth()));

            ViewGroup view = (ViewGroup) holder.weeksDaysTxtViews[i].getParent();
            if (week[i].equals(CalendarUtils.selectedDate)){
                calendarBackground = ContextCompat.getDrawable(view.getContext(), R.drawable.calendar_background_actualdate);
            }else{
                calendarBackground = ContextCompat.getDrawable(view.getContext(), R.drawable.calendar_background);
            }
            view.setBackground(calendarBackground);
        }
    }

    @Override
    public int getItemCount() {
        return weeks.size();
    }

    public interface OnItemListener{
        void onItemClick(int position, LocalDate date);
    }
}
