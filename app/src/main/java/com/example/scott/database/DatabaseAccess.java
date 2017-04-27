package com.example.scott.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Scott on 4/26/2017.
 */

public class DatabaseAccess extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "practiceDB.db";
    private static final String table = "users";
    private static final String Id = "id";
    private static final String user = "user";
    private static final String pass = "pass";
    SQLiteDatabase db;

    public DatabaseAccess(Context context)
    {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + table + " (id integer primary key not null , "
            + "user text not null , pass text not null);");
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table);
        this.onCreate(db);
    }

    public void addUser(String user, String pass)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Cursor cursor = db.rawQuery("select * from users", null);
        int count = cursor.getCount();

        values.put("id", count);
        values.put("user", user);
        values.put("pass", pass);

        db.insert(table, null, values);
        db.close();
    }

    public String userPass(String user)
    {
        db = this.getReadableDatabase();
        String u, p;
        p = "not found";
        Cursor cursor = db.rawQuery("select user, pass from "+ table, null);
        if (cursor.moveToFirst())
        {
            do {
                u = cursor.getString(0);

                if( u.equals(user)) {
                    p = cursor.getString(1);
                }
            }
            while (cursor.moveToNext());
        }

        db.close();
        return p;
    }
}
