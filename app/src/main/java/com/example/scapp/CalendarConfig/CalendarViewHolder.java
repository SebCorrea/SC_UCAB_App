package com.example.scapp.CalendarConfig;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scapp.R;

public class CalendarViewHolder extends RecyclerView.ViewHolder{

    public TextView[] weeksDaysTxtViews = new TextView[7];

    public CalendarViewHolder(@NonNull View itemView) {
        super(itemView);
        weeksDaysTxtViews[0] = itemView.findViewById(R.id.sunDate_txtView);
        weeksDaysTxtViews[1] = itemView.findViewById(R.id.monDate_txtView);
        weeksDaysTxtViews[2] = itemView.findViewById(R.id.tueDate_txtView);
        weeksDaysTxtViews[3] = itemView.findViewById(R.id.wedDate_txtView);
        weeksDaysTxtViews[4] = itemView.findViewById(R.id.thurDate_txtView);
        weeksDaysTxtViews[5] = itemView.findViewById(R.id.friDate_txtView);
        weeksDaysTxtViews[6] = itemView.findViewById(R.id.satDate_txtView);
    }
}