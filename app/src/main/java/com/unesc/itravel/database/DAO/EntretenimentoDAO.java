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
            contentValues.put(Entretenimento.COLUNA_ID_DADOS, entretenimentoModel.getId_dados());
            contentValues.put(Entretenimento.COLUNA_VALOR_ENTRETENIMENTO, entretenimentoModel.getValor_entretenimento());
            contentValues.put(Entretenimento.COLUNA_TOTAL, entretenimentoModel.getTotal());

            rowAffect = db.insert(Entretenimento.TABLE_NAME, null, contentValues);
        }
        finally {
            Close();
        }

        return rowAffect;
    }

    /*public Hospedagem findOne(String hospedagem) {
        Cursor cursor = null;
        Hospedagem hosp = null;
        try {
            Open();

            String selection = Hospedagem.COLUNA_ID + "=?";
            String[] selectionArgs = {hospedagem};

            cursor = db.query(Hospedagem.TABLE_NAME, colunas, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Hospedagem.COLUNA_ID));
                Float valor_diaria = cursor.getFloat(cursor.getColumnIndexOrThrow(Hospedagem.COLUNA_VALOR_DIARIA));
                Float total_noites = cursor.getFloat(cursor.getColumnIndexOrThrow(Hospedagem.COLUNA_TOTAL_NOITES));
                Float total_quartos = cursor.getFloat(cursor.getColumnIndexOrThrow(Hospedagem.COLUNA_TOTAL_QUARTOS));
                Float total = cursor.getFloat(cursor.getColumnIndexOrThrow(Hospedagem.COLUNA_TOTAL));

                hosp = new Hospedagem();

                hosp.setId(id);
                hosp.setValor_diaria(valor_diaria);
                hosp.setTotal_noites(total_noites);
                hosp.setTotal_quartos(total_quartos);
                hosp.setTotal(total);
            }

        }
        finally {
            cursor.close();
            Close();
        }

        return hosp;
    }*/
}
