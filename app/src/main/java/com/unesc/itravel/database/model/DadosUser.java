package com.unesc.itravel.database.model;

public class DadosUser {
    public static final String TABLE_NAME = "dados";
    public static final String
            COLUNA_ID = "_id",
            COLUNA_ID_LOGIN = "_idLogin",

            COLUNA_VIAJANTES = "_viajantes",
            COLUNA_QTD_DIAS = "_qtd_dias";

    private long id;
    private long id_login;
    private Float viajantes;
    private Float qtd_dias;

    public static final String CREATE_TABLE = "create table " + TABLE_NAME
            + "("
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_ID_LOGIN + " integer not null, "
            + COLUNA_VIAJANTES + " REAL not null, "
            + COLUNA_QTD_DIAS + " REAL not null "
            + ");";

    public static final String DROP_TABLE = "drop table if exists " + TABLE_NAME +";";

    public DadosUser() {
    }

    public DadosUser(long id_login, Float viajantes, Float qtd_dias) {
        this.id_login = id_login;
        this.viajantes = viajantes;
        this.qtd_dias = qtd_dias;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_login() {
        return id_login;
    }

    public void setId_login(long id_login) {
        this.id_login = id_login;
    }

    public Float getViajantes() {
        return viajantes;
    }

    public void setViajantes(Float viajantes) {
        this.viajantes = viajantes;
    }

    public Float getQtd_dias() {
        return qtd_dias;
    }

    public void setQtd_dias(Float qtd_dias) {
        this.qtd_dias = qtd_dias;
    }
}
