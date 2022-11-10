package com.example.scapp.CalendarConfig;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.scapp.R;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarUtils{

    private static LocalDate actualDate;
    private static final List<LocalDate[]> weeks = new ArrayList<>();
    public static final int ACTUAL_WEEK = 6;

    public static void setActualDate(LocalDate actualDate){
        CalendarUtils.actualDate = actualDate;
    }

    public static List<LocalDate[]> generateInitialWeeks() {

        LocalDate[] week;
        LocalDate sundayOfThisWeek = sundayForDate(actualDate); //Del dia actual obtenemos el día domingo de esa misma semana para asi organizar las semanas
        if(sundayOfThisWeek != null){
            LocalDate initialDate = sundayOfThisWeek.minusWeeks(6); //Cuando inicie la app se van a generar 6 semanas pasadas a la actual
            LocalDate endDate = sundayOfThisWeek.plusWeeks(7); //Cuando inicie la app se van a generar 6 semanas futuras a la actual (Semana actual + 6 futuras)

            while (initialDate.isBefore(endDate)){
                week = new LocalDate[7];
                for(int i=0; i<week.length; i++){
                    week[i]= initialDate;
                    initialDate = initialDate.plusDays(1);
                }
                weeks.add(week); //Añadimos las semanas a la lista
            }
        }
        return weeks;
    }

    //Metodo para generar 3 semanas futuras
    public static void generatePlusWeeks(CalendarAdapter calendarAdapter){

        LocalDate[] week = weeks.get(weeks.size()-1);
        LocalDate endDate = week[week.length-1].plusDays(1);
        LocalDate newEndDate = endDate.plusWeeks(3);
        while (endDate.isBefore(newEndDate)){ //Se añadirán 3 semanas superiores a la semana actual
            week = new LocalDate[7];
            for(int i = 0; i<week.length; i++){
                week[i] = endDate;
                endDate = endDate.plusDays(1);
            }
            weeks.add(week); //Se añaden las 3 semanas futuras a la semana actual
            calendarAdapter.notifyItemInserted(weeks.size()-1);
            weeks.remove(0); //Se eliminan 3 semanas anteriores a la semana actual
            calendarAdapter.notifyItemRemoved(0);
        }
    }
    //Metodo para generar 3 semanas pasadas
    public static void generateMinusWeeks(CalendarAdapter calendarAdapter){

        LocalDate[] week = weeks.get(0);
        LocalDate initialDate = week[0].minusDays(1);
        LocalDate newInitialDate = initialDate.minusWeeks(3);
        while (initialDate.isAfter(newInitialDate)){ //Se añadirán 3 semanas pasadas a la semana actual
            week = new LocalDate[7];
            for(int i=week.length-1; i>=0; i--){
                week[i] = initialDate;
                initialDate = initialDate.minusDays(1);
            }
            weeks.add(0,week); //Se añaden las 3 semanas pasadas a la semana actual
            calendarAdapter.notifyItemInserted(0);
            weeks.remove(weeks.size()-1); //Se eliminan 3 semanas futuras a la semana actual
            calendarAdapter.notifyItemRemoved(weeks.size()-1);
        }
    }

    //Metodo para obtener el dia domingo
    private static LocalDate sundayForDate(@NonNull LocalDate current) {
        LocalDate oneWeekAgo = current.minusWeeks(1);
        while (current.isAfter(oneWeekAgo)){
            if(current.getDayOfWeek() == DayOfWeek.SUNDAY){
                return current;
            }
            current = current.minusDays(1);
        }
        return null;
    }

    //CALENDAR DESING----------------------------------------------------------------------------------------

    //Metoto para mostrar en el txtView el mes con el año
    public static void showMonthAndYear(int position, @NonNull TextView monthYear_txtView){
        LocalDate scrollDate = weeks.get(position)[0];
        String scrollMonthYear = month(scrollDate) + ", " +scrollDate.getYear();
        monthYear_txtView.setText(scrollMonthYear);
    }

    //Metodo para establecer el BackGround y los colorpalette del calendario
    public static void calendarColors(@NonNull CalendarViewHolder holder, @NonNull LocalDate localDate, int i){
        ViewGroup view = (ViewGroup) holder.weeksDatesTxtViews[i].getParent(); //Obtenemos la vista padre
        if (localDate.equals(actualDate)){
            view.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.teal_700)));
            holder.weeksDatesTxtViews[i].setTextColor(Color.WHITE);
            holder.weeksDaysTxtViews[i].setTextColor(Color.WHITE);
        }else{
            view.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(),R.color.transparent)));
            holder.weeksDatesTxtViews[i].setTextColor(Color.BLACK);
            holder.weeksDaysTxtViews[i].setTextColor(Color.GRAY);
        }
    }

    //Metodo para obtener el mes
    public static String month(@NonNull LocalDate localDate){
        String month="";
        switch (localDate.getMonth()){
            case JANUARY:
                month="Enero";
                break;
            case FEBRUARY:
                month="Febrero";
                break;
            case MARCH:
                month="Marzo";
                break;
            case APRIL:
                month="Abril";
                break;
            case MAY:
                month="Mayo";
                break;
            case JUNE:
                month="Junio";
                break;
            case JULY:
                month="Julio";
                break;
            case AUGUST:
                month="Agosto";
                break;
            case SEPTEMBER:
                month="Septiembre";
                break;
            case OCTOBER:
                month="Octubre";
                break;
            case NOVEMBER:
                month="Noviembre";
                break;
            case DECEMBER:
                month="Diciembre";
                break;
        }
        return month;
    }
}

