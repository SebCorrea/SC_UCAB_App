package com.example.scapp.data.providers;

import androidx.annotation.NonNull;
import com.example.scapp.ui.calendarUI.CalendarAdapter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarDatesProvider {

    private static final List<LocalDate[]> weeks = new ArrayList<>();

    public static List<LocalDate[]> get_Weeks(){
        return weeks;
    }
    public static void generateInitialWeeks() {
        LocalDate[] week;
        LocalDate sundayOfThisWeek = sundayForDate(LocalDate.now()); //Del dia actual obtenemos el día domingo de esa misma semana para asi organizar las semanas
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
        }
        weeks.subList(0,3).clear();
        calendarAdapter.notifyItemRangeRemoved(0,3);
    }

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
            weeks.add(0,week);
            calendarAdapter.notifyItemInserted(0);
        }
        weeks.subList(weeks.size()-3, weeks.size()).clear();
        calendarAdapter.notifyItemRangeRemoved(weeks.size()-3,3);
    }

    @NonNull
    public static String get_MonthAndYear(int position){
        
        LocalDate firstDayOfWeek = weeks.get(position)[0];
        String month="";
        switch (firstDayOfWeek.getMonth()){
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
        return month + ", " +firstDayOfWeek.getYear();
    }

}
