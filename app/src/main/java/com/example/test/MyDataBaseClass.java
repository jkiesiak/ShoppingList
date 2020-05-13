package com.example.test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDataBaseClass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "testt";
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_NAME = "groceryList";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_FROM_ANOTHER_TAB = "NameOfList";
    /////////2nd table
    public static final String TABLE_NAME_2 = "ListOfLists";
    public static final String COLUMN_NAME_List = "list_name";
    public static final String COLUMN_List_Type = "lista_typ";


    Context context;

    private String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE  " +  TABLE_NAME
            +" (  COLUMN_ID  INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " VARCHAR(255) NOT NULL, " +
            COLUMN_AMOUNT + " INTEGER NOT NULL,  " +
            COLUMN_FROM_ANOTHER_TAB + " VARCHAR(255) NOT NULL  " + " ); ";

    private String SQL_CREATE_LIST_TABLE = "CREATE TABLE  " +  TABLE_NAME_2
            +" (  COLUMN_ID  INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_NAME_List + " VARCHAR(255) NOT NULL,  "
            +COLUMN_List_Type + " VARCHAR(255) NOT NULL  "
            +" ); ";
//            COLUMN_AMOUNT + " INTEGER NOT NULL  " +

    public MyDataBaseClass(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(SQL_CREATE_LIST_TABLE);
            db.execSQL(SQL_CREATE_GROCERYLIST_TABLE);
            Toast.makeText(context, "Created list table and grocery table", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(context, "errore Created list table", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //// metoda dla spinnera
    public List<String> getAllLabels() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT  " +  COLUMN_NAME_List + "  FROM  " + TABLE_NAME_2 + "  ORDER BY COLUMN_ID ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return labels;
    }

////////// ładuje dane na liste
    public  Cursor viewIngriedients(String list) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME  +   " WHERE " + COLUMN_FROM_ANOTHER_TAB + " = '" + list + "'";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

///////////// wybieram konkretny element z listView i sciagam jego id
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT COLUMN_ID "  + " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_NAME + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE COLUMN_ID " + " = '" + id + "'" +
                " AND " + COLUMN_NAME + " = '" + name + "'";
        db.execSQL(query);
    }
///////analogiczneie do ingr
    public Cursor getListID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT COLUMN_ID "  + " FROM " + TABLE_NAME_2 +
                " WHERE " + COLUMN_NAME_List + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public  Cursor viewLists() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_2;
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }
    public void deleteList(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME_2 + " WHERE COLUMN_ID " + " = '" + id + "'" +
                " AND " + COLUMN_NAME_List + " = '" + name + "'";
        String query2 = "DELETE FROM " + TABLE_NAME + " WHERE COLUMN_FROM_ANOTHER_TAB " +  " = '" + name + "'";

        db.execSQL(query);
        db.execSQL(query2);
    }

}
