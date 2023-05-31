package com.unesc.itravel.database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.unesc.itravel.database.DBOpenHelper;
import com.unesc.itravel.database.model.Login;

public class LoginDAO extends AbstrataDAO {

    private static final String[] colunas = {
            Login.COLUNA_ID,
            Login.COLUNA_NOME,
            Login.COLUNA_LOGIN,
            Login.COLUNA_SENHA
    };

    public LoginDAO(Context context) {
        db_helper = new DBOpenHelper(context);
    }

    public long insert(Login loginModel){
        long rowAffect = 0;
        try {
            Open();

            ContentValues contentValues = new ContentValues();
            contentValues.put(Login.COLUNA_ID, loginModel.getId());
            contentValues.put(Login.COLUNA_LOGIN, loginModel.getLogin());
            contentValues.put(Login.COLUNA_NOME, loginModel.getNome());
            contentValues.put(Login.COLUNA_SENHA, loginModel.getSenha());

            rowAffect = db.insert(Login.TABLE_NAME, null, contentValues);
        }
        finally {
            Close();
        }

        return rowAffect;
    }

    public Login findOne(String login) {
        Cursor cursor = null;
        Login log = null;
        try {
            Open();

            String selection = Login.COLUNA_LOGIN + " = ?";
            String[] selectionArgs = {login};

            cursor = db.query(Login.TABLE_NAME, colunas, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Login.COLUNA_ID));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(Login.COLUNA_NOME));
                String usuario = cursor.getString(cursor.getColumnIndexOrThrow(Login.COLUNA_LOGIN));
                String senha = cursor.getString(cursor.getColumnIndexOrThrow(Login.COLUNA_SENHA));

                log = new Login();

                log.setLogin(usuario);
                log.setSenha(senha);
                log.setId(id);
                log.setNome(nome);
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

        return log;
    }
}
