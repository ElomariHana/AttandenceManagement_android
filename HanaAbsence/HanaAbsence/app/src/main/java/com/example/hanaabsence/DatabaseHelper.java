package com.example.hanaabsence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= " base-absences";

    // champs de la table etudiant

    private static final String TABLE_ETUDIANTS = "ETUDIANTS";
    private static final String KEY_ID = "id";
    private static final String KEY_CNE = "cne";
    private static final String KEY_LAST_NAME = "nom";
    private static final String KEY_FIRST_NAME = "prenom";

    // champs de la table date_absence

    private static final String TABLE_DATE_ABSENCE = "DATE_D_ABSENCE";
    private static final String KEY_ID_ABS = "idAbs";
    private static final String KEY_ID_ETUDIANT = "idEtudiant";
    private static final String KEY_DATE = "date";


    private String CREATE_TABLE_ETUDIANT = "CREATE TABLE IF NOT EXISTS " + TABLE_ETUDIANTS
            + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_CNE + " TEXT, "
            + KEY_LAST_NAME + " TEXT, "
            + KEY_FIRST_NAME + " TEXT "
            + ")";

    private String CREATE_TABLE_ABSENCE = "CREATE TABLE IF NOT EXISTS " + TABLE_DATE_ABSENCE
            +"("
            +KEY_ID_ABS + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +KEY_ID_ETUDIANT + " INTEGER,"
            +KEY_DATE + " DATETIME DEFAULT CURRENT_DATE,"
            +" FOREIGN KEY (" + KEY_ID_ETUDIANT + ") REFERENCES "
            + TABLE_ETUDIANTS +" (" + KEY_ID + ")"
            + ")";


    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ETUDIANT);
        db.execSQL(CREATE_TABLE_ABSENCE);
        db.execSQL(" create table class ( id INTEGER PRIMARY KEY AUTOINCREMENT, filiere TEXT, niveau TEXT, responsable TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_ETUDIANTS  );
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_ETUDIANTS );
        db.execSQL("DROP TABLE IF EXISTS class");

        onCreate(db);
        }


    public  Boolean insert(String filiere, String niveau,String responsable ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("filiere", filiere);
        contentValues.put("niveau", niveau);
        contentValues.put("responsable", responsable);
        long result = db.insert("class", null,contentValues);

        if (result == -1) {
            return false;
        }else {
            return true;
        }

    }

    public ArrayList getAllClasses(){
        ArrayList classes = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from class",null);
        res.moveToFirst();
        while (res.isAfterLast() == false){

            String id = res.getString(0);
            String filiere = res.getString(1);
            String niveau = res.getString(2);
            String responsable = res .getString(3);

            classes.add(id + ". Fil: " + '"' + filiere + " " + niveau + '"' + " --- Respo: " + '"' + responsable + '"' );
            res.moveToNext();
        }
        return classes;
    }


    public ArrayList getAllEtudiants(){
        ArrayList etudiants = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_ETUDIANTS,null);
        res.moveToFirst();
        while (res.isAfterLast() == false){

            int id = res.getInt(0);
            String cne = res.getString(1);
            String nom = res.getString(2);
            String prenom = res .getString(3);

            etudiants.add(id + ". CNE: " + cne + " " + nom + " " + prenom);
            res.moveToNext();
        }
        return etudiants;
    }

    public List<Etudiant> getAllEtudiants2(){
        List<Etudiant> mesEtudiants = new ArrayList<Etudiant>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_ETUDIANTS,null);
        res.moveToFirst();
        while (res.isAfterLast() == false){

            int id = res.getInt(0);
            String cne = res.getString(1);
            String nom = res.getString(2);
            String prenom = res .getString(3);

            mesEtudiants.add(new Etudiant(id, cne, nom, prenom));
            res.moveToNext();
        }
        res.close();
        return mesEtudiants;
    }

    public void addAllEtudiants(List<Etudiant> etudiants) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        for(Etudiant etu : etudiants){

            int id = etu.getId();
            String cne = etu.getCNE();
            String nom = etu.getNom();
            String prenom = etu.getPrenom();

            contentValues.put(KEY_ID, id);
            contentValues.put(KEY_CNE, cne);
            contentValues.put(KEY_LAST_NAME, nom);
            contentValues.put(KEY_FIRST_NAME, prenom);

            db.insert(TABLE_ETUDIANTS, null,contentValues);
        }
    }


    //count absences for a student
    public int getAbsencesCount(Etudiant etudiant){
        String countQuery = "SELECT * FROM " + TABLE_DATE_ABSENCE + " WHERE "
                + KEY_ID_ETUDIANT +" = "
                + String.valueOf(etudiant.getId());
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
// return count
        return cursor.getCount();
    }


    public int getAbsencesCount(){
        int count = 0;
        String countQuery = "SELECT * FROM " + TABLE_DATE_ABSENCE ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        count = cursor.getCount();
        cursor.close();
        db.close();
// return count
        return count;
    }

    public int getAbsencesCount2(Etudiant etudiant){
        int count = 0;
        try {
            String countQuery = "SELECT * FROM " + TABLE_DATE_ABSENCE + " WHERE "
                    + KEY_ID_ETUDIANT +" = "
                    + String.valueOf(etudiant.getId());            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            count = cursor.getCount();
            cursor.close();
            db.close();
            // return count
        } catch(SQLException exe) {
            //log exceptions
            Log.i("iiiii:",exe.getMessage());

        }

        return count;

    }

    public int getAbsencesCount3(int id){
        int count = 0;
        try {
            String countQuery = "SELECT * FROM " + TABLE_DATE_ABSENCE + " WHERE "
                    + KEY_ID_ETUDIANT +" = "
                    + String.valueOf(id);
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            count = cursor.getCount();

            // return count
        } catch(SQLException exe) {
            //log exceptions
            Log.i("iiiii:",exe.getMessage());

        }

        return count;

    }


    public List<Date> getAbsenceDates(Etudiant etudiant){
        List<Date> abs = new ArrayList<Date>();
        String countQuery = "SELECT * FROM " + TABLE_DATE_ABSENCE + " WHERE "
                + KEY_ID_ETUDIANT +" = "+ String.valueOf(etudiant.getId());
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                try {
                    abs.add(this.dateFormat.parse(cursor.getString(2)));
                } catch (ParseException e) {
                    Log.getStackTraceString(e) ;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return abs ;
    }

    public boolean markeAbsent(Etudiant e){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Date cal = (Date) Calendar.getInstance().getTime();
        contentValues.put(KEY_ID_ETUDIANT, e.getId());

        long result = db.insert(TABLE_DATE_ABSENCE, null,contentValues);

        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public Etudiant findStudentById(int id) {
        String countQuery = "SELECT * FROM " + TABLE_ETUDIANTS + " WHERE "
                + KEY_ID +" = "
                + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        //int id = cursor.getInt(0);
        String cne = cursor.getString(1);
        String nom = cursor.getString(2);
        String prenom = cursor.getString(3);

        Etudiant etudiant = new Etudiant(id, cne, nom, prenom);
        cursor.close();
        return etudiant;


    }


    public int delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("class", "id = ?",new String[] {id});
    }

    public Boolean update(String identifiant, String fil, String niv, String res) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", identifiant);
        contentValues.put("filiere", fil);
        contentValues.put("niveau", niv);
        contentValues.put("responsable", res);
        db.update("class", contentValues, "id = ?", new String[] {String.valueOf(identifiant)});


        return true;
    }


    public ArrayList getAllDatesOfAbs(int idEtu) {
        ArrayList myArray = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor curseur = db.rawQuery("SELECT * FROM " + TABLE_DATE_ABSENCE , null);
        Cursor curseur = db.rawQuery("SELECT * FROM " + TABLE_DATE_ABSENCE + " where idEtudiant = '" + idEtu + "'" , null);


        curseur.moveToFirst();

        while (curseur.isAfterLast() == false) {
            String date = curseur.getString(curseur.getColumnIndex("date"));
            String id = curseur.getString(curseur.getColumnIndex("idEtudiant"));


            //myArray.add("Student number: " + id + ", is absent on: " + date);
            myArray.add("This student is absent on: " + date);
            curseur.moveToNext();
        }


        return myArray;

    }


    public ArrayList getInfo(int id) {



        ArrayList myArray = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor curseur = db.rawQuery("SELECT * FROM " + TABLE_DATE_ABSENCE , null);
        //Cursor curseur = db.rawQuery("SELECT * FROM " + TABLE_DATE_ABSENCE + " where idEtudiant = '" + idEtu + "'" , null);
        Cursor curseur = db.rawQuery("SELECT * FROM " + TABLE_ETUDIANTS + " where id = '" + id + "'" , null);


        curseur.moveToFirst();

        while (curseur.isAfterLast() == false) {
            String ident = curseur.getString(curseur.getColumnIndex("id"));
            String nom = curseur.getString(curseur.getColumnIndex("nom"));
            String prenom = curseur.getString(curseur.getColumnIndex("prenom"));
            String cne = curseur.getString(curseur.getColumnIndex("cne"));

            myArray.add("ID:                    " + '"' + ident +  '"' +"\nCNE:           " + '"' +cne + '"' + "\nNOM:          " + '"' +  nom +  '"' + "\nPRENOM:   " + '"' + prenom+ '"') ;

            curseur.moveToNext();
        }


        return myArray;
    }
}
