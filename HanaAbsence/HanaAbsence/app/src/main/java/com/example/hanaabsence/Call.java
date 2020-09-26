package com.example.hanaabsence;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Call extends AppCompatActivity {

    public List<Etudiant> lstEtudiant;

    public static ArrayList<String> checkedItems = new ArrayList<String>();
    DatabaseHelper db = new DatabaseHelper(this);
    private static final String TAG = "Call";
    private ListView ListViewEtudiants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);


        TextView tv = (TextView) findViewById(R.id.TDate);

        String dt;
        Date cal = (Date) Calendar.getInstance().getTime();
        dt = cal.toLocaleString();
        tv.setText(dt.toString());







        ListViewEtudiants = (ListView) findViewById(R.id.LEtud);

        getEtudiants();


////////////////////////en cas d'erreur à supprimer  et la methode oncheckbox //////////////
/*
        ListViewEtudiants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                onCheckboxClicked(view);




            }
        });
*/



    }

    public void getEtudiants() {
/*
        final ArrayList<String> myStudents = db.getAllEtudiants();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,myStudents);

        ListViewEtudiants.setAdapter(arrayAdapter);


        ListViewEtudiants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: name: " + myStudents.get(position));
                Toast.makeText(Call.this, "you are in " + myStudents.get(position), Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(getApplicationContext(),.class);
                //startActivity(intent);
            }
        });
*/

        lstEtudiant = db.getAllEtudiants2();

        EtudiantAdapter adapter = new EtudiantAdapter(this,lstEtudiant);

        ListViewEtudiants.setAdapter(adapter);




    }





    public void onCheckboxClicked(View view){

        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.CHECK:

                    if (checked) {

                       // db.markeAbsent(db.findStudentById(position));
                        Toast.makeText(Call.this, "marked absent", Toast.LENGTH_SHORT).show();
                    } else {
                    }
                break;
        }
    }

    public void addItems(View view) {
        if (view.getTag() != null) {
            if (checkedItems.contains(view.getTag().toString())) {
                checkedItems.remove(view.getTag().toString());
                if (checkedItems.size() == 0) {
                   // removeSaveTab();
                }
            }else{
                /*if (isSearch.equals(true)) {
                    showFinishDialog(getString(R.string.saveWaitTitle), getString(R.string.saveWaitMsg), RecoveryActivity.this);
                    progressContainer.setVisibility(View.VISIBLE);
                    return;
                }*/
                checkedItems.add(view.getTag().toString());
                // addSaveTab();
            }
        }
    }


}
