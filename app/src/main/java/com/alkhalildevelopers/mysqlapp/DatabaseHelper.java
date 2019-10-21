package com.alkhalildevelopers.mysqlapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Create some Variables for Your Table Contents like : Database Name,Table Name,Columns Names etc.
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME= "Student_Table";
    public static final String Col_1 = "ID";
    public static final String Col_2 = "NAME";
    public static final String Col_3 = "SURNAME";
    public static final String Col_4 = "MARKS";

//Create Constructor for this SQLiteOpenHelper Class
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //This is OnCreate Method .here you have to create your Table in SQLite through SQL Query command
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT, SURNAME TEXT , MARKS INTEGER )";
        db.execSQL(create);
        Log.d("kb" ,"Your Database table is created successfully");

    }

    //This Method/Function will be executed when your Database is Updated
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME); //this sql-query will be used to delete the existing Table Data.
        onCreate(db);
    }

    //This Method/Function will be used for inserting/Adding data into your SQLite-Database
    public boolean insertData (String name,String surname,String marks){
        SQLiteDatabase db = this.getWritableDatabase();         //get WritableDatabase permission
        ContentValues values = new ContentValues();             //Create ContentValues object to get the Values
        values.put(Col_2,name);
        values.put(Col_3,surname);
        values.put(Col_4,marks);

        long success = db.insert(TABLE_NAME,null,values);
        if (success == -1){
            return false;
        }else {
            return true;
        }

    }

    // This Method/Function will be used to retrieve/Show data/get Data --through Cursor function
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLE_NAME,null);
        return  cursor;
    }


    // This Method/Function will used to Update your data from your Database Table
    public  boolean updateData (String id,String name,String surname,String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col_1 , id);
        values.put(Col_2,name);
        values.put(Col_3,surname);
        values.put(Col_4,marks);

        db.update(TABLE_NAME,values,"ID = ?",new String[]{id});
        return true;

    }

    //This Method/Function will be used to Delete data from your Database Table
    public boolean deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col_1,id);
        db.delete(TABLE_NAME,"ID = ?",new String[]{id});
        return true;

    }
}
