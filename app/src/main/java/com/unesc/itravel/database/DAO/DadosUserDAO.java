package com.unesc.itravel.database.DAO;

import android.content.ContentValues;
import android.content.Context;

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

    /*public TarifaAerea findOne(String tarifaAerea) {
        Cursor cursor = null;
        TarifaAerea aerea = null;
        try {
            Open();

            String selection = TarifaAerea.COLUNA_ID + "=?";
            String[] selectionArgs = {tarifaAerea};

            cursor = db.query(TarifaAerea.TABLE_NAME, colunas, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Gasolina.COLUNA_ID));
                Float total_km = cursor.getFloat(cursor.getColumnIndexOrThrow(Gasolina.COLUNA_TOTAL_KM));
                Float media_litro = cursor.getFloat(cursor.getColumnIndexOrThrow(Gasolina.COLUNA_MEDIA_LITRO));
                Float custo_litro = cursor.getFloat(cursor.getColumnIndexOrThrow(Gasolina.COLUNA_CUSTO_LITRO));
                Float total_veic = cursor.getFloat(cursor.getColumnIndexOrThrow(Gasolina.COLUNA_TOTAL_VEIC));
                Float total = cursor.getFloat(cursor.getColumnIndexOrThrow(Gasolina.COLUNA_TOTAL));

                aerea = new Gasolina();

                aerea.setId(id);
                aerea.setTotal_km(total_km);
                aerea.setMedia_litro(media_litro);
                aerea.setCusto_litro(custo_litro);
                aerea.setTotal_veic(total_veic);
                aerea.setTotal(total);

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
