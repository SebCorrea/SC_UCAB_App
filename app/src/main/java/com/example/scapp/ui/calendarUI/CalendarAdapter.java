package com.example.scapp.ui.calendarUI;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.scapp.R;
import com.example.scapp.databinding.CalendarCellBinding;
import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;



public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private final List<LocalDate[]> weeks;


    @Inject
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
            CalendarStyle(weekDateTxtView,weekDaysTxtView,date);
        }
    }

    @Override
    public int getItemCount() {
        return weeks.size();
    }

    //Metodo para establecer el Background y los colorpalette del calendario
    private void CalendarStyle(@NonNull TextView weekDateTxtView, TextView weekDayTxtView, @NonNull LocalDate date){

        ViewGroup parentView = (ViewGroup) weekDateTxtView.getParent();
        if(date.equals(LocalDate.now())){
            parentView.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(parentView.getContext(), R.color.teal_700)));
            weekDateTxtView.setTextColor(Color.WHITE);
            weekDayTxtView.setTextColor(Color.WHITE);
        }else{
            parentView.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(parentView.getContext(), R.color.transparent)));
            weekDateTxtView.setTextColor(Color.BLACK);
            weekDayTxtView.setTextColor(Color.GRAY);
        }
    }

}

class CalendarViewHolder extends RecyclerView.ViewHolder {

    public TextView[] weeksDatesTxtViews = new TextView[7];
    public TextView[] weeksDaysTxtViews = new TextView[7];

    public CalendarViewHolder(@NonNull View itemView) {
        super(itemView);

        CalendarCellBinding binding = CalendarCellBinding.bind(itemView);
        weeksDatesTxtViews[0] = binding.sunDateTxtView;
        weeksDatesTxtViews[1] = binding.monDateTxtView;
        weeksDatesTxtViews[2] = binding.tueDateTxtView;
        weeksDatesTxtViews[3] = binding.wedDateTxtView;
        weeksDatesTxtViews[4] = binding.thurDateTxtView;
        weeksDatesTxtViews[5] = binding.friDateTxtView;
        weeksDatesTxtViews[6] = binding.satDateTxtView;

        weeksDaysTxtViews[0] = binding.sunDayTxtView;
        weeksDaysTxtViews[1] = binding.monDayTxtView;
        weeksDaysTxtViews[2] = binding.tueDayTxtView;
        weeksDaysTxtViews[3] = binding.wedDayTxtView;
        weeksDaysTxtViews[4] = binding.thurDayTxtView;
        weeksDaysTxtViews[5] = binding.friDayTxtView;
        weeksDaysTxtViews[6] = binding.satDayTxtView;
    }
}