package com.unesc.itravel.database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.unesc.itravel.database.DBOpenHelper;
import com.unesc.itravel.database.model.Login;

import java.util.ArrayList;
import java.util.List;

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
            contentValues.put(Login.COLUNA_NOME, loginModel.getNome());
            contentValues.put(Login.COLUNA_LOGIN, loginModel.getLogin());
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

            String selection = Login.COLUNA_LOGIN + "=?";
            String[] selectionArgs = {login};

            cursor = db.query(Login.TABLE_NAME, colunas, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Login.COLUNA_ID));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(Login.COLUNA_NOME));
                String usuario = cursor.getString(cursor.getColumnIndexOrThrow(Login.COLUNA_LOGIN));
                String senha = cursor.getString(cursor.getColumnIndexOrThrow(Login.COLUNA_SENHA));

                log = new Login();

                log.setId(id);
                log.setNome(nome);
                log.setLogin(usuario);
                log.setSenha(senha);

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

    public List<Login> selectAll() {
        List<Login> login = new ArrayList<>();
        try {
            Open();

            Cursor cursor = db.query(Login.TABLE_NAME, colunas, null, null, null, null, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()){
                login.add(CursorToStructure(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        }
        finally {
          Close();
        }

        return (login);
    }

    public void delete(final long idLogin){
        Open();
        db.delete(Login.TABLE_NAME, Login.COLUNA_ID + " =?", new String[]{""+idLogin});
        Close();
    }

    public void deleteAll(){
        Open();
        db.delete(Login.TABLE_NAME, null, null);
        Close();
    }

    public final Login CursorToStructure(Cursor cursor){
        Login login = new Login();

        login.setId(cursor.getLong(0));
        login.setNome(cursor.getString(1));
        login.setLogin(cursor.getString(2));
        login.setSenha(cursor.getString(3));

        return (login);
    }
}
