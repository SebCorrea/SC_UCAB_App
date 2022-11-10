package com.example.scapp.CalendarConfig;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.scapp.R;
import java.time.LocalDate;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private final List<LocalDate[]> weeks;

    public CalendarAdapter(List<LocalDate[]> days) {
        this.weeks = days;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (ViewGroup.LayoutParams.WRAP_CONTENT);
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        LocalDate[] week = weeks.get(position);
        for(int i = 0; i<week.length; i++){
            LocalDate date = week[i];
            TextView weekDateTxtView = holder.weeksDatesTxtViews[i];
            TextView weekDaysTxtView = holder.weeksDaysTxtViews[i];
            weekDateTxtView.setText(String.valueOf(date.getDayOfMonth())); //Colocamos los dias del mes en el calendario
            CalendarDesing.CalendarStyleBackground(weekDateTxtView,weekDaysTxtView,date);
        }
    }

    @Override
    public int getItemCount() {
        return weeks.size();
    }


}
