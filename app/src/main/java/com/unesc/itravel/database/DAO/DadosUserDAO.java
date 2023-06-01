package com.unesc.itravel.database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.unesc.itravel.database.DBOpenHelper;
import com.unesc.itravel.database.model.DadosUser;
import com.unesc.itravel.database.model.TarifaAerea;

public class DadosUserDAO extends AbstrataDAO{
    private static final String[] colunas = {
            DadosUser.COLUNA_ID,
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
            contentValues.put(DadosUser.COLUNA_VIAJANTES, dadosModel.getViajantes());
            contentValues.put(DadosUser.COLUNA_QTD_DIAS, dadosModel.getQtd_dias());

            rowAffect = db.insert(DadosUser.TABLE_NAME, null, contentValues);
        }
        finally {
            Close();
        }

        return rowAffect;
    }

    public DadosUser findOne(String id_dados) {
        Cursor cursor = null;
        DadosUser dadosUser = null;
        try {
            Open();

            String selection = DadosUser.COLUNA_ID + "=?";
            String[] selectionArgs = { id_dados };

            cursor = db.query(DadosUser.TABLE_NAME, colunas, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DadosUser.COLUNA_ID));
                int viajantes = cursor.getInt(cursor.getColumnIndexOrThrow(DadosUser.COLUNA_VIAJANTES));
                int qtdDias = cursor.getInt(cursor.getColumnIndexOrThrow(DadosUser.COLUNA_QTD_DIAS));

                dadosUser = new DadosUser();

                dadosUser.setId(id);
                dadosUser.setQtd_dias(qtdDias);
                dadosUser.setViajantes(viajantes);
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

        return dadosUser;
    }
}
