package com.example.projet_mobile;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AjoutFragParcs extends Fragment {

    LinearLayout listActivite;

    public AjoutFragParcs() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_ajoutfragparcs, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button boutonAnnuler = view.findViewById(R.id.bouton_annule);
        boutonAnnuler.setOnClickListener(v -> {
            // Fragment fermer
            getFragmentManager().beginTransaction().remove(AjoutFragParcs.this).commit();
            Medecin.setButtonVisible();
        });
        listActivite = view.findViewById(R.id.activites_liste);
        Button boutonAjouterActivite = view.findViewById(R.id.bouton_activites_ajouter);
        boutonAjouterActivite.setOnClickListener(v -> {
            addView();
        });
    }

    private void addView(){
        @SuppressLint("inflate parameters")
        final View activiteView = getLayoutInflater().inflate(R.layout.activity_ajoutactiver,null, false);
        ImageView imageClose = (ImageView)activiteView.findViewById(R.id.image_remove);
        imageClose.setOnClickListener(v -> {
            removeView(activiteView);
        });
        listActivite.addView(activiteView);
    }

    private void removeView(View view){
        listActivite.removeView(view);
    }
}