package com.example.scapp.CalendarConfig;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
            holder.weeksDatesTxtViews[i].setText(String.valueOf(week[i].getDayOfMonth()));
            ViewGroup view = (ViewGroup) holder.weeksDatesTxtViews[i].getParent();
            if (week[i].equals(CalendarUtils.actualDate)){
                view.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(),R.color.teal_700)));
                holder.weeksDatesTxtViews[i].setTextColor(Color.WHITE);
                holder.weeksDaysTxtViews[i].setTextColor(Color.WHITE);
            }else{
                view.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
                holder.weeksDatesTxtViews[i].setTextColor(Color.BLACK);
                holder.weeksDaysTxtViews[i].setTextColor(Color.GRAY);
            }
        }
    }

    @Override
    public int getItemCount() {
        return weeks.size();
    }

}
