package com.example.owner.firebasedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by owner on 4/7/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public  static final String  DATABASE_NAME = "NotificationDB.db";
    public  static final String  TABLE_NAME = "notification_table";
    public  static final String  COL_1 = "ID";
    public  static final String  COL_2 = "DEPTO";
    public  static final String  COL_3 = "MESSAGE";
    public  static final String  COL_4 = "TITLE";
    public  static final String  COL_5 = "ACTIVE";

    public  final ArrayList<String> arrayList = new ArrayList<String>();
    public  final ArrayList<String> arrayActive = new ArrayList<String>();




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("create table " + TABLE_NAME +" (ID TEXT PRIMARY KEY , DEPTO TEXT, MESSAGE TEXT, TITLE TEXT, ACTIVE TEXT)");
        Log.d("Created: ","DB creada" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);

    }

    public  boolean insertData(String ID, String depto, String message, String title, String active){


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,ID);
        contentValues.put(COL_2,depto);
        contentValues.put(COL_3,message);
        contentValues.put(COL_4,title);
        contentValues.put(COL_5,active);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select* from "+ TABLE_NAME,null);

        return  res;
    }


    public boolean verificarDatos(String ID){

        Cursor res = getAllData();

        while (res.moveToNext()){
            String sID =  res.getString(0);
            String sActive = res.getString(4);
            Log.d("Array Datos" ,  sID + sActive);
            arrayList.add(sID);
            arrayActive.add(sActive);
        }

        if (arrayList.contains(ID) && arrayActive.contains("desactivar")){
            Log.d("true","true");
            return true;
        }
        else
            return false;

        //return false;
    }

    public  boolean active(String postition){

        ArrayList<String> activaArray= new ArrayList<>();

        Cursor res = getAllData();
        while (res.moveToNext()) {
            String datos = res.getString(0);
            activaArray.add(datos);
        }

        if (activaArray.contains(postition)) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_5,"Activar");
            db.update(TABLE_NAME,contentValues,"ID = ?",new String[]{postition });

            return true;
        }

        return false;
    }


}
