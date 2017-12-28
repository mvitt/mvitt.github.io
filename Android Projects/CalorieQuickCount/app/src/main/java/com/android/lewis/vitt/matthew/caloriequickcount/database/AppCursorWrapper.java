package com.android.lewis.vitt.matthew.caloriequickcount.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.android.lewis.vitt.matthew.caloriequickcount.Dates;
import com.android.lewis.vitt.matthew.caloriequickcount.Foods;
import com.android.lewis.vitt.matthew.caloriequickcount.database.AppDBSchema.DatesTable;
import com.android.lewis.vitt.matthew.caloriequickcount.database.AppDBSchema.FoodsTable;

import java.util.UUID;

/**
 * Created by Matthew on 4/5/2017.
 */

public class AppCursorWrapper extends CursorWrapper {

    public AppCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Dates getDate(){
        String dateUUIDStr = getString(getColumnIndex(DatesTable.Cols.UUID));
        String date = getString(getColumnIndex(DatesTable.Cols.DATE));

        Dates dates = new Dates(UUID.fromString(dateUUIDStr));
        dates.setDate(date);

        return dates;
    }

    public Foods getFoods(){
        String foodUUIDStr = getString(getColumnIndex(FoodsTable.Cols.UUID));
        String date = getString(getColumnIndex(FoodsTable.Cols.DATE));
        String name = getString(getColumnIndex(FoodsTable.Cols.NAME));
        String calories = getString(getColumnIndex(FoodsTable.Cols.CALORIES));

        Foods foods = new Foods(UUID.fromString(foodUUIDStr));
        foods.setDate(date);
        foods.setName(name);
        foods.setCalories(Integer.parseInt(calories));

        return foods;
    }
}
