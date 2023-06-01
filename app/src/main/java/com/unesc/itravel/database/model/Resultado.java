package com.unesc.itravel.database.model;

public class Resultado {
    public static final String TABLE_NAME = "resultados";
    public static final String
            COLUNA_ID = "_id",
            COLUNA_TOTAL = "_total",
            COLUNA_TOTAL_PESSOA = "_total_pessoa";

    private long id;
    private Float total;
    private Float total_pessoa;

    public static final String CREATE_TABLE = "create table " + TABLE_NAME
            + "("
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_TOTAL + " REAL not null, "
            + COLUNA_TOTAL_PESSOA + " REAL not null "
            + ");";

    public static final String DROP_TABLE = "drop table if exists " + TABLE_NAME +";";

    public Resultado() {
    }

    public Resultado(Float total, Float total_pessoa) {
        this.total = total;
        this.total_pessoa = total_pessoa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getTotal_pessoa() {
        return total_pessoa;
    }

    public void setTotal_pessoa(Float total_pessoa) {
        this.total_pessoa = total_pessoa;
    }
}
