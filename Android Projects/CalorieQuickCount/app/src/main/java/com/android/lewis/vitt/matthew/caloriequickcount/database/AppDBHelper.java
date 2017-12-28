package com.android.lewis.vitt.matthew.caloriequickcount.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.android.lewis.vitt.matthew.caloriequickcount.database.AppDBSchema.DatesTable;
import com.android.lewis.vitt.matthew.caloriequickcount.database.AppDBSchema.FoodsTable;


/**
 * Created by Matthew on 4/5/2017.
 */

public class AppDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "caloriesDataBase.db";

    public AppDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + DatesTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                DatesTable.Cols.UUID + ", " +
                DatesTable.Cols.DATE + " text" +
                ")"
        );

        db.execSQL("create table " + FoodsTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                FoodsTable.Cols.UUID + ", " +
                FoodsTable.Cols.DATE + " text, " +
                FoodsTable.Cols.NAME + ", " +
                FoodsTable.Cols.CALORIES + ", " +
                "foreign key(" + FoodsTable.Cols.DATE + ") references " + DatesTable.NAME + "(" + DatesTable.Cols.DATE + ")" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
