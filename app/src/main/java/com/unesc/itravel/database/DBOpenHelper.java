package com.unesc.itravel.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.unesc.itravel.database.model.Login;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NOME = "banco.db";
    private static final int DATABASE_VERSION = 1;


    public DBOpenHelper(Context context) {
        super(context, DATABASE_NOME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Login.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
