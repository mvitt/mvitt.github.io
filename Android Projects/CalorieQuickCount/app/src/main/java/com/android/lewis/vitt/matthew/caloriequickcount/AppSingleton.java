package com.android.lewis.vitt.matthew.caloriequickcount;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.lewis.vitt.matthew.caloriequickcount.database.AppCursorWrapper;
import com.android.lewis.vitt.matthew.caloriequickcount.database.AppDBHelper;
import com.android.lewis.vitt.matthew.caloriequickcount.database.AppDBSchema.DatesTable;
import com.android.lewis.vitt.matthew.caloriequickcount.database.AppDBSchema.FoodsTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Created by Matthew on 4/5/2017.
 */

public class AppSingleton {
    private static AppSingleton appSingleton;
    private Context appContext;
    private SQLiteDatabase database;

    public static AppSingleton get(Context context) {
        if (appSingleton == null) {
            appSingleton = new AppSingleton(context);
        }

        return appSingleton;
    }

    private AppSingleton(Context context) {
        appContext = context.getApplicationContext();
        database = new AppDBHelper(appContext).getWritableDatabase();
    }


    public List<Dates> getDates() {
        List<Dates> dates = new ArrayList<>();
        AppCursorWrapper cursorWrapper = queryDates(null, null);

        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                dates.add(cursorWrapper.getDate());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }

        return dates;
    }

    public List<Foods> getFoods(Dates date) {
        List<Foods> foods = new ArrayList<>();
        AppCursorWrapper cursorWrapper = queryFoods(FoodsTable.Cols.DATE + " = ?", new String[]{date.getDate()});

        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                foods.add(cursorWrapper.getFoods());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }

        return foods;
    }


    public Dates getDate(UUID id) {
        AppCursorWrapper cursorWrapper = queryDates(DatesTable.Cols.UUID + " = ?", new String[]{id.toString()});

        try {
            if (cursorWrapper.getCount() == 0) {
                return null;
            }

            cursorWrapper.moveToFirst();
            return cursorWrapper.getDate();
        } finally {
            cursorWrapper.close();
        }
    }

    public Foods getFood(UUID id) {
        AppCursorWrapper cursorWrapper = queryFoods(FoodsTable.Cols.UUID + " = ?", new String[]{id.toString()});

        try {
            if (cursorWrapper.getCount() == 0) {
                return null;
            }

            cursorWrapper.moveToFirst();

            return cursorWrapper.getFoods();
        } finally {
            cursorWrapper.close();
        }
    }

    public void removeFood(UUID id) {
        database.execSQL("PRAGMA foreign_keys=OFF;");
        database.delete(FoodsTable.NAME, FoodsTable.Cols.UUID + " = ?", new String[]{id.toString()});

    }

    public int getTotalCalories(Dates dates) {
        Cursor cursor = database.rawQuery("select sum(" + FoodsTable.Cols.CALORIES + ") from " + FoodsTable.NAME + " where " + DatesTable.Cols.DATE + " = " + "\"" + dates.getDate() + "\"", null);
        try {
            if (cursor.moveToFirst()) {
                return cursor.getInt(0);
            } else return 0;
        } finally {
            cursor.close();
        }
    }


    private static ContentValues getDateContentValues(Dates dates) {
        ContentValues values = new ContentValues();
        values.put(DatesTable.Cols.UUID, dates.getID().toString());
        values.put(DatesTable.Cols.DATE, dates.getDate());
        return values;
    }

    private static ContentValues getFoodContentValues(Foods foods, Dates dates) {
        ContentValues values = new ContentValues();
        values.put(FoodsTable.Cols.UUID, foods.getID().toString());
        values.put(FoodsTable.Cols.DATE, dates.getDate());
        values.put(FoodsTable.Cols.NAME, foods.getName());
        values.put(FoodsTable.Cols.CALORIES, foods.getCalories());
        return values;
    }

    public void addDate(Dates dates) {
        ContentValues values = getDateContentValues(dates);
        database.insert(DatesTable.NAME, null, values);
    }

    public void addFood(Foods foods, Dates dates) {
        ContentValues values = getFoodContentValues(foods, dates);
        database.insert(FoodsTable.NAME, null, values);
    }

    public void updateDate(Dates dates) {
        String dateUUID = dates.getID().toString();
        ContentValues values = getDateContentValues(dates);

        database.update(DatesTable.NAME, values, DatesTable.Cols.UUID + " = ?", new String[]{dateUUID});
    }

    public void updateFood(Foods foods, Dates dates) {
        String foodUUID = foods.getID().toString();
        ContentValues values = getFoodContentValues(foods, dates);

        database.update(FoodsTable.NAME, values, FoodsTable.Cols.UUID + " = ?", new String[]{foodUUID});
    }


    private AppCursorWrapper queryDates(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(DatesTable.NAME, null, whereClause, whereArgs, null, null, null);

        return new AppCursorWrapper(cursor);
    }

    private AppCursorWrapper queryFoods(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(FoodsTable.NAME, null, whereClause, whereArgs, null, null, null);
        return new AppCursorWrapper(cursor);
    }

    public boolean IsTodaysDateAdded(String[] value) {
        Cursor cursor = database.query(DatesTable.NAME, null, DatesTable.Cols.DATE + " = ?", value, null, null, null);

        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }

        cursor.close();
        return true;
    }


}
