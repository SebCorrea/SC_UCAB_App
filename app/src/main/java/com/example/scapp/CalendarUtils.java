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

    public CalendarUtils(CalendarAdapter.OnItemListener onItemListener) {
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

    public static List<LocalDate[]> daysInWeekArray(LocalDate selectedDate) {

        List<LocalDate[]> weeks = new ArrayList<>();

        LocalDate week[] = new LocalDate[7];
        LocalDate sundayOfThisWeek = sundayForDate(selectedDate);
        LocalDate initialDate = sundayOfThisWeek.minusWeeks(1);
        LocalDate endDate = sundayOfThisWeek.plusWeeks(2);

        while (initialDate.isBefore(endDate)){

            for(int i = 0; i<week.length; i++){
                week[i] = initialDate;
                initialDate = initialDate.plusDays(1);
            }
            weeks.add(week);
            week = new LocalDate[7];
        }

        return weeks;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }



}
