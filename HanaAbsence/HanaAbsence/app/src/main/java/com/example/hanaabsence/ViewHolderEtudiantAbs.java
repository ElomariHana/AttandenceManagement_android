package com.example.hanaabsence;

import android.widget.TextView;

public class ViewHolderEtudiantAbs {

    public TextView nom;
    public TextView prenom;
    public TextView nombreAbsences;

    public void setDataIntoViewHolderAbs(Etudiant etudiantAbs){
        nom.setText(etudiantAbs.getNom());
        prenom.setText(etudiantAbs.getPrenom());
        //nombreAbsences.setText(etudiantAbs.getNombresAbsences());
    }

}
