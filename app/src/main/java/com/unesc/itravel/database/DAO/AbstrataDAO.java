package com.unesc.itravel.database.DAO;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.unesc.itravel.database.DBOpenHelper;

public abstract class AbstrataDAO {

    protected SQLiteDatabase db;
    protected DBOpenHelper db_helper;

    protected final void Open() throws SQLException{
        //Recebe instancia do banco
        db = db_helper.getWritableDatabase();
    }

    protected final void Close() throws SQLException{
        //Fecha conexao com o banco
        db_helper.close();
    }
}
