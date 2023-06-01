package com.unesc.itravel.database.model;

public class Hospedagem {
    public static final String TABLE_NAME = "hospedagem";
    public static final String
            COLUNA_ID = "_id",
            COLUNA_VALOR_DIARIA = "_valor_diaria",
            COLUNA_TOTAL_NOITES = "_total_noites",
            COLUNA_TOTAL_QUARTOS = "_total_quartos",
            COLUNA_TOTAL = "_total";

    private long id;
    private Float valor_diaria;
    private Float total_noites;
    private Float total_quartos;
    private Float total;

    public static final String CREATE_TABLE = "create table " + TABLE_NAME
            + "("
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_VALOR_DIARIA + " REAL not null, "
            + COLUNA_TOTAL_NOITES + " REAL not null, "
            + COLUNA_TOTAL_QUARTOS + " REAL not null, "
            + COLUNA_TOTAL + " REAL not null "
            + ");";

    public static final String DROP_TABLE = "drop table if exists " + TABLE_NAME +";";

    public Hospedagem() {
    }

    public Hospedagem(Float valor_diaria, Float total_noites, Float total_quartos, Float total) {
        this.valor_diaria = valor_diaria;
        this.total_noites = total_noites;
        this.total_quartos = total_quartos;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getValor_diaria() {
        return valor_diaria;
    }

    public void setValor_diaria(Float valor_diaria) {
        this.valor_diaria = valor_diaria;
    }

    public Float getTotal_noites() {
        return total_noites;
    }

    public void setTotal_noites(Float total_noites) {
        this.total_noites = total_noites;
    }

    public Float getTotal_quartos() {
        return total_quartos;
    }

    public void setTotal_quartos(Float total_quartos) {
        this.total_quartos = total_quartos;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
