package com.example.scapp.CalendarConfig;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.example.scapp.R;
import java.time.LocalDate;

public class CalendarDesing {


    //Metodo para establecer el Background y los colorpalette del calendario
    public static void CalendarStyle(@NonNull TextView weekDateTxtView, TextView weekDayTxtView, @NonNull LocalDate date){
        ViewGroup parentView = (ViewGroup) weekDateTxtView.getParent();
        if(date.equals(CalendarUtils.getActualDate())){
            parentView.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(parentView.getContext(), R.color.teal_700)));
            weekDateTxtView.setTextColor(Color.WHITE);
            weekDayTxtView.setTextColor(Color.WHITE);
        }else{
            parentView.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(parentView.getContext(), R.color.transparent)));
            weekDateTxtView.setTextColor(Color.BLACK);
            weekDayTxtView.setTextColor(Color.GRAY);
        }
    }

    //Metoto para mostrar en el txtView el mes con el año
    public static void showMonthAndYearTxt_View(int position, @NonNull TextView monthYear_txtView){
        LocalDate scrollDate = CalendarUtils.getWeeks().get(position)[0];
        String scrollMonthYear = month(scrollDate) + ", " +scrollDate.getYear();
        monthYear_txtView.setText(scrollMonthYear);
    }

    //Editamos la forma en la que se muestra el texto
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
