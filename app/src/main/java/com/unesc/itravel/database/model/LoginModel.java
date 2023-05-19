package com.unesc.itravel.database.model;

public class LoginModel {

    public static final String TABLE_NAME = "login";
    public static final String
            COLUNA_ID = "_id",
            COLUNA_NOME = "_nome",
            COLUNA_LOGIN = "_login",
            COLUNA_SENHA = "_senha";

    private long id;
    private String nome;
    private String login;
    private String senha;

    public static final String CREATE_TABLE = "create table " + TABLE_NAME
            + "("
            + COLUNA_ID + " integer primary key autoincrement,"
            + COLUNA_NOME + " text not null,"
            + COLUNA_LOGIN + " text not null,"
            + COLUNA_SENHA + " text not null"
            + ");";

    public static final String DROP_TABLE = "drop table if exists " + TABLE_NAME +";";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
