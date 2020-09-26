package com.example.hanaabsence;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import android.support.design.widget.NavigationView;
public class Home extends AppCompatActivity {

    private static final String TAG = "Home";
    DatabaseHelper db = new DatabaseHelper(this);


    private Button btnAdd, btnUp, btnDel;
    ListView ListViewClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        btnAdd = (Button) findViewById(R.id.NewCl);
        btnUp  = (Button) findViewById(R.id.updCl);
        btnDel = (Button) findViewById(R.id.DelCl);

        ListViewClasses = (ListView) findViewById(R.id.ListViewCl);

        getClasses();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewClass.class);
                startActivity(intent);
            }
        });

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdateClass.class);
                startActivity(intent);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DeleteClass.class);
                startActivity(intent);
            }
        });




    }

    public void getClasses(){
        final ArrayList<String> myClasses = db.getAllClasses();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,myClasses);

        ListViewClasses.setAdapter(arrayAdapter);


        ListViewClasses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: name: " + myClasses.get(position));
                //Toast.makeText(Home.this, "you are in " + myClasses.get(position), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),Menu.class);
                startActivity(intent);
            }
        });





    }
}
