package com.unesc.itravel.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.unesc.itravel.database.model.Gasolina;
import com.unesc.itravel.database.model.Login;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NOME = "banco.db";
    private static final int DATABASE_VERSION = 1;


    public DBOpenHelper(Context context) {
        super(context, DATABASE_NOME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(Login.CREATE_TABLE);
            sqLiteDatabase.execSQL(Gasolina.CREATE_TABLE);
            System.out.println("Tabela gasolina criada com sucesso");
        }catch(SQLException e) {
            System.out.println("Erro ao criar tabela gasolina: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
