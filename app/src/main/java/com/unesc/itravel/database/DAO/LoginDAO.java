package com.unesc.itravel.database.DAO;

import android.content.ContentValues;
import android.content.Context;

import com.unesc.itravel.database.DBOpenHelper;
import com.unesc.itravel.database.model.LoginModel;

public class LoginDAO extends AbstrataDAO {

    private static final String[] colunas = {
            LoginModel.COLUNA_ID,
            LoginModel.COLUNA_NOME,
            LoginModel.COLUNA_LOGIN,
            LoginModel.COLUNA_SENHA
    };

    public LoginDAO(Context context) {
        db_helper = new DBOpenHelper(context);
    }

    public long insert(LoginModel loginModel){
        long rowAffect = 0;
        try {
            Open();

            ContentValues contentValues = new ContentValues();
            contentValues.put(LoginModel.COLUNA_ID, loginModel.getId());
            contentValues.put(LoginModel.COLUNA_LOGIN, loginModel.getLogin());
            contentValues.put(LoginModel.COLUNA_NOME, loginModel.getNome());
            contentValues.put(LoginModel.COLUNA_SENHA, loginModel.getSenha());

            rowAffect = db.insert(LoginModel.TABLE_NAME, null, contentValues);
        }
        finally {
            Close();
        }

        return rowAffect;
    }
}
