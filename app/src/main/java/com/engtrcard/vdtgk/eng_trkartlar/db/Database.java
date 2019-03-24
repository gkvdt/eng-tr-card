package com.engtrcard.vdtgk.eng_trkartlar.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "my.db";
    private static final int DB_VERSION = 1;


    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Constants.CREATE_TABLE_SQL);

    }

    public void AddData(String engValue, String trValue1, String trValue2, String trValue3, String trValue4, String trValue5) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Constants.ENG, engValue);
        values.put(Constants.TR1, trValue1);
        values.put(Constants.TR2, trValue2);
        values.put(Constants.TR3, trValue3);
        values.put(Constants.TR4, trValue4);
        values.put(Constants.TR5, trValue5);
        values.put(Constants.TRUE, 0);
        db.insert(Constants.TABLE_NAME, null, values);
        db.close();

    }

    public void ReCreateDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(Constants.DROP_TABLE);
        onCreate(db);
    }

    public HashMap<String, String>
    GetFromID(int index) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(Constants.GET_FROM_ID_SQL + index, null);
        HashMap<String, String> word = new HashMap<String, String>();
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                word.put(cursor.getColumnName(i), cursor.getString(i));
            }
        }
        return word;
    }

    public int DbSize() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + Constants.TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);

        int count = cursor.getCount();

        return count;

    }

    public void IncrementTrue(int index, int count) {
        SQLiteDatabase db = this.getWritableDatabase();
        String Sql = "UPDATE " + Constants.TABLE_NAME + " SET " + Constants.TRUE + "=" + (count + 1) + " WHERE " + Constants.ID + "=" + index;

        db.execSQL(Sql);
        db.close();
    }

    public void DecrementTrue(int index, int count) {
        if (count > 0) {
            SQLiteDatabase db = this.getWritableDatabase();
            String Sql = "UPDATE " + Constants.TABLE_NAME + " SET " + Constants.TRUE + "=" + (count - 1) + " WHERE " + Constants.ID + "=" + index;

            db.execSQL(Sql);
            db.close();
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
