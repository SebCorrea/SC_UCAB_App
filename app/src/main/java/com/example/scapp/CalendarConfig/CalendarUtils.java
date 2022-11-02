package com.example.scapp.CalendarConfig;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CalendarUtils{

    public static LocalDate selectedDate;
    public static List<LocalDate[]> weeks = new ArrayList<>();

    public static List<LocalDate[]> daysOfThisWeeks(LocalDate selectedDate) {

        LocalDate[] week;
        LocalDate sundayOfThisWeek = sundayForDate(selectedDate);
        LocalDate initialDate = Objects.requireNonNull(sundayOfThisWeek).minusWeeks(6);
        LocalDate endDate = sundayOfThisWeek.plusWeeks(7);

        while (initialDate.isBefore(endDate)){
            week = new LocalDate[7];
            for(int i=0; i<week.length; i++){
                week[i]= initialDate;
                initialDate = initialDate.plusDays(1);
            }
            weeks.add(week);
        }
        return weeks;
    }

    public static void generatePlusWeeks(CalendarAdapter calendarAdapter){

        LocalDate[] week = weeks.get(weeks.size()-1);
        LocalDate endDate = week[6].plusDays(1);
        LocalDate newEndDate = endDate.plusWeeks(3);
        while (endDate.isBefore(newEndDate)){
            week = new LocalDate[7];
            for(int i = 0; i<week.length; i++){
                week[i] = endDate;
                endDate = endDate.plusDays(1);
            }
            //Se generan nuevas semanas
            weeks.add(week);
            calendarAdapter.notifyItemInserted(weeks.size()-1);
            //Se eliminan semanas antiguas
            weeks.remove(0);
            calendarAdapter.notifyItemRemoved(0);
        }
    }
    public static void generateMinusWeeks(CalendarAdapter calendarAdapter){

        LocalDate[] week = weeks.get(0);
        LocalDate initialDate = week[0].minusDays(1);
        LocalDate newInitialDate = initialDate.minusWeeks(3);
        while (initialDate.isAfter(newInitialDate)){
            week = new LocalDate[7];
            for(int i=week.length-1; i>=0; i--){
                week[i] = initialDate;
                initialDate = initialDate.minusDays(1);
            }
            //Se generan nuevas semanas
            weeks.add(0,week);
            calendarAdapter.notifyItemInserted(0);
            //Se eliminan semanas antiguas
            weeks.remove(weeks.size()-1);
            calendarAdapter.notifyItemRemoved(weeks.size()-1);
        }
    }

    private static LocalDate sundayForDate(LocalDate current) {

        LocalDate oneWeekAgo = current.minusWeeks(1);
        while (current.isAfter(oneWeekAgo)){
            if(current.getDayOfWeek() == DayOfWeek.SUNDAY){
                return current;
            }
            current = current.minusDays(1);
        }
        return null;
    }

    public static String month(LocalDate localDate){

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