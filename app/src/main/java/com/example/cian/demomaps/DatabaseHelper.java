package com.example.cian.demomaps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cian on 01/03/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "reports.db";
    public static final String TABLE_NAME = "reports_table";
    public static final String COL_1 = "USER_ID";
    public static final String COL_2 = "REPORT_LAT";
    public static final String COL_3 = "REPORT_LONG";
    public static final String COL_4 = "REPORT_ADDRESS";
    public static final String COL_5 = "REPORT_DESCRIPTION";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, REPORT_LAT DOUBLE, REPORT_LONG DOUBLE, REPORT_ADDRESS TEXT, REPORT_DESCRIPTION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
