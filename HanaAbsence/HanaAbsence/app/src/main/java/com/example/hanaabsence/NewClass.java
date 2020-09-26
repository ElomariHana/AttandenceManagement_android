package com.example.hanaabsence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewClass extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);


    private Button btnSave;
    private EditText filiere;
    private EditText niveau;
    private EditText respo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_class);


        btnSave = (Button) findViewById(R.id.SaveCl);
        filiere = (EditText) findViewById(R.id.Fil);
        niveau = (EditText) findViewById(R.id.Ann);
        respo = (EditText) findViewById(R.id.Resp);







        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);

            }
        });
    }


    public void add(){

        String fil = filiere.getText().toString();
        String niv = niveau.getText().toString();
        String res = respo.getText().toString();
        Boolean result = db.insert(fil, niv, res);
        if(result == true) {
            Toast.makeText(NewClass.this, "Data saved sucssfuly ... ", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(NewClass.this, "ERROR data not saved ... ", Toast.LENGTH_LONG).show();
        }
    }







}
