package com.unesc.itravel.database.model;

public class DadosUser {
    public static final String TABLE_NAME = "dados";
    public static final String
            COLUNA_ID = "_id",
            COLUNA_VIAJANTES = "_viajantes",
            COLUNA_QTD_DIAS = "_qtd_dias";

    private long id;
    private int viajantes;
    private int qtd_dias;

    public static final String CREATE_TABLE = "create table " + TABLE_NAME
            + "("
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_VIAJANTES + " integer not null, "
            + COLUNA_QTD_DIAS + " integer not null "
            + ");";

    public static final String DROP_TABLE = "drop table if exists " + TABLE_NAME +";";

    public DadosUser() {
    }

    public DadosUser(int viajantes, int qtd_dias) {
        this.viajantes = viajantes;
        this.qtd_dias = qtd_dias;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getViajantes() {
        return viajantes;
    }

    public void setViajantes(int viajantes) {
        this.viajantes = viajantes;
    }

    public int getQtd_dias() {
        return qtd_dias;
    }

    public void setQtd_dias(int qtd_dias) {
        this.qtd_dias = qtd_dias;
    }
}
