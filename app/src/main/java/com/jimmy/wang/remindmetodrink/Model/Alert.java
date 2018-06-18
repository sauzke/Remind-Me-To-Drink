package com.jimmy.wang.remindmetodrink.Model;

public class Alert {
    public static final String TABLE_NAME = "alert";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ALERT_COUNT = "count";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+
            TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ALERT_COUNT + " INTEGER"
            + ")";

    private int id;
    private int count;

    public Alert() {

    }

    public Alert(int id, int count) {
        this.id = id;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
