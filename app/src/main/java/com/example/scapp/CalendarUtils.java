package com.example.scapp;

import androidx.appcompat.app.AppCompatActivity;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CalendarUtils extends AppCompatActivity {
    public static LocalDate selectedDate;
    private final CalendarAdapter.OnItemListener onItemListener;
    public static List<LocalDate[]> weeks = new ArrayList<>();
    public static LocalDate week[] = new LocalDate[7];
    public static LocalDate endDate;
    public static LocalDate initialDate;

    public CalendarUtils(CalendarAdapter.OnItemListener onItemListener){
        this.onItemListener = onItemListener;
    }

    public static ArrayList<LocalDate> daysInMonthArray(LocalDate date) {
        ArrayList<LocalDate> daysInMonthArray = new ArrayList<>();

        //El metodo from obtiene el mes y año actual a traves del TemporalAccessor especificado
        YearMonth yearMonth = YearMonth.from(date);

        //Metodo lengthOfMonth devuelve la longitud del mes
        int daysInMonth = yearMonth.lengthOfMonth();

        //Con el metodo withDayOfMonth obtenemos una copia de LocalDate con el día del mes alterado.
        //Obtenemos el primer dia del mes
        LocalDate firstOfMonth = CalendarUtils.selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i=1; i <= 42; i++){
            if(i<=dayOfWeek || i>daysInMonth + dayOfWeek){
                daysInMonthArray.add(null);
            }else{
                daysInMonthArray.add(LocalDate.of(selectedDate.getYear(),selectedDate.getMonth(),i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    public static List<LocalDate[]> daysOfThisWeeks(LocalDate selectedDate) {

        LocalDate sundayOfThisWeek = sundayForDate(selectedDate);
        initialDate = sundayOfThisWeek.minusWeeks(6);
        endDate = sundayOfThisWeek.plusWeeks(7);
        while (initialDate.isBefore(endDate)){
            for(int i=0; i<week.length; i++){
                week[i]= initialDate;
                initialDate = initialDate.plusDays(1);
            }
            weeks.add(week);
            week = new LocalDate[7];
        }
        initialDate = sundayOfThisWeek.minusDays(1).minusWeeks(6);
        return weeks;
    }

    public static void generatePlusWeeks(CalendarAdapter calendarAdapter){
        LocalDate newEndDate = endDate.plusWeeks(3);
        while (endDate.isBefore(newEndDate)){
            for(int i = 0; i<week.length; i++){
                week[i] = endDate;
                endDate = endDate.plusDays(1);
            }
            weeks.add(week);
            calendarAdapter.notifyItemInserted(weeks.size()-1);
            weeks.remove(0);
            calendarAdapter.notifyItemRemoved(0);
            week = new LocalDate[7];
        }
    }

    public static void generateMinusWeeks(CalendarAdapter calendarAdapter){
        LocalDate newInitialDate = initialDate.minusWeeks(3);
        while (initialDate.isAfter(newInitialDate)){
            for(int i=week.length-1; i>=0; i--){
                week[i] = initialDate;
                initialDate = initialDate.minusDays(1);
            }
            weeks.add(0,week);
            calendarAdapter.notifyItemInserted(0);
            weeks.remove(weeks.size()-1);
            calendarAdapter.notifyItemRemoved(weeks.size()-1);
            week = new LocalDate[7];
        }
    }

    public static void generatePlusWeeks2(CalendarAdapter calendarAdapter){
        LocalDate[] week = weeks.get(weeks.size()-1);
        LocalDate endDate = week[6].plusDays(1);

        LocalDate newEndDate = endDate.plusWeeks(3);
        while (endDate.isBefore(newEndDate)){
            week = new LocalDate[7];
            for(int i = 0; i<week.length; i++){
                week[i] = endDate;
                endDate = endDate.plusDays(1);
            }
            weeks.add(week);
            calendarAdapter.notifyItemInserted(weeks.size()-1);
            weeks.remove(0);
            calendarAdapter.notifyItemRemoved(0);
        }
    }
    public static void generateMinusWeeks2(CalendarAdapter calendarAdapter){
        LocalDate[] week = weeks.get(0);
        LocalDate initialDate = week[0].minusDays(1);
        LocalDate newInitialDate = initialDate.minusWeeks(3);

        while (initialDate.isAfter(newInitialDate)){
            week = new LocalDate[7];
            for(int i=week.length-1; i>=0; i--){
                week[i] = initialDate;
                initialDate = initialDate.minusDays(1);
            }
            weeks.add(0,week);
            calendarAdapter.notifyItemInserted(0);
            weeks.remove(weeks.size()-1);
            calendarAdapter.notifyItemRemoved(weeks.size()-1);
        }
    }

    private static LocalDate sundayForDate(LocalDate current) {

        //Devuelve la semana anterior transcurrida
        LocalDate oneWeekAgo = current.minusWeeks(1);

        while (current.isAfter(oneWeekAgo)){
            if(current.getDayOfWeek() == DayOfWeek.SUNDAY){
                return current;
            }

            current = current.minusDays(1);
        }
        return null;
    }

    public static String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return date.format(formatter);
    }



}
