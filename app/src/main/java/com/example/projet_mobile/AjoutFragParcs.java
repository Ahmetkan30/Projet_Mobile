package com.example.projet_mobile;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class AjoutFragParcs extends Fragment implements AdapterView.OnItemSelectedListener, TextWatcher {

    private LinearLayout listActivite;
    private FichPatient patient;
    private EditText Titreparcours;
    private EditText Descriptionsparcours;
    private EditText Categorie;
    private EditText TitreActiv;
    private EditText DescripActiv;
    private EditText Duree;
    private ArrayList<Activiter> arraylistActivite;

    public AjoutFragParcs() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_ajoutfragparcs, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Spinner spinner = view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<FichPatient> ad = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, FichPatient.allPatient);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ad);

        arraylistActivite = new ArrayList<>();

        Titreparcours = view.findViewById(R.id.parcours_titres);
        Titreparcours.addTextChangedListener(this);
        Descriptionsparcours = view.findViewById(R.id.parcours_descriptions);
        Descriptionsparcours.addTextChangedListener(this);
        Categorie = view.findViewById(R.id.categories);
        Categorie.addTextChangedListener(this);
        TitreActiv = view.findViewById(R.id.titre_activite);
        TitreActiv.addTextChangedListener(this);
        DescripActiv = view.findViewById(R.id.description_activite);
        DescripActiv.addTextChangedListener(this);
        Duree = view.findViewById(R.id.duree);
        Duree.addTextChangedListener(this);

        Button boutonAnnuler = view.findViewById(R.id.arreter);
        boutonAnnuler.setOnClickListener(v -> {
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().remove(AjoutFragParcs.this).commit();
            Medecin.setButtonVisible();
        });
        listActivite = view.findViewById(R.id.list_activite);
        Button boutonAjouterActivite = view.findViewById(R.id.bouton_activites_ajouter);
        boutonAjouterActivite.setOnClickListener(v -> {
            addView();
        });
        Button boutonValider = view.findViewById(R.id.valider);
        boutonValider.setOnClickListener(v -> {
            if(isValid()){
                new ParcoursAPA(
                        patient,
                        Titreparcours.getText().toString(),
                        Descriptionsparcours.getText().toString(),
                        Categorie.getText().toString(),
                        arraylistActivite
                );
                // on ferme le Fragment
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().remove(AjoutFragParcs.this).commit();
                // on réaffiche le bouton pour créer un parcours
                Medecin.setButtonVisible();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        patient = FichPatient.allPatient.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if(s == Titreparcours.getEditableText()){
            if (Titreparcours.getText().toString().isEmpty()) {
                Titreparcours.setError("Le champ est requis");
            } else {
                Titreparcours.setError(null);
            }
        }
        else if(s == Descriptionsparcours.getEditableText()){
            if (Descriptionsparcours.getText().toString().isEmpty()) {
                Descriptionsparcours.setError("Le champ est requis");
            } else {
                Descriptionsparcours.setError(null);
            }
        }
        else if(s == Categorie.getEditableText()){
            if (Categorie.getText().toString().isEmpty()) {
                Categorie.setError("Le champ est requis");
            } else {
                Categorie.setError(null);
            }
        }
        else if(s == TitreActiv.getEditableText()){
            if (TitreActiv.getText().toString().isEmpty()) {
                TitreActiv.setError("Le champ est requis");
            } else {
                TitreActiv.setError(null);
            }
        }
        else if(s == Duree.getEditableText()){
            if (Duree.getText().toString().isEmpty()) {
                Duree.setError("Le champ est requis");
            } else {
                Duree.setError(null);
            }
        }

    }

    private void addView(){
        @SuppressLint("InflateParams")
        final View activiteView = getLayoutInflater().inflate(R.layout.activity_ajoutactiver,null, false);
        ImageView imageClose = activiteView.findViewById(R.id.image_remove);
        imageClose.setOnClickListener(v -> {
            // on retire l'activité
            removeView(activiteView);
        });
        listActivite.addView(activiteView);
    }

    private void removeView(View view){
        listActivite.removeView(view);
    }

    private boolean isValid(){
        if(Titreparcours.getText().toString().isEmpty()){
            Titreparcours.setError("Le champ est requis");
            return false;
        }
        if(Descriptionsparcours.getText().toString().isEmpty()){
            Descriptionsparcours.setError("Le champ est requis");
            return false;
        }
        if(Categorie.getText().toString().isEmpty()){
            Categorie.setError("Le champ est requis");
            return false;
        }
        String titreActivite = TitreActiv.getText().toString();
        String descriptionActivite = DescripActiv.getText().toString();
        String duree = Duree.getText().toString().trim();
        if(titreActivite.isEmpty()){
            TitreActiv.setError("Le champ est requis");
            return false;
        }
        if(descriptionActivite.isEmpty()){
            DescripActiv.setError("Le champ est requis");
            return false;
        }
        if(duree.isEmpty()){
            Duree.setError("Le champ est requis");
            return false;
        }
        else{
            String pattern = "^[0-9]*$";
            if(!duree.matches(pattern)){
                Duree.setError("Seuls les nombres entiers sont autorisés");
                return false;
            }
            Activiter activite = new Activiter(titreActivite,descriptionActivite,Integer.parseInt(duree));
            arraylistActivite.add(activite);
        }
        int count = listActivite.getChildCount();
        for (int i = 5; i < count; i++) {
            View row = listActivite.getChildAt(i);
            TitreActiv = row.findViewById(R.id.titre_activite);
            DescripActiv = row.findViewById(R.id.description_activite);
            Duree = row.findViewById(R.id.duree);
            titreActivite = TitreActiv.getText().toString();
            descriptionActivite = DescripActiv.getText().toString();
            duree = Duree.getText().toString().trim();
            if(titreActivite.isEmpty()){
                TitreActiv.setError("Le champ est requis");
                return false;
            }
            if(descriptionActivite.isEmpty()){
                DescripActiv.setError("Le champ est requis");
                return false;
            }
            if(duree.isEmpty()){
                Duree.setError("Le champ est requis");
                return false;
            }
            else{
                String pattern = "^[0-9]*$";
                if(!duree.matches(pattern)){
                    Duree.setError("Seuls les nombres entiers sont autorisés");
                    return false;
                }
                Activiter activite = new Activiter(titreActivite,descriptionActivite,Integer.parseInt(duree));
                arraylistActivite.add(activite);
            }
        }
        return true;
    }

}