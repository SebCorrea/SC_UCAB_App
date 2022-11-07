package com.example.scapp.CalendarConfig;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scapp.R;

public class CalendarViewHolder extends RecyclerView.ViewHolder{

    public TextView[] weeksDatesTxtViews = new TextView[7];
    public TextView[] weeksDaysTxtViews = new TextView[7];

    public CalendarViewHolder(@NonNull View itemView) {
        super(itemView);
        weeksDatesTxtViews[0] = itemView.findViewById(R.id.sunDate_txtView);
        weeksDatesTxtViews[1] = itemView.findViewById(R.id.monDate_txtView);
        weeksDatesTxtViews[2] = itemView.findViewById(R.id.tueDate_txtView);
        weeksDatesTxtViews[3] = itemView.findViewById(R.id.wedDate_txtView);
        weeksDatesTxtViews[4] = itemView.findViewById(R.id.thurDate_txtView);
        weeksDatesTxtViews[5] = itemView.findViewById(R.id.friDate_txtView);
        weeksDatesTxtViews[6] = itemView.findViewById(R.id.satDate_txtView);

        weeksDaysTxtViews[0] = itemView.findViewById(R.id.sunDay_txtView);
        weeksDaysTxtViews[1] = itemView.findViewById(R.id.monDay_txtView);
        weeksDaysTxtViews[2] = itemView.findViewById(R.id.tueDay_txtView);
        weeksDaysTxtViews[3] = itemView.findViewById(R.id.wedDay_txtView);
        weeksDaysTxtViews[4] = itemView.findViewById(R.id.thurDay_txtView);
        weeksDaysTxtViews[5] = itemView.findViewById(R.id.friDay_txtView);
        weeksDaysTxtViews[6] = itemView.findViewById(R.id.satDay_txtView);

    }
}
