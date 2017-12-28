package com.android.lewis.vitt.matthew.caloriequickcount.database;

/**
 * Created by Matthew on 4/5/2017.
 */

public class AppDBSchema {
    public static final class DatesTable {
        public static final String NAME = "dates";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String DATE = "date";
        }
    }

    public static final class FoodsTable {
        public static final String NAME = "foods";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String DATE = "date";
            public static final String NAME = "name";
            public static final String CALORIES = "calories";
        }
    }
}
