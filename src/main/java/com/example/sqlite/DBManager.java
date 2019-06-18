package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBManager extends SQLiteOpenHelper //this class is responsible for making Database in SQLite
{
    private Context context;
    private static final String DATABASE_NAME = "people.db";
    private static final String TABLE_NAME = "usertable";
    private static final String COL1="ID";
    private static final String COL2="NAME";
    private static final String COL3="EMAIL";
    private static final String COL4="TVSHOW";

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null ,1); //when we sub class object make subclass constructor call and
        //sub class constructor imlicitly call our parrent non parametrized constructor but if we want to call super class
        //parameterized constructor we use super() keyword.. super is super class type . and it is a local variable in every instance method/

        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_table = "CREATE TABLE " +TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "NAME TEXT, EMAIL TEXT, TVSHOW TEXT)";  //Create table

        db.execSQL(create_table);
        Toast.makeText(context, "Table created", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE_NAME); //this query execute when table is already created it will drop that table
        onCreate(db);
    }

    public boolean addData(String name,String email,String tvShow){

        SQLiteDatabase db = this.getWritableDatabase(); //here we use this reference variable this is a local refrence variable
        //in every instance method..point to caller object.

        ContentValues contentValues = new ContentValues(); //store data in key and value
        contentValues.put(COL2,name);
        contentValues.put(COL3,email);
        contentValues.put(COL4,tvShow);

        long result = db.insert(TABLE_NAME,null,contentValues); //

        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor showDataTable()
    {
        SQLiteDatabase db = this.getWritableDatabase(); //here with rawquery method we receive our table's copy table.
        Cursor cursor = db.rawQuery("SELECT * fROM "+ TABLE_NAME , null);
        return cursor;

    }

    public boolean setUpdateData(String id, String name,String email,String tvshow)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2,name);
        contentValues.put(COL3,email);
        contentValues.put(COL4,tvshow);
        db.update(TABLE_NAME,contentValues, "ID = ?", new String[] {id});

        return true;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[] {id});
    }
}
