package com.unesc.itravel.database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.unesc.itravel.database.DBOpenHelper;
import com.unesc.itravel.database.model.Gasolina;
import com.unesc.itravel.database.model.TarifaAerea;

import java.util.ArrayList;
import java.util.List;

public class TarifaAereaDAO extends AbstrataDAO{
    private static final String[] colunas = {
            TarifaAerea.COLUNA_ID,
            TarifaAerea.COLUNA_PASSAGEM,
            TarifaAerea.COLUNA_ALUGUEL_CARRO,
            TarifaAerea.COLUNA_TOTAL
    };

    public TarifaAereaDAO(Context context) {
        db_helper = new DBOpenHelper(context);
    }

    public long insert(TarifaAerea tarifaAereaModel){
        long rowAffect = 0;
        try {
            Open();

            ContentValues contentValues = new ContentValues();
            contentValues.put(TarifaAerea.COLUNA_PASSAGEM, tarifaAereaModel.getPassagem());
            contentValues.put(TarifaAerea.COLUNA_ALUGUEL_CARRO, tarifaAereaModel.getAluguel_carro());
            contentValues.put(TarifaAerea.COLUNA_TOTAL, tarifaAereaModel.getTotal());

            rowAffect = db.insert(TarifaAerea.TABLE_NAME, null, contentValues);
        }
        finally {
            Close();
        }

        return rowAffect;
    }

    /*public TarifaAerea findOne(String tarifaAerea) {
        Cursor cursor = null;
        TarifaAerea aerea = null;
        try {
            Open();

            String selection = TarifaAerea.COLUNA_ID + "=?";
            String[] selectionArgs = {tarifaAerea};

            cursor = db.query(TarifaAerea.TABLE_NAME, colunas, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {

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

        return aerea;
    }*/
}
