package com.example.projet_mobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FragmentConnexion extends Fragment {

    String email;
    String mdp;

    public FragmentConnexion() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fragmentconnexion, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        Button boutonConnection = view.findViewById(R.id.bouton_connexion);
        MainActivity activity = (MainActivity) getActivity();
        boutonConnection.setOnClickListener(v -> {

            EditText e_mail = view.findViewById(R.id.mail);
            EditText e_mot_de_passe = view.findViewById(R.id.mot_de_passe);
            email = e_mail.getText().toString();
            mdp = e_mot_de_passe.getText().toString();
            //Intent espacePatientIntent = new Intent(activity.getApplicationContext(), EspacePatient.class);
            //startActivity(espacePatientIntent);


            Intent espaceMedecinIntent = new Intent(activity.getApplicationContext(), Medecin.class);
            startActivity(espaceMedecinIntent);


        });
    }

}