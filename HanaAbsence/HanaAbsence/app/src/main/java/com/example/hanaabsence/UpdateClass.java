package com.example.hanaabsence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateClass extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);


    private Button btnUpdate;
    private EditText id;
    private EditText filiere;
    private EditText niveau;
    private EditText respo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_class);


        btnUpdate = (Button) findViewById(R.id.UpdateCl);
        id = (EditText) findViewById(R.id.idCla);
        filiere = (EditText) findViewById(R.id.Fil);
        niveau = (EditText) findViewById(R.id.Ann);
        respo = (EditText) findViewById(R.id.Resp);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

    }

public void update(){
    String fil = filiere.getText().toString();
    String niv = niveau.getText().toString();
    String res = respo.getText().toString();
    String identifiant = id.getText().toString();

    Boolean result = db.update(identifiant, fil, niv, res);

    if(result == true) {
        Toast.makeText(UpdateClass.this, "Data updated sucssfuly ... ", Toast.LENGTH_LONG).show();
    }else{
        Toast.makeText(UpdateClass.this, "ERROR data not updated ... ", Toast.LENGTH_LONG).show();
    }
}


}
