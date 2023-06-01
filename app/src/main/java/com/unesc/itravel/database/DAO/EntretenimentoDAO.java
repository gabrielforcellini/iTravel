package com.unesc.itravel.database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.unesc.itravel.database.DBOpenHelper;
import com.unesc.itravel.database.model.Entretenimento;
import com.unesc.itravel.database.model.Gasolina;

import java.util.ArrayList;
import java.util.List;

public class EntretenimentoDAO extends AbstrataDAO{
    private static final String[] colunas = {
            Entretenimento.COLUNA_ID,
            Entretenimento.COLUNA_VALOR_ENTRETENIMENTO,
            Entretenimento.COLUNA_TOTAL,
    };

    public EntretenimentoDAO(Context context) {
        db_helper = new DBOpenHelper(context);
    }

    public long insert(Entretenimento entretenimentoModel){
        long rowAffect = 0;
        try {
            Open();

            ContentValues contentValues = new ContentValues();
            contentValues.put(Entretenimento.COLUNA_VALOR_ENTRETENIMENTO, entretenimentoModel.getValor_entretenimento());
            contentValues.put(Entretenimento.COLUNA_TOTAL, entretenimentoModel.getTotal());

            rowAffect = db.insert(Entretenimento.TABLE_NAME, null, contentValues);
        }
        finally {
            Close();
        }

        return rowAffect;
    }

    public Entretenimento findOneByIdDados(String id_dados) {
        Cursor cursor = null;
        Entretenimento entr = null;
        try {
            Open();

            // Mudar para COLUNA_ID_DADOS
            String selection = Entretenimento.COLUNA_ID + "=?";
            String[] selectionArgs = { id_dados };

            cursor = db.query(Entretenimento.TABLE_NAME, colunas, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Entretenimento.COLUNA_ID));
                float valor_entretenimento = cursor.getFloat(cursor.getColumnIndexOrThrow(Entretenimento.COLUNA_VALOR_ENTRETENIMENTO));
                float valor_total = cursor.getFloat(cursor.getColumnIndexOrThrow(Entretenimento.COLUNA_TOTAL));
                entr = new Entretenimento();

                entr.setId(id);
                entr.setValor_entretenimento(valor_entretenimento);
                entr.setTotal(valor_total);
            }

        }
        finally {
            cursor.close();
            Close();
        }

        return entr;
    }
}
