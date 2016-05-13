package com.example.owner.assignment4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 2015-11-05.
 */
public class DatabaseManager extends SQLiteOpenHelper {
    //
    private static final String DATABASE_NAME = "Student.db";
    private static final int DATABASE_VERSION = 1;
    private String[] tables=new String[4]; //table names
    private String[] tableCreatorString=new String[4]; //SQL statements to create tables

    //
    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    //initialize database table names and DDL statements
    public void dbInitialize(String[] tabless, String tableCreatorStringg[])
    { // SQLiteDatabase db = this.getWritableDatabase();
        this.tables=tabless;
        this.tableCreatorString=tableCreatorStringg;

      }

    // Create tablesggg
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Drop existing tables
        for (int i=0 ;i<tables.length;i++)
            db.execSQL("DROP TABLE IF EXISTS " + tables[i]);
        for (int i=0;i<tables.length;i++)
            db.execSQL(tableCreatorString[i]);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing tables
        for (int i=0;i<tables.length;i++)
            db.execSQL("DROP TABLE IF EXISTS " + tables[i]);

        // Create tables again
        onCreate(db);
    }

    void addRecord(ContentValues values, String tableName, String fields[],String record[]) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(tableName, null, values);
        db.close(); //close database connection
    }


    //return list of patient
    public List getPatientList(String tableName) {
        List table = new ArrayList();
        String selectQuery = "SELECT firstname,patient_Id  FROM " + tableName;
      SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList row=new ArrayList(); //to store one row
        //scroll over rows and store each row in an array list object
        if (cursor.moveToFirst())
        {
            do
            { // for each row
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    row.add(cursor.getString(i));
                }

                table.add(row); //add row to the list
    } while (cursor.moveToNext());
        }
        return table;
    }


    public List getTestResults(String tbl_test,String id) {
        List table = new ArrayList();
        String selectQuery = "SELECT *  FROM " + tbl_test+" WHERE patient_Id="+id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList row=new ArrayList();
        if (cursor.moveToFirst())
        {
            do
            { // for each row
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    row.add(cursor.getString(i));
                }
                table.add(row); //add row to the list
            } while (cursor.moveToNext());
        }
        return table;
    }

    public List getDataByName(String tbl, String id) {
        List table = new ArrayList();
        String selectQuery = "SELECT *  FROM " + tbl+" WHERE patient_Id="+id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList row=new ArrayList(); //to store one row
        //scroll over rows and store each row in an array list object
        if (cursor.moveToFirst())
        {
            do
            { // for each row
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    row.add(cursor.getString(i));
                }
                table.add(row); //add row to the list
            } while (cursor.moveToNext());
        }
        return table;
    }
}
