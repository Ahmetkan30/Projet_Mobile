package com.example.projet_mobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class FragmentConnexion extends Fragment implements AdapterView.OnItemSelectedListener {

    private String profil = "";

    public FragmentConnexion() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_fragmentconnexion, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        Button boutonConnecter = view.findViewById(R.id.bouton_connexion);
        MainActivity activity = (MainActivity) getActivity();
        Spinner spinner = view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        boutonConnecter.setOnClickListener(v -> {

            if(this.profil.equals("Médecin")){
                Intent espaceMedecinIntent = new Intent(activity.getApplicationContext(), Medecin.class);
                startActivity(espaceMedecinIntent);
            }
            else if(this.profil.equals("Intervenant")){
                Intent espaceIntervenantIntent = new Intent(activity.getApplicationContext(), intervenant.class);
                startActivity(espaceIntervenantIntent);
            }
            else{
                Intent espacePatientIntent = new Intent(activity.getApplicationContext(), Patient.class);
                startActivity(espacePatientIntent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        this.profil = (String) parent.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}