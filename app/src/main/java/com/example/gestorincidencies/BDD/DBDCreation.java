package com.example.gestorincidencies.BDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gestorincidencies.APP.incidencia;

import java.util.ArrayList;

import static com.example.gestorincidencies.BDD.DBDeclaration.IncidenciaDBD.Nombre_Emergencia;
import static com.example.gestorincidencies.BDD.DBDeclaration.IncidenciaDBD.Nombre_Incidencia;
import static com.example.gestorincidencies.BDD.DBDeclaration.IncidenciaDBD.Nombre_Tabla;
import static com.example.gestorincidencies.BDD.DBDeclaration.IncidenciaDBD.ID;

public class DBDCreation extends SQLiteOpenHelper {
    public static final int DBD_Version = 1;
    public static final String DBD_Nom = "incidencias.db";

    private static final String Creacion_Entradas_SQL = "CREATE TABLE " + Nombre_Tabla + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Nombre_Incidencia + " TEXT," + Nombre_Emergencia + " TEXT)";


    public DBDCreation(Context context) {
        super(context, DBD_Nom, null, DBD_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Creacion_Entradas_SQL);
        Log.d("Jertox ANOUNCE","Database created!!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void insertIncidencia(SQLiteDatabase db, incidencia i){
        //Check the bd is open
        if (db.isOpen()){
            //Creation of the register for insert object with the content values
            ContentValues values = new ContentValues();

            //Insert the incidence getting all values
            values.put(Nombre_Incidencia, i.getNom());
            values.put(Nombre_Emergencia, i.getPrioritat());

            db.insert(Nombre_Tabla, null, values);
        }else{
            Log.d("sql","BDD cerrada");
        }
    }

    public ArrayList<incidencia> LIncidencia(){
        String sql = "select * from " + Nombre_Tabla;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<incidencia> inc = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                String nom = cursor.getString(1);
                String emergencia = cursor.getString(2);
                inc.add(new incidencia(nom, emergencia));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return inc;
    }
    //delete all
    public void eliminar(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(Nombre_Tabla,null,null);
        //db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }

    //delete an specific item
    public void delinc(int incid){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM "+Nombre_Tabla+" WHERE id="+incid);
        db.close();
    }
}
