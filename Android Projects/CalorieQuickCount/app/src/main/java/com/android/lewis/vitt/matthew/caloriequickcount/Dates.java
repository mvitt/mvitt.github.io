package com.android.lewis.vitt.matthew.caloriequickcount;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * Created by Matthew on 4/3/2017.
 */

public class Dates {
    UUID ID;
    Date todaysDate;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
    String date;

    public Dates() {
        this(UUID.randomUUID());

    }

    public Dates(UUID id) {
        ID = id;
        todaysDate = new Date();
        date = dateFormat.format(todaysDate);
    }

    public UUID getID() {
        return ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
