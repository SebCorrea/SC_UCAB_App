package com.example.scapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

    public static void setWeekView2(){
        ArrayList<LocalDate> daysInWeek = daysInWeekArray(selectedDate);
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInWeek);
        //Pasamos el adaptador al RecyclerView
        MainActivity.calendarRecyclerView.setAdapter(calendarAdapter);

        //Creamos e instanciamos el layoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.calendarRecyclerView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        //Pasamos el layoutManager al recyclerView
        //calendarRecyclerView.setLayoutManager(new SpeedyLinearLayoutManager(this, SpeedyLinearLayoutManager.HORIZONTAL, false));
        MainActivity.calendarRecyclerView.setLayoutManager(layoutManager);


        MainActivity.calendarRecyclerView.addOnScrollListener(new CalendarScroll(MainActivity.calendarRecyclerView.getContext()));
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();

        //MainActivity.prueba.setText(String.valueOf(selectedDate.getDayOfMonth()));
        try {
            pagerSnapHelper.attachToRecyclerView(MainActivity.calendarRecyclerView);
        }catch (Exception e){

        }

    }
    public static ArrayList<LocalDate> daysInWeekArray(LocalDate selectedDate) {

        ArrayList<LocalDate> days = new ArrayList<>();
        LocalDate current = sundayForDate(selectedDate);

        LocalDate initialDate = current.minusWeeks(1);
        LocalDate finalDate = current.plusWeeks(2);

        days.clear();

        while (initialDate.isBefore(finalDate)){
            days.add(initialDate);
            initialDate = initialDate.plusDays(1);
        }

        return days;
    }


    public static ArrayList<LocalDate[]> WeekArrayPRUEBA(LocalDate selectedDate) {

        ArrayList<LocalDate[]> weeks = new ArrayList<>();

        LocalDate week[] = new LocalDate[7];
        LocalDate sundayOfThisWeek = sundayForDate(selectedDate);
        LocalDate initialDate = sundayOfThisWeek.minusWeeks(1);
        LocalDate endDate = sundayOfThisWeek.plusWeeks(2);

        while (initialDate.isBefore(endDate)){
            for(int i = 0; i<week.length; i++){
                week[i] = sundayOfThisWeek;
                sundayOfThisWeek = sundayOfThisWeek.plusDays(1);
            }
            weeks.add(week);
            initialDate = initialDate.plusWeeks(1);
        }

        MainActivity.prueba.setText(String.valueOf(weeks.size()));
        return weeks;



        /*

        while (sunday.isBefore(selectedDate)){

        }
        LocalDate current = sundayForDate(selectedDate);

        LocalDate initialDate = current.minusWeeks(1);
        LocalDate finalDate = current.plusWeeks(2);

        days.clear();

        while (initialDate.isBefore(finalDate)){
            days.add(initialDate);
            initialDate = initialDate.plusDays(1);
        }

         */
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


    public static String Days(LocalDate date){
        String day = "";
        switch (date.getDayOfWeek()){
            case MONDAY:
                day = "Mon";
                break;
            case TUESDAY:
                day = "Tue";
                break;
            case WEDNESDAY:
                day = "Wed";
                break;
            case THURSDAY:
                day = "Thur";
                break;
            case FRIDAY:
                day = "Fri";
                break;
            case SATURDAY:
                day = "Sat";
                break;
            case SUNDAY:
                day = "Sun";
                break;
        }
        return day;
    }

}
