package com.example.projet_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Medecin extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    private static Button boutonCreerParcours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecin);
        FragmentManager fragmentManager = getSupportFragmentManager();
        boutonCreerParcours = findViewById(R.id.bouton_creation_de_parcours);
        boutonCreerParcours.setOnClickListener(v -> {
            AjoutFragParcs fragment = new AjoutFragParcs();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.conteneur_fragment,fragment).commit();
            boutonCreerParcours.setVisibility(View.GONE);
        });
    }

    public static void setButtonVisible(){
        boutonCreerParcours.setVisibility(View.VISIBLE);
    }
}