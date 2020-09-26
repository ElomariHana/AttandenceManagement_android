package com.example.hanaabsence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    private Button charger;
    private Button afficher;
    private Button appeler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        charger = (Button) findViewById(R.id.Cha);
        afficher = (Button) findViewById(R.id.Aff);
        appeler = (Button) findViewById(R.id.App);


        charger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addAllEtudiants(uploadFile());
                //////////// a developper
            }
        });


        appeler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Call.class);
                startActivity(intent);
            }
        });

        afficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AffichageAbs.class);
                startActivity(intent);
            }
        });


    }// fermeture de onCreate

    private List<Etudiant> uploadFile() {
        List<Etudiant> etudiants = new ArrayList<Etudiant>();

        try
        {
            //lu à partir de data/data etc.
            //  InputStream instream = this.openFileInput("liste.txt");
           InputStream instream = getAssets().open("liste.txt");
            InputStreamReader inputreader = new InputStreamReader(instream);
            BufferedReader buffreader = new BufferedReader(inputreader);
            StringBuilder out = new StringBuilder();
            String line;
            String tmp;
            String[] champs;
            while (( line = buffreader.readLine()) != null)
            {
                out = new StringBuilder();
                out.append(line);
                tmp = out.toString();
                champs = tmp.split(" ");
                int id = Integer.parseInt(champs[0]);
                etudiants.add(new Etudiant(id,champs[1],champs[2],champs[3]));


            }
            Toast.makeText(Menu.this,"la liste est bien chargée" ,
                    Toast.LENGTH_LONG).show();
            instream.close();
        }
        catch (Exception e)
        {
            Toast.makeText(Menu.this,e.getMessage() ,
                    Toast.LENGTH_LONG).show();
        }

        return etudiants;
    }

}
