package com.example.scapp.ui.calendarUI;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.scapp.databinding.CalendarCellBinding;

public class CalendarViewHolder extends RecyclerView.ViewHolder{

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
