package com.example.projet_mobile;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.List;

public class intervenant extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private LinearLayout linearLayout;
    @SuppressLint("StaticFieldLeak")
    protected static LinearLayout listActivite;
    protected static FichPatient patient;
    protected static Activiter selectedActivite;
    protected static ArrayList<View> addedActiviteViews;
    @SuppressLint("StaticFieldLeak")
    protected static WeekView mWeekView;
    private int count;
    protected static int index;
    private EditText nomStructure;
    private EditText disciplineStructure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intervenant);
        mWeekView = findViewById(R.id.weekView);
        mWeekView.setMonthChangeListener(mMonthChangeListener);

        linearLayout = findViewById(R.id.linear_layout);
        listActivite = findViewById(R.id.list_activite);
        addedActiviteViews = new ArrayList<>();
        count = 0;

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<FichPatient> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, FichPatient.allPatient);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ad);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        patient = FichPatient.allPatient.get(position);
        ArrayList<Activiter> activiteEnAttente = patient.getActiviteEnAttente();
        listActivite.removeAllViews();
        for(int i=0; i<activiteEnAttente.size(); i++){
            addView(activiteEnAttente.get(i));
        }
        mWeekView.notifyDatasetChanged();
        linearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        linearLayout.setVisibility(View.GONE);
    }
    MonthLoader.MonthChangeListener mMonthChangeListener = this::getEvents;

    private List<WeekViewEvent> getEvents(int newYear, int newMonth){
        ArrayList<Seance> agenda = patient.getAgenda();
        List<WeekViewEvent> events = new ArrayList<>();
        for(int i=0; i<agenda.size(); i++){
            WeekViewEvent event = new WeekViewEvent(
                    i,
                    agenda.get(i).getActivite().getTitre(),
                    agenda.get(i).getDebut(),
                    agenda.get(i).getFin()
            );
            event.setColor(getResources().getColor(R.color.event_color_01));
            events.add(event);
        }
        return events;
    }

    @SuppressLint({"InflateParams", "StringFormatInvalid"})
    private void addView(Activiter activite){
        String titre = activite.getTitre();
        String description = activite.getDescription();
        String duree = Integer.toString(activite.getDuree());
        View activiteView = getLayoutInflater().inflate(R.layout.activity_structure, null, false);
        EditText titreActivite = activiteView.findViewById(R.id.titre_activite);
        titreActivite.setText(titre);
        titreActivite.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if (titreActivite.getText().toString().isEmpty()) {

                    titreActivite.setText(titre);
                }
                else{
                    activite.setTitre(s.toString());
                }
            }
        });
        EditText descriptionActivite = activiteView.findViewById(R.id.description_activite);
        descriptionActivite.setText(description);
        descriptionActivite.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if (descriptionActivite.getText().toString().isEmpty()) {
                    descriptionActivite.setText(description);
                }
                else{
                    activite.setDescription(s.toString());
                }
            }
        });
        TextView dureeActivite = activiteView.findViewById(R.id.duree);
        dureeActivite.setText(getString(R.string.durees,duree));

        LinearLayout assignerStructure = activiteView.findViewById(R.id.assigner_structure);
        RadioGroup radioGroup = activiteView.findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == R.id.radio_button1){
                assignerStructure.setVisibility(View.VISIBLE);
                Structure structure = new Structure();
                nomStructure = activiteView.findViewById(R.id.nom_struct);
                if(!nomStructure.getText().toString().isEmpty()){
                    structure.setNom(nomStructure.getText().toString());
                }
                else{
                    structure.setNom("");
                }
                activite.setStructure(structure);
                nomStructure.addTextChangedListener(new TextWatcher() {
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }
                    public void afterTextChanged(Editable s) {
                        if (nomStructure.getText().toString().isEmpty()) {
                            nomStructure.setError("Le champ est requis");
                        }
                        else{
                            nomStructure.setError(null);
                            structure.setNom(nomStructure.getText().toString());
                            activite.setStructure(structure);
                        }
                    }
                });
                disciplineStructure = activiteView.findViewById(R.id.discipline_struct);
                if(!disciplineStructure.getText().toString().isEmpty()){
                    structure.setDiscipline(disciplineStructure.getText().toString());
                }
                else{
                    structure.setDiscipline("");
                }
                activite.setStructure(structure);
                disciplineStructure.addTextChangedListener(new TextWatcher() {
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }
                    public void afterTextChanged(Editable s) {
                        if (disciplineStructure.getText().toString().isEmpty()) {
                            disciplineStructure.setError("Le champ est requis");
                        }
                        else{
                            disciplineStructure.setError(null);
                            structure.setDiscipline(disciplineStructure.getText().toString());
                            activite.setStructure(structure);
                        }
                    }
                });
            }
            else{
                assignerStructure.setVisibility(View.GONE);
                activite.setStructure(null);
            }
        });

        Button programmerButton = activiteView.findViewById(R.id.heure);
        programmerButton.setId(R.id.heure+count);
        count++;
        addedActiviteViews.add(activiteView);
        programmerButton.setOnClickListener(v -> {
            for(int i=0; i<=count; i++){
                if(v.getId() == R.id.heure){
                    if(activite.getStructure() != null){
                        if(activite.getStructure().getNom().isEmpty()){
                            new AlertDialog.Builder(this)
                                    .setTitle("Erreur")
                                    .setMessage("Veuillez renseigner le nom de la structure.")
                                    .setPositiveButton(android.R.string.yes, null)
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                            break;
                        }
                        else if(activite.getStructure().getDiscipline().isEmpty()){
                            new AlertDialog.Builder(this)
                                    .setTitle("Erreur")
                                    .setMessage("Veuillez renseigner la discipline de la structure.")
                                    .setPositiveButton(android.R.string.yes, null)
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                            break;
                        }
                        else{
                            selectedActivite = activite;
                            index = 0;
                            Intent assignerActiviteIntent = new Intent(this, associerActivite.class);
                            startActivity(assignerActiviteIntent);
                            break;
                        }
                    }
                    else if(activite.getStructure() == null){
                        selectedActivite = activite;
                        index = 0;
                        Intent assignerActiviteIntent = new Intent(this, associerActivite.class);
                        startActivity(assignerActiviteIntent);
                        break;
                    }
                }
                if(v.getId() == R.id.heure+i){
                    if(activite.getStructure() != null){
                        if(activite.getStructure().getNom().isEmpty()){
                            new AlertDialog.Builder(this)
                                    .setTitle("Erreur")
                                    .setMessage("Veuillez renseigner le nom de la structure.")
                                    .setPositiveButton(android.R.string.yes, null)
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                            break;
                        }
                        else if(activite.getStructure().getDiscipline().isEmpty()){
                            new AlertDialog.Builder(this)
                                    .setTitle("Erreur")
                                    .setMessage("Veuillez renseigner la discipline de la structure.")
                                    .setPositiveButton(android.R.string.yes, null)
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                            break;
                        }
                        else{
                            selectedActivite = activite;
                            index = i;
                            Intent assignerActiviteIntent = new Intent(this, associerActivite.class);
                            startActivity(assignerActiviteIntent);
                            break;
                        }
                    }
                    else if(activite.getStructure() == null){
                        selectedActivite = activite;
                        index = i;
                        Intent assignerActiviteIntent = new Intent(this, associerActivite.class);
                        startActivity(assignerActiviteIntent);
                        break;
                    }
                }
            }
        });
        listActivite.addView(activiteView);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

}