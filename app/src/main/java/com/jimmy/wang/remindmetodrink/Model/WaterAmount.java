package com.jimmy.wang.remindmetodrink.Model;

public class WaterAmount {
    public static final String TABLE_NAME = "waterAmount";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+
            TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_AMOUNT + " DOUBLE,"
            + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")";

    private int id;
    private double amount;
    private String timestamp;

    public WaterAmount() {
    }

    public WaterAmount(int id, double amount, String timestamp) {
        this.id = id;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
