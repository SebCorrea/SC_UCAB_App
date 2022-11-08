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
            holder.weeksDatesTxtViews[i].setText(String.valueOf(week[i].getDayOfMonth())); //Colocamos los dias del mes en el calendario

            calendarColors(holder, week[i], i);
        }
    }

    @Override
    public int getItemCount() {
        return weeks.size();
    }

    private static void calendarColors(CalendarViewHolder holder, LocalDate localDate, int i){

        int backgroundColor;
        int dateTextViewColor;
        int dayTextViewColor;
        ViewGroup view = (ViewGroup) holder.weeksDatesTxtViews[i].getParent(); //Obtenemos la vista padre
        if (localDate.equals(CalendarUtils.actualDate)){
            backgroundColor = R.color.teal_700;
            dateTextViewColor = Color.WHITE;
            dayTextViewColor = Color.WHITE;
        }else{
            backgroundColor = R.color.transparent;
            dateTextViewColor = Color.BLACK;
            dayTextViewColor = Color.GRAY;
        }
        view.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(),backgroundColor)));
        holder.weeksDatesTxtViews[i].setTextColor(dateTextViewColor);
        holder.weeksDaysTxtViews[i].setTextColor(dayTextViewColor);
    }
}
