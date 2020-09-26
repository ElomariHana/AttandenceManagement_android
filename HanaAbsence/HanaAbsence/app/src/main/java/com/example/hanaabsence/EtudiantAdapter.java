package com.example.hanaabsence;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class EtudiantAdapter extends BaseAdapter {

    private static final String TAG = "EtudiantAdapter";

    private DatabaseHelper db;
    private List<Etudiant> etudiants;
    private LayoutInflater inflater;

    public EtudiantAdapter(@NonNull Context context, @NonNull List<Etudiant> objects) {
        etudiants = objects;
        inflater = LayoutInflater.from(context);
        db = new DatabaseHelper(context);

    }

    @Override
    public int getCount() {
        return (etudiants == null) ? 0 : etudiants.size();
    }

    @Override
    public Object getItem(int position) {
        return etudiants.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        // problem com from  convertView

        /* 1 solution
        View view = null;
        view = inflater.inflate(R.layout.activity_list_item_view, null);
        */

        // 2 solution
        View view = convertView;
        if (view == null)
            view = inflater.inflate(R.layout.activity_list_item_view, null);
        //-----------

        TextView nom = (TextView) view.findViewById(R.id.VN);
        TextView prenom = (TextView) view.findViewById(R.id.VP);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.CHECK);

        final Etudiant etudiant = etudiants.get(position);
        nom.setText(etudiant.getNom());
        prenom.setText(etudiant.getPrenom());
        checkBox.setChecked(etudiant.isChecked());


     /*
        use this with first solution

     checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    boolean isAbsent = db.markeAbsent(etudiant);
                }
                etudiant.setChecked(isChecked);
                Log.d(TAG, "onCheckedChanged: " + isChecked);
            }
        });*/

        // use this with 2 solution

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox) v).isChecked();
                if (isChecked) {
                    boolean isAbsent = db.markeAbsent(etudiant);
                }
                etudiant.setChecked(isChecked);
                Log.d(TAG, "onCheckedChanged: " + isChecked);
            }
        });


        return view;
    }


}
