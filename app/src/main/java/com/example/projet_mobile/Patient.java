package com.example.projet_mobile;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Patient extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener  {

    private int jour, mois, années;
    private int Pjours, Pmois, Pannées;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        WeekView mWeekView = findViewById(R.id.weekView);
        mWeekView.setMonthChangeListener(mMonthChangeListener);
        mWeekView.setOnEventClickListener(onEventClick);
    }
    WeekView.EventClickListener onEventClick = (event, eventRect) -> {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(event.getStartTime().getTime());
        String Debheurs = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String Debmin;
        if(calendar.get(Calendar.MINUTE) == 0){
            Debmin = "00";
        }
        else{
            Debmin = String.valueOf(calendar.get(Calendar.MINUTE));
        }
        calendar.setTime(event.getEndTime().getTime());
        String FinRDVheure = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String FinRDVmin;
        if(calendar.get(Calendar.MINUTE) == 0){
            FinRDVmin = "00";
        }
        else{
            FinRDVmin = String.valueOf(calendar.get(Calendar.MINUTE));
        }
        Toast.makeText(this, "Clicked " + event.getName()
                        + "\nHeure début " + Debheurs + "h" + Debmin
                        + "\nHeure fin " + FinRDVheure + "h" + FinRDVmin
                , Toast.LENGTH_LONG).show();
        montreledialog(R.layout.activity_affichageactiviter, event.getColor());
    };

    private void montreledialog(int layout, int color){
        AlertDialog.Builder Constructiondialog = new AlertDialog.Builder(Patient.this);
        View layoutView = getLayoutInflater().inflate(layout, null);
        LinearLayout options = layoutView.findViewById(R.id.linear_layout);
        options.setBackgroundColor(color);
        Button dialogButton = layoutView.findViewById(R.id.bouton_daccord);
        Constructiondialog.setView(layoutView);
        AlertDialog alertDialog = Constructiondialog.create();
        alertDialog.show();
        dialogButton.setOnClickListener(view -> alertDialog.dismiss());
        Button reprogButton = layoutView.findViewById(R.id.bouton_reorganisr);
        reprogButton.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            années = calendar.get(Calendar.YEAR);
            mois = calendar.get(Calendar.MONTH);
            jour = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(Patient.this, Patient.this, années, mois, jour);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();
        });
    }

    @Override
    public void onDateSet(DatePicker view, int années, int mois, int jouretmois) {
        Pannées = années;
        Pjours = jour;
        Pmois = mois;
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(Patient.this,
                Patient.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }
    @Override
    public void onTimeSet(TimePicker view, int heurs, int min) {
        Toast.makeText(this, "L'annes: " + Pannées + "\n" +
                "Mois: " + Pmois + "\n" +
                "Jour: " + Pjours + "\n" +
                "heurs du RDV: " + heurs + "\n" +
                "Minute: " + min, Toast.LENGTH_LONG).show();
    }


    MonthLoader.MonthChangeListener mMonthChangeListener = this::getEvents;

    private List<WeekViewEvent> getEvents(int newYear, int newMonth){
        List<WeekViewEvent> events = new ArrayList<>();

        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 8);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 1);
        endTime.set(Calendar.MONTH, newMonth-1);
        WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.design_default_color_primary));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 8);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 4);
        endTime.set(Calendar.MINUTE, 30);
        endTime.set(Calendar.MONTH, newMonth-1);
        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.design_default_color_primary));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 20);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 5);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.design_default_color_primary));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 16);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 2);
        endTime.set(Calendar.MONTH, newMonth-1);
        event = new WeekViewEvent(2, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.design_default_color_primary));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 14);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        startTime.add(Calendar.DATE, 1);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent(3, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.design_default_color_primary));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 10);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(4, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.design_default_color_primary));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 1);
        startTime.set(Calendar.HOUR_OF_DAY, 8);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.design_default_color_primary));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, startTime.getActualMaximum(Calendar.DAY_OF_MONTH));
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.design_default_color_primary));
        events.add(event);

        return events;
    }

    @SuppressLint("DefaultLocale")
    private String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }
}