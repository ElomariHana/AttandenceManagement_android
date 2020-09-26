package com.example.hanaabsence;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class EtudiantAdapterAbs extends ArrayAdapter<Etudiant> {

    DatabaseHelper db = new DatabaseHelper(getContext());

    List<Etudiant> lsEtudiantAbs;
    LayoutInflater inflater;

    public EtudiantAdapterAbs(@NonNull Context context, int resource, @NonNull List<Etudiant> objects) {
        super(context, resource, objects);

        lsEtudiantAbs = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View view = convertView;
        //return super.getView(position, convertView, parent);

        final ViewHolderEtudiantAbs viewholderabs;
        if(view == null){
            view = inflater.inflate(R.layout.activity_row_item_view,null);
            viewholderabs = new ViewHolderEtudiantAbs();

            viewholderabs.nom = (TextView) view.findViewById(R.id.VNOM);
            viewholderabs.prenom = (TextView) view.findViewById(R.id.VPRENOM);
            viewholderabs.nombreAbsences = (TextView) view.findViewById(R.id.NBABS);
            view.setTag(viewholderabs);



        }else {
            viewholderabs = (ViewHolderEtudiantAbs) view.getTag();

        }

        final Etudiant etudiant = lsEtudiantAbs.get(position);
        //final TextView tv = view.findViewById(R.id.NBABS);




            int nb = db.getAbsencesCount2(etudiant);
            viewholderabs.nombreAbsences.setText("Nb d'absences: "+Integer.toString(nb));
            viewholderabs.setDataIntoViewHolderAbs(etudiant);



/*
        if (tv != null) {

            int nb = db.getAbsencesCount2(etudiant);
            tv.setText(Integer.toString(nb));
            viewholderabs.setDataIntoViewHolderAbs(etudiant);// le probleme est ici

        }
*/

        viewholderabs.setDataIntoViewHolderAbs(etudiant);// le probleme est ici



        return view;
    }
}
