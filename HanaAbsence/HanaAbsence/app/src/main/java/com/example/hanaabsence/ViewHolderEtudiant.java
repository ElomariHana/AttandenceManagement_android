package com.example.hanaabsence;

import android.widget.CheckBox;
import android.widget.TextView;

public class ViewHolderEtudiant{

    public TextView nom;
    public TextView prenom;
    public CheckBox checked;

    public void setDataIntoViewHolder(Etudiant etudiant){
        nom.setText(etudiant.getNom());
        prenom.setText(etudiant.getPrenom());

    }

}
