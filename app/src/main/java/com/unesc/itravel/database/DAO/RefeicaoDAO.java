package com.unesc.itravel.database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.unesc.itravel.database.DBOpenHelper;
import com.unesc.itravel.database.model.Refeicao;

import java.util.ArrayList;
import java.util.List;

public class RefeicaoDAO extends AbstrataDAO{
    private static final String[] colunas = {
            Refeicao.COLUNA_ID,
            Refeicao.COLUNA_ID_DADOS,
            Refeicao.COLUNA_CUSTO_REFEICAO,
            Refeicao.COLUNA_REFEICAO_DIA,
            Refeicao.COLUNA_TOTAL
    };

    public RefeicaoDAO(Context context) {
        db_helper = new DBOpenHelper(context);
    }

    public long insert(Refeicao refeicaoModel){
        long rowAffect = 0;
        try {
            Open();

            ContentValues contentValues = new ContentValues();
            contentValues.put(Refeicao.COLUNA_ID_DADOS, refeicaoModel.getId_dados());
            contentValues.put(Refeicao.COLUNA_CUSTO_REFEICAO, refeicaoModel.getCusto_refeicao());
            contentValues.put(Refeicao.COLUNA_REFEICAO_DIA, refeicaoModel.getRefeicao_dia());
            contentValues.put(Refeicao.COLUNA_TOTAL, refeicaoModel.getTotal());

            rowAffect = db.insert(Refeicao.TABLE_NAME, null, contentValues);
        }
        finally {
            Close();
        }

        return rowAffect;
    }

    public Refeicao findOne(String refeicao) {
        Cursor cursor = null;
        Refeicao ref = null;
        try {
            Open();

            String selection = Refeicao.COLUNA_ID + "=?";
            String[] selectionArgs = {refeicao};

            cursor = db.query(Refeicao.TABLE_NAME, colunas, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Refeicao.COLUNA_ID));
                Float custo = cursor.getFloat(cursor.getColumnIndexOrThrow(Refeicao.COLUNA_CUSTO_REFEICAO));
                Float refeicao_diaria = cursor.getFloat(cursor.getColumnIndexOrThrow(Refeicao.COLUNA_REFEICAO_DIA));
                Float total = cursor.getFloat(cursor.getColumnIndexOrThrow(Refeicao.COLUNA_TOTAL));

                ref = new Refeicao();

                ref.setId(id);
                ref.setCusto_refeicao(custo);
                ref.setRefeicao_dia(refeicao_diaria);
                ref.setTotal(total);

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

        return ref;
    }

    public Refeicao findOneByIdDados(String id_dados) {
        Cursor cursor = null;
        Refeicao ref = null;
        try {
            Open();

            // Mudar para COLUNA_ID_DADOS
            String selection = Refeicao.COLUNA_ID_DADOS + "=?";
            String[] selectionArgs = { id_dados };

            cursor = db.query(Refeicao.TABLE_NAME, colunas, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Refeicao.COLUNA_ID));
                int idDados = cursor.getInt(cursor.getColumnIndexOrThrow(Refeicao.COLUNA_ID_DADOS));
                Float custo = cursor.getFloat(cursor.getColumnIndexOrThrow(Refeicao.COLUNA_CUSTO_REFEICAO));
                Float refeicao_diaria = cursor.getFloat(cursor.getColumnIndexOrThrow(Refeicao.COLUNA_REFEICAO_DIA));
                Float total = cursor.getFloat(cursor.getColumnIndexOrThrow(Refeicao.COLUNA_TOTAL));

                ref = new Refeicao();

                ref.setId(id);
                ref.setId_dados(idDados);
                ref.setCusto_refeicao(custo);
                ref.setRefeicao_dia(refeicao_diaria);
                ref.setTotal(total);

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

        return ref;
    }

    public List<Refeicao> selectAll() {
        List<Refeicao> refeicoes = new ArrayList<>();
        try {
            Open();

            Cursor cursor = db.query(Refeicao.TABLE_NAME, colunas, null, null, null, null, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()){
                refeicoes.add(CursorToStructure(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        }
        finally {
            Close();
        }

        return (refeicoes);
    }

    public void delete(final long idRefeicao){
        Open();
        db.delete(Refeicao.TABLE_NAME, Refeicao.COLUNA_ID + " =?", new String[]{""+idRefeicao});
        Close();
    }

    public void deleteAll(){
        Open();
        db.delete(Refeicao.TABLE_NAME, null, null);
        Close();
    }

    public final Refeicao CursorToStructure(Cursor cursor){
        Refeicao refeicao = new Refeicao();

        refeicao.setId(cursor.getLong(0));
        refeicao.setCusto_refeicao(cursor.getFloat(1));
        refeicao.setRefeicao_dia(cursor.getFloat(2));
        refeicao.setTotal(cursor.getFloat(3));

        return (refeicao);
    }
}
