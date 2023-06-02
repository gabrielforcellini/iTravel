package com.unesc.itravel.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.unesc.itravel.database.model.ControleGastos;
import com.unesc.itravel.database.model.DadosUser;
import com.unesc.itravel.database.model.Entretenimento;
import com.unesc.itravel.database.model.Gasolina;
import com.unesc.itravel.database.model.Hospedagem;
import com.unesc.itravel.database.model.Login;
import com.unesc.itravel.database.model.Refeicao;
import com.unesc.itravel.database.model.Resultado;
import com.unesc.itravel.database.model.TarifaAerea;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NOME = "banco.db";
    private static final int DATABASE_VERSION = 6;


    public DBOpenHelper(Context context) {
        super(context, DATABASE_NOME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(Login.CREATE_TABLE);
            sqLiteDatabase.execSQL(Gasolina.CREATE_TABLE);
            sqLiteDatabase.execSQL(Hospedagem.CREATE_TABLE);
            sqLiteDatabase.execSQL(Refeicao.CREATE_TABLE);
            sqLiteDatabase.execSQL(Entretenimento.CREATE_TABLE);
            sqLiteDatabase.execSQL(TarifaAerea.CREATE_TABLE);
            sqLiteDatabase.execSQL(DadosUser.CREATE_TABLE);
            sqLiteDatabase.execSQL(ControleGastos.CREATE_TABLE);
            sqLiteDatabase.execSQL(Resultado.CREATE_TABLE);
        }catch(SQLException e) {
            System.out.println("Erro ao criar tabela gasolina: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
//            sqLiteDatabase.execSQL(Login.DROP_TABLE);
            sqLiteDatabase.execSQL(Gasolina.DROP_TABLE);
            sqLiteDatabase.execSQL(Hospedagem.DROP_TABLE);
            sqLiteDatabase.execSQL(Refeicao.DROP_TABLE);
            sqLiteDatabase.execSQL(Entretenimento.DROP_TABLE);
            sqLiteDatabase.execSQL(TarifaAerea.DROP_TABLE);
//            sqLiteDatabase.execSQL(DadosUser.DROP_TABLE);
            sqLiteDatabase.execSQL(ControleGastos.DROP_TABLE);
            sqLiteDatabase.execSQL(Resultado.DROP_TABLE);

//            sqLiteDatabase.execSQL(Login.CREATE_TABLE);
            sqLiteDatabase.execSQL(Gasolina.CREATE_TABLE);
            sqLiteDatabase.execSQL(Hospedagem.CREATE_TABLE);
            sqLiteDatabase.execSQL(Refeicao.CREATE_TABLE);
            sqLiteDatabase.execSQL(Entretenimento.CREATE_TABLE);
            sqLiteDatabase.execSQL(TarifaAerea.CREATE_TABLE);
//            sqLiteDatabase.execSQL(DadosUser.CREATE_TABLE);
            sqLiteDatabase.execSQL(ControleGastos.CREATE_TABLE);
            sqLiteDatabase.execSQL(Resultado.CREATE_TABLE);
        }catch(SQLException e) {
            System.out.println("Erro ao criar tabela gasolina: " + e.getMessage());
        }
    }
}
