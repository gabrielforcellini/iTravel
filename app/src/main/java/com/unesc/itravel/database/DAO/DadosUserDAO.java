package com.unesc.itravel.database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.unesc.itravel.database.DBOpenHelper;
import com.unesc.itravel.database.model.DadosUser;

public class DadosUserDAO extends AbstrataDAO{
    private static final String[] colunas = {
            DadosUser.COLUNA_ID,
            DadosUser.COLUNA_ID_LOGIN,
            DadosUser.COLUNA_VIAJANTES,
            DadosUser.COLUNA_QTD_DIAS
    };

    public DadosUserDAO(Context context) {
        db_helper = new DBOpenHelper(context);
    }

    public long insert(DadosUser dadosModel){
        long rowAffect = 0;
        try {
            Open();

            ContentValues contentValues = new ContentValues();
            contentValues.put(DadosUser.COLUNA_ID_LOGIN, dadosModel.getId_login());
            contentValues.put(DadosUser.COLUNA_VIAJANTES, dadosModel.getViajantes());
            contentValues.put(DadosUser.COLUNA_QTD_DIAS, dadosModel.getQtd_dias());

            rowAffect = db.insert(DadosUser.TABLE_NAME, null, contentValues);
        }
        finally {
            Close();
        }

        return rowAffect;
    }

    public DadosUser findOne(String dadosUser) {
        Cursor cursor = null;
        DadosUser dados = null;
        try {
            Open();

            String selection = DadosUser.COLUNA_ID + "=?";
            String[] selectionArgs = {dadosUser};

            cursor = db.query(DadosUser.TABLE_NAME, colunas, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DadosUser.COLUNA_ID));
                int id_login = cursor.getInt(cursor.getColumnIndexOrThrow(DadosUser.COLUNA_ID_LOGIN));
                Float viajantes = cursor.getFloat(cursor.getColumnIndexOrThrow(DadosUser.COLUNA_VIAJANTES));
                Float qtdDias = cursor.getFloat(cursor.getColumnIndexOrThrow(DadosUser.COLUNA_QTD_DIAS));

                dados = new DadosUser();

                dados.setId(id);
                dados.setId_login(id_login);
                dados.setViajantes(viajantes);
                dados.setQtd_dias(qtdDias);

            }
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }

            if (db != null) {
                db.close();
            }
        }

        return dados;
    }
}
