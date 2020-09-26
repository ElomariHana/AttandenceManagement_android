package com.example.hanaabsence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class AffichageAbs extends AppCompatActivity {


    DatabaseHelper db = new DatabaseHelper(this);
    public static int pos;
    public ListView ListViewEtudiantsAbs;
    public List<Etudiant> lstEtudiantAbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_abs);

        ListViewEtudiantsAbs = (ListView) findViewById(R.id.LAFFABS);


        lstEtudiantAbs = db.getAllEtudiants2();//a developper il doit afficher le nombre des abs et les noms (jointure entre les deux tables)

        EtudiantAdapterAbs adapter = new EtudiantAdapterAbs(this,R.layout.activity_row_item_view,lstEtudiantAbs);

        ListViewEtudiantsAbs.setAdapter(adapter);


        //Ajouté le 25 *************************************************

        ListViewEtudiantsAbs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                pos = position+1;
                //Toast.makeText(AffichageAbs.this, "you are in " + lstEtudiantAbs.get(position), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),DateAbsenceForEveryStudent.class);
                startActivity(intent);
            }
        });


    }
}
