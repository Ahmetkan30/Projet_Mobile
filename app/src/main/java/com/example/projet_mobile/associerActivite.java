package com.example.projet_mobile;


import androidx.appcompat.app.AppCompatActivity;

        import android.app.AlertDialog;
        import android.app.TimePickerDialog;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import com.squareup.timessquare.CalendarPickerView;

        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.Date;

public class associerActivite extends AppCompatActivity {

    private int mYear, mMonth, mDay, mHour, mMinute;
    private int heure, minutes;
    private ArrayList<Date> listeDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associer_activite);

        heure = -1;
        minutes = -1;
        listeDates = new ArrayList<>();

        final CalendarPickerView calendarView = findViewById(R.id.calendar_view);
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        Date today = new Date();
        calendarView.init(today, nextYear.getTime()).inMode(CalendarPickerView.SelectionMode.MULTIPLE);

        calendarView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateUnselected(Date date) {
                listeDates.remove(date);
            }
            @Override
            public void onDateSelected(Date date) {
                listeDates.add(date);
            }
        });


        Button btnHeure = findViewById(R.id.heure);
        btnHeure.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (view, hourOfDay, minute) -> {
                        heure = hourOfDay;
                        minutes = minute;
                    }, mHour, mMinute, true);
            timePickerDialog.show();
        });

        Button btnValider = findViewById(R.id.valider);
        btnValider.setOnClickListener(this::onClick);

        Button btnAnnuler = findViewById(R.id.arreter);
        btnAnnuler.setOnClickListener(v -> {
            finish();
        });
    }


    private void onClick(View v) {
        if (listeDates.size() == 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Erreur")
                    .setMessage("Veuillez sélectionner au moins une date.")
                    .setPositiveButton(android.R.string.yes, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else if (heure == -1 || minutes == -1) {
            new AlertDialog.Builder(this)
                    .setTitle("Erreur")
                    .setMessage("Veuillez choisir l'heure avant de valider.")
                    .setPositiveButton(android.R.string.yes, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            for (int i = 0; i < listeDates.size(); i++) {
                Calendar startTime = Calendar.getInstance();
                startTime.setTime(listeDates.get(i));
                startTime.set(Calendar.HOUR_OF_DAY, heure);
                startTime.set(Calendar.MINUTE, minutes);
                Seance seance = new Seance(intervenant.selectedActivite, startTime);
                intervenant.patient.addSeance(seance);
            }
            intervenant.mWeekView.notifyDatasetChanged();
            intervenant.patient.removeActivite(intervenant.selectedActivite);
            intervenant.listActivite.removeView(intervenant.addedActiviteViews.get(intervenant.index));
            finish();
        }
    }
}