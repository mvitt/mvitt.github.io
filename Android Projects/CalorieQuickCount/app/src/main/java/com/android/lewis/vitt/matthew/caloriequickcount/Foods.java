package com.android.lewis.vitt.matthew.caloriequickcount;

import java.util.UUID;

/**
 * Created by Matthew on 4/3/2017.
 */

public class Foods {
    UUID ID;
    String date;
    String name;
    int calories;


    public Foods() {
        this(UUID.randomUUID());
    }

    public Foods(UUID id) {
        ID = id;
        name = "Food";
        calories = 0;

    }

    public Foods(String name, int calories) {
        ID = UUID.randomUUID();
        this.name = name;
        this.calories = calories;
    }

    public UUID getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
