package com.example.hanaabsence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteClass extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);


    private Button btnDelete;
    private EditText id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_class);

        btnDelete = (Button) findViewById(R.id.DeleteCl);
        id = (EditText) findViewById(R.id.idClass);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

    }

    public void delete(){
        String ID = id.getText().toString();
        int result = db.delete(ID);
        if(result == 1) {
            Toast.makeText(DeleteClass.this, "Data deleted sucssfuly ... ", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(DeleteClass.this, "ERROR data not deleted ... ", Toast.LENGTH_LONG).show();
        }
    }
}
