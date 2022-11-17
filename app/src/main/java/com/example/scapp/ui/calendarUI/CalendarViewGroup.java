package com.example.scapp.ui.calendarUI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.scapp.R;
import com.example.scapp.databinding.CalendarViewgroupBinding;

public class CalendarViewGroup extends LinearLayout {

    private CalendarViewgroupBinding binding;


    public CalendarViewGroup(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.calendar_viewgroup, this, true);
        binding = CalendarViewgroupBinding.bind(view);

        RecyclerView m = binding.gordo;
        TextView p = binding.pene;
    }

}
