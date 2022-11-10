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
import java.util.Date;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private final List<LocalDate[]> weeks;
    private final LocalDate actualDate;

    public CalendarAdapter(List<LocalDate[]> days, LocalDate actualDate) {
        this.weeks = days;
        this.actualDate = actualDate;
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

    private void calendarColors(@NonNull CalendarViewHolder holder, @NonNull LocalDate localDate, int i){

        ViewGroup view = (ViewGroup) holder.weeksDatesTxtViews[i].getParent(); //Obtenemos la vista padre
        if (localDate.equals(actualDate)){
            view.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(),R.color.teal_700)));
            holder.weeksDatesTxtViews[i].setTextColor(Color.WHITE);
            holder.weeksDaysTxtViews[i].setTextColor(Color.WHITE);
        }else{
            view.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(),R.color.transparent)));
            holder.weeksDatesTxtViews[i].setTextColor(Color.BLACK);
            holder.weeksDaysTxtViews[i].setTextColor(Color.GRAY);
        }
    }
}
