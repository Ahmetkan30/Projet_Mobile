package com.example.projet_mobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FragmentEnregistrer extends Fragment {

    String noms;
    String prenoms;
    String email;
    String confi_mdp;
    String mdp;

    public FragmentEnregistrer() {
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        Button boutonEnregistrer = view.findViewById(R.id.bouton_enregistrer);
        MainActivity activity = (MainActivity) getActivity();
        boutonEnregistrer.setOnClickListener(v -> {
            EditText e_noms = view.findViewById(R.id.noms);
            EditText e_prenoms = view.findViewById(R.id.prenoms);
            EditText e_email = view.findViewById(R.id.mail);
            EditText e_confi_mdp = view.findViewById(R.id.mot_de_passe);
            EditText e_mdp = view.findViewById(R.id.remot_de_passe);
            noms = e_noms.getText().toString();
            prenoms = e_prenoms.getText().toString();
            email = e_email.getText().toString();
            mdp = e_mdp.getText().toString();
            confi_mdp = e_confi_mdp.getText().toString();
            //Intent Patient = new Intent(activity.getApplicationContext(), EspacePatient.class);
            //startActivity(Patient);
            Intent Medecin = new Intent(activity.getApplicationContext(), Medecin.class);
            startActivity(Medecin);

        });
    }

}