package com.unesc.itravel.database.model;

public class Entretenimento {
    public static final String TABLE_NAME = "entretenimento";
    public static final String
            COLUNA_ID = "_id",
            COLUNA_VALOR_ENTRETENIMENTO = "_valor_entretenimento",
            COLUNA_TOTAL = "_total";

    private long id;
    private Float valor_entretenimento;
    private Float total;

    public static final String CREATE_TABLE = "create table " + TABLE_NAME
            + "("
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_VALOR_ENTRETENIMENTO + " REAL not null, "
            + COLUNA_TOTAL + " REAL not null "
            + ");";

    public static final String DROP_TABLE = "drop table if exists " + TABLE_NAME +";";

    public Entretenimento() {
    }

    public Entretenimento(Float valor_entretenimento, Float total) {
        this.valor_entretenimento = valor_entretenimento;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getValor_entretenimento() {
        return valor_entretenimento;
    }

    public void setValor_entretenimento(Float valor_entretenimento) {
        this.valor_entretenimento = valor_entretenimento;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
