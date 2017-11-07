package com.example.lence.dudos.dateBase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MyDb";

    public static final String TABLE_USER = "user";

    public static final String KEY_USER_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_AGE = "age";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_MARRIED = "married";

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_USER +"("
                + KEY_USER_ID+ " integer primary key autoincrement,"
                + KEY_NAME + " text,"
                + KEY_AGE + " text,"
                + KEY_GENDER + " text,"
                + KEY_MARRIED + " text"
                +");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
