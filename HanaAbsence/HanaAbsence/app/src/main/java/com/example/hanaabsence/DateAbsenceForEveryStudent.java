package com.example.hanaabsence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.hanaabsence.AffichageAbs.pos;

public class DateAbsenceForEveryStudent extends AppCompatActivity {
    DatabaseHelper db = new DatabaseHelper(this);
    //int id;
    private ListView ListViewDates,ListInfoEtudiant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_absence_for_every_student);

        //TextView TVFirstName = (TextView) findViewById(R.id.TVnom);
        //TextView TVLastName = (TextView) findViewById(R.id.TVprenom);
        ListViewDates = (ListView) findViewById(R.id.ListViewAbsDate);
        ListInfoEtudiant = (ListView) findViewById(R.id.ListViewInfoEtudiant);
        getAllDates();
        getInfoEtudiant(pos);

    }

    private void getInfoEtudiant(int id) {
        ArrayList<String> Liste = db.getInfo(id);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,Liste);
        ListInfoEtudiant.setAdapter(arrayAdapter);


    }


    private void getAllDates() {
        ArrayList<String> maListe = db.getAllDatesOfAbs(pos);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,maListe);
        ListViewDates.setAdapter(arrayAdapter);

    }

}
