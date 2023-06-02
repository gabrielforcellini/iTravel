package com.unesc.itravel.database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.unesc.itravel.database.DBOpenHelper;
import com.unesc.itravel.database.model.Hospedagem;

import java.util.ArrayList;
import java.util.List;

public class HospedagemDAO extends AbstrataDAO{
    private static final String[] colunas = {
            Hospedagem.COLUNA_ID,
            Hospedagem.COLUNA_VALOR_DIARIA,
            Hospedagem.COLUNA_TOTAL_NOITES,
            Hospedagem.COLUNA_TOTAL_QUARTOS,
            Hospedagem.COLUNA_TOTAL
    };

    public HospedagemDAO(Context context) {
        db_helper = new DBOpenHelper(context);
    }

    public long insert(Hospedagem hospedagemModel){
        long rowAffect = 0;
        try {
            Open();

            ContentValues contentValues = new ContentValues();
            contentValues.put(Hospedagem.COLUNA_ID_DADOS, hospedagemModel.getId_dados());
            contentValues.put(Hospedagem.COLUNA_VALOR_DIARIA, hospedagemModel.getValor_diaria());
            contentValues.put(Hospedagem.COLUNA_TOTAL_NOITES, hospedagemModel.getTotal_noites());
            contentValues.put(Hospedagem.COLUNA_TOTAL_QUARTOS, hospedagemModel.getTotal_quartos());
            contentValues.put(Hospedagem.COLUNA_TOTAL, hospedagemModel.getTotal());


            rowAffect = db.insert(Hospedagem.TABLE_NAME, null, contentValues);
        }
        finally {
            Close();
        }

        return rowAffect;
    }

    public Hospedagem findOne(String hospedagem) {
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
    }

    public Hospedagem findOneByIdDados(String id_dados) {
        Cursor cursor = null;
        Hospedagem hosp = null;

        try {
            Open();

            // Mudar para COLUNA_ID_DADOS
            String selection = Hospedagem.COLUNA_ID_DADOS;
            String[] selectionArgs = { id_dados };

            cursor = db.query(Hospedagem.TABLE_NAME, colunas, selection, selectionArgs, null, null, null);
            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Hospedagem.COLUNA_ID));
                int idDados = cursor.getInt(cursor.getColumnIndexOrThrow(Hospedagem.COLUNA_ID_DADOS));
                Float valor_diaria = cursor.getFloat(cursor.getColumnIndexOrThrow(Hospedagem.COLUNA_VALOR_DIARIA));
                Float total_noites = cursor.getFloat(cursor.getColumnIndexOrThrow(Hospedagem.COLUNA_TOTAL_NOITES));
                Float total_quartos = cursor.getFloat(cursor.getColumnIndexOrThrow(Hospedagem.COLUNA_TOTAL_QUARTOS));
                Float total = cursor.getFloat(cursor.getColumnIndexOrThrow(Hospedagem.COLUNA_TOTAL));

                hosp = new Hospedagem();

                hosp.setId(id);
                hosp.setId_dados(idDados);
                hosp.setValor_diaria(valor_diaria);
                hosp.setTotal_noites(total_noites);
                hosp.setTotal_quartos(total_quartos);
                hosp.setTotal(total);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }

            if (db != null) {
//                db.close();
            }
        }
        return hosp;
    }

    public List<Hospedagem> selectAll() {
        List<Hospedagem> hospedagems = new ArrayList<>();
        try {
            Open();

            Cursor cursor = db.query(Hospedagem.TABLE_NAME, colunas, null, null, null, null, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()){
                hospedagems.add(CursorToStructure(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        }
        finally {
            Close();
        }

        return (hospedagems);
    }

    public void delete(final long idHospedagem){
        Open();
        db.delete(Hospedagem.TABLE_NAME, Hospedagem.COLUNA_ID + " =?", new String[]{""+idHospedagem});
        Close();
    }

    public void deleteAll(){
        Open();
        db.delete(Hospedagem.TABLE_NAME, null, null);
        Close();
    }

    public final Hospedagem CursorToStructure(Cursor cursor){
        Hospedagem hospedagem = new Hospedagem();

        hospedagem.setId(cursor.getLong(0));
        hospedagem.setValor_diaria(cursor.getFloat(1));
        hospedagem.setTotal_noites(cursor.getFloat(2));
        hospedagem.setTotal_quartos(cursor.getFloat(3));
        hospedagem.setTotal(cursor.getFloat(4));

        return (hospedagem);
    }
}
