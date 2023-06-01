package com.unesc.itravel.database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.unesc.itravel.database.DBOpenHelper;
import com.unesc.itravel.database.model.Gasolina;
import com.unesc.itravel.database.model.Login;

import java.util.ArrayList;
import java.util.List;

public class GasolinaDAO extends AbstrataDAO{
    private static final String[] colunas = {
            Gasolina.COLUNA_ID,
            Gasolina.COLUNA_MEDIA_LITRO,
            Gasolina.COLUNA_CUSTO_LITRO,
            Gasolina.COLUNA_TOTAL_KM,
            Gasolina.COLUNA_TOTAL_VEIC,
            Gasolina.COLUNA_TOTAL
    };

    public GasolinaDAO(Context context) {
        db_helper = new DBOpenHelper(context);
    }

    public long insert(Gasolina gasolinaModel){
        long rowAffect = 0;
        try {
            Open();

            ContentValues contentValues = new ContentValues();
            contentValues.put(Gasolina.COLUNA_TOTAL_KM, gasolinaModel.getTotal_km().floatValue());
            contentValues.put(Gasolina.COLUNA_MEDIA_LITRO, gasolinaModel.getMedia_litro().floatValue());
            contentValues.put(Gasolina.COLUNA_CUSTO_LITRO, gasolinaModel.getCusto_litro().floatValue());
            contentValues.put(Gasolina.COLUNA_TOTAL_VEIC, gasolinaModel.getTotal_veic().floatValue());
            contentValues.put(Gasolina.COLUNA_TOTAL, gasolinaModel.getTotal().floatValue());


            rowAffect = db.insert(Gasolina.TABLE_NAME, null, contentValues);
        }
        finally {
            Close();
        }

        return rowAffect;
    }

    public Gasolina findOne(String gasolina) {
        Cursor cursor = null;
        Gasolina gas = null;
        try {
            Open();

            String selection = Gasolina.COLUNA_ID + "=?";
            String[] selectionArgs = {gasolina};

            cursor = db.query(Gasolina.TABLE_NAME, colunas, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Gasolina.COLUNA_ID));
                Float total_km = cursor.getFloat(cursor.getColumnIndexOrThrow(Gasolina.COLUNA_TOTAL_KM));
                Float media_litro = cursor.getFloat(cursor.getColumnIndexOrThrow(Gasolina.COLUNA_MEDIA_LITRO));
                Float custo_litro = cursor.getFloat(cursor.getColumnIndexOrThrow(Gasolina.COLUNA_CUSTO_LITRO));
                Float total_veic = cursor.getFloat(cursor.getColumnIndexOrThrow(Gasolina.COLUNA_TOTAL_VEIC));
                Float total = cursor.getFloat(cursor.getColumnIndexOrThrow(Gasolina.COLUNA_TOTAL));

                gas = new Gasolina();

                gas.setId(id);
                gas.setTotal_km(total_km);
                gas.setMedia_litro(media_litro);
                gas.setCusto_litro(custo_litro);
                gas.setTotal_veic(total_veic);
                gas.setTotal(total);

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

        return gas;
    }

    public List<Gasolina> selectAll() {
        List<Gasolina> gasolina = new ArrayList<>();
        try {
            Open();

            Cursor cursor = db.query(Gasolina.TABLE_NAME, colunas, null, null, null, null, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()){
                gasolina.add(CursorToStructure(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        }
        finally {
            Close();
        }

        return (gasolina);
    }

    public void delete(final long idGasolina){
        Open();
        db.delete(Gasolina.TABLE_NAME, Gasolina.COLUNA_ID + " =?", new String[]{""+idGasolina});
        Close();
    }

    public void deleteAll(){
        Open();
        db.delete(Gasolina.TABLE_NAME, null, null);
        Close();
    }

    public final Gasolina CursorToStructure(Cursor cursor){
        Gasolina gasolina = new Gasolina();

        gasolina.setId(cursor.getLong(0));
        gasolina.setTotal_km(cursor.getFloat(1));
        gasolina.setMedia_litro(cursor.getFloat(2));
        gasolina.setCusto_litro(cursor.getFloat(3));
        gasolina.setTotal_veic(cursor.getFloat(4));
        gasolina.setTotal(cursor.getFloat(4));

        return (gasolina);
    }
}
