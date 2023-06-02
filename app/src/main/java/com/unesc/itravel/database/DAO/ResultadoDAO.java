package com.unesc.itravel.database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.unesc.itravel.database.DBOpenHelper;
import com.unesc.itravel.database.model.Gasolina;
import com.unesc.itravel.database.model.Resultado;

import java.util.ArrayList;
import java.util.List;

public class ResultadoDAO extends AbstrataDAO{
    private static final String[] colunas = {
            Resultado.COLUNA_ID,
            Resultado.COLUNA_ID_DADOS,
            Resultado.COLUNA_TOTAL,
            Resultado.COLUNA_TOTAL_PESSOA
    };

    public ResultadoDAO(Context context) {
        db_helper = new DBOpenHelper(context);
    }

    public long insert(Resultado resultadoModel){
        long rowAffect = 0;
        try {
            Open();

            ContentValues contentValues = new ContentValues();
            contentValues.put(Resultado.COLUNA_ID_DADOS, resultadoModel.getId_dados());
            contentValues.put(Resultado.COLUNA_TOTAL, resultadoModel.getTotal());
            contentValues.put(Resultado.COLUNA_TOTAL_PESSOA, resultadoModel.getTotal_pessoa());

            rowAffect = db.insert(Resultado.TABLE_NAME, null, contentValues);
        }
        finally {
            Close();
        }

        return rowAffect;
    }

    public Resultado findOne(String resultado) {
        Cursor cursor = null;
        Resultado result = null;
        try {
            Open();

            String selection = Resultado.COLUNA_ID + "=?";
            String[] selectionArgs = {resultado};

            cursor = db.query(Resultado.TABLE_NAME, colunas, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Resultado.COLUNA_ID));
                Float total = cursor.getFloat(cursor.getColumnIndexOrThrow(Resultado.COLUNA_TOTAL));
                Float total_pessoa = cursor.getFloat(cursor.getColumnIndexOrThrow(Resultado.COLUNA_TOTAL_PESSOA));

                result = new Resultado();

                result.setId(id);
                result.setTotal(total);
                result.setTotal_pessoa(total_pessoa);
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

        return result;
    }
}
