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

    public void AddData(String engValue, String trValue) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Constants.ENG, engValue);
        values.put(Constants.TR, trValue);
        values.put(Constants.TRUE, 0);
        db.insert(Constants.TABLE_NAME, null, values);
        db.close();

    }

    public void ReCreateDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(Constants.DROP_TABLE);
        onCreate(db);
    }

    public HashMap<String, String> GetFromID(int index) {
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

    // TODO: 11/22/18 update fonksyonu yazılacak sadece true


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
