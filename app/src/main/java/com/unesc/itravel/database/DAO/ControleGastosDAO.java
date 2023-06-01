package com.unesc.itravel.database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.unesc.itravel.database.DBOpenHelper;
import com.unesc.itravel.database.model.ControleGastos;
import com.unesc.itravel.database.model.Gasolina;

import java.util.ArrayList;
import java.util.List;

public class ControleGastosDAO extends AbstrataDAO{
    private static final String[] colunas = {
            ControleGastos.COLUNA_ID,
            ControleGastos.COLUNA_BOX_GASOLINA,
            ControleGastos.COLUNA_BOX_TARIFA,
            ControleGastos.COLUNA_BOX_REFEICAO,
            ControleGastos.COLUNA_BOX_HOSPEDAGEM,
            ControleGastos.COLUNA_BOX_ENTRETENIMENTO
    };

    public ControleGastosDAO(Context context) {
        db_helper = new DBOpenHelper(context);
    }

    public long insert(ControleGastos controleGastosModel){
        long rowAffect = 0;
        try {
            Open();

            ContentValues contentValues = new ContentValues();
            contentValues.put(ControleGastos.COLUNA_BOX_GASOLINA, controleGastosModel.isBox_gasolina());
            contentValues.put(ControleGastos.COLUNA_BOX_TARIFA, controleGastosModel.isBox_tarifa());
            contentValues.put(ControleGastos.COLUNA_BOX_REFEICAO, controleGastosModel.isBox_refeicao());
            contentValues.put(ControleGastos.COLUNA_BOX_HOSPEDAGEM, controleGastosModel.isBox_hospedagem());
            contentValues.put(ControleGastos.COLUNA_BOX_ENTRETENIMENTO, controleGastosModel.isBox_entretenimento());

            rowAffect = db.insert(ControleGastos.TABLE_NAME, null, contentValues);
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
