package com.jimmy.wang.remindmetodrink.Model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WaterModel extends SQLiteOpenHelper implements Model{
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "water_db";

    public WaterModel(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(WaterAmount.CREATE_TABLE);
        db.execSQL(WaterAmount.UPDATE_TIMESTAMP_TRIGGER);
        db.execSQL(Alert.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + WaterAmount.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Alert.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public WaterAmount selectAmount(long id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(WaterAmount.TABLE_NAME,new String[]{WaterAmount.COLUMN_ID,WaterAmount.COLUMN_AMOUNT,WaterAmount.COLUMN_TIMESTAMP},
                WaterAmount.COLUMN_ID + "=?", new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        WaterAmount amount = null;

        try {
            amount = new WaterAmount(
                    cursor.getInt(cursor.getColumnIndex(WaterAmount.COLUMN_ID)),
                    cursor.getDouble(cursor.getColumnIndex(WaterAmount.COLUMN_AMOUNT)),
                    cursor.getString(cursor.getColumnIndex(WaterAmount.COLUMN_TIMESTAMP)));

        }catch(CursorIndexOutOfBoundsException exception){
        } finally {
            cursor.close();
        }

        return amount;
    }

    public long insertAmount(Double amount){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WaterAmount.COLUMN_AMOUNT,amount);

        long id = db.insert(WaterAmount.TABLE_NAME,null,values);

        db.close();

        return id;
    }

    public int updateAmount(WaterAmount waterAmount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WaterAmount.COLUMN_AMOUNT, waterAmount.getAmount());

        return db.update(WaterAmount.TABLE_NAME,values,WaterAmount.COLUMN_ID + "=?",
                new String[]{String.valueOf(waterAmount.getId())});
    }

    public long insertAlert(int alertCount){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Alert.COLUMN_ALERT_COUNT,alertCount);

        long id = db.insert(Alert.TABLE_NAME,null,values);

        db.close();

        return id;
    }

    public Alert selectAlert(long id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Alert.TABLE_NAME,new String[]{Alert.COLUMN_ID,Alert.COLUMN_ALERT_COUNT},
                Alert.COLUMN_ID + "=?", new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Alert alert = null;

        try {
            alert = new Alert(
                    cursor.getInt(cursor.getColumnIndex(Alert.COLUMN_ID)),
                    cursor.getInt(cursor.getColumnIndex(Alert.COLUMN_ALERT_COUNT)));

        }catch(CursorIndexOutOfBoundsException exception){
        } finally {
            cursor.close();
        }

        return alert;
    }

    public int updateAlert(Alert alert){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Alert.COLUMN_ALERT_COUNT, alert.getCount());

        return db.update(Alert.TABLE_NAME,values,Alert.COLUMN_ID + "=?",
                new String[]{String.valueOf(alert.getId())});
    }
}
