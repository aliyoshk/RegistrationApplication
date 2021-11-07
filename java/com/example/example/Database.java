package com.example.example;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    //Creation of the database name and tables happens here

    //this is the database name
    public static final String Database_Name = "register.db";

    // table names
    public static final String Table_Name = "Users";

    // Column names
    public static final String Col1 = "ID";
    public static final String Col2 = "Surname";
    public static final String Col3 = "Firstname";
    public static final String Col4 = "Phone";
    public static final String Col5 = "Email";
    public static final String Col6 = "Password";

    public Database(Context context)
    {
    super(context, Database_Name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +Table_Name+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Surname TEXT, Firstname TEXT, Phone INTEGER, Email TEXT, Password INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " +Database_Name);//Drop older table if exists
        onCreate(db);

    }


}
