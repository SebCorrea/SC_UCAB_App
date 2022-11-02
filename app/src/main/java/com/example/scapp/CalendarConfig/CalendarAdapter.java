package com.example.scapp.CalendarConfig;
import android.graphics.drawable.Drawable;
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

        final LocalDate[] week = weeks.get(position);
        Drawable calendarBackground;

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

}
