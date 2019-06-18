package com.example.sqlite.SQliteListView;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBhelper extends SQLiteOpenHelper
{
    Context context;
    private static final String DATABASE_NAME = "user.db";
    private static final String TAblE_NAME = "people_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "NAME";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String our_table = "CREATE TABLE "+TAblE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT)";
        db.execSQL(our_table);
        Toast.makeText(context, "Table is created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " +TAblE_NAME);
        onCreate(db);
    }

    public  boolean addData(String item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2, item);

        long x = db.insert(TAblE_NAME,null,contentValues);

        if(x == -1)
            return false;
        else
            return true;
    }

    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TAblE_NAME, null);
        return cursor;
    }

    public Cursor getSelectedNameId(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+COL1+ "FROM "+TAblE_NAME+" WHERE "+COL2+" = '"+name+ "' ",null);
        return cursor;

    }

    public void UpdateData(int ID, String newName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TAblE_NAME+ " SET "+COL2+ "= '"+newName+"' WHERE "+COL1+ "= '"+ID+"'";
        db.execSQL(query);
    }

    public void deleData(int id,String name)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "DELETE FROM "+TAblE_NAME+ "WHERE" +COL1+" = '"+id+"'";
        db.execSQL(query);

    }
}
