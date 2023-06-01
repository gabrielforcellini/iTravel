package com.unesc.itravel.database.model;

public class Refeicao {
    public static final String TABLE_NAME = "refeicao";
    public static final String
            COLUNA_ID = "_id",
            COLUNA_CUSTO_REFEICAO = "_custo_refeicao",
            COLUNA_REFEICAO_DIA = "_refeicao_dia",
            COLUNA_TOTAL = "_total";

    private long id;
    private Float custo_refeicao;
    private Float refeicao_dia;
    private Float total;

    public static final String CREATE_TABLE = "create table " + TABLE_NAME
            + "("
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_CUSTO_REFEICAO + " REAL not null, "
            + COLUNA_REFEICAO_DIA + " REAL not null, "
            + COLUNA_TOTAL + " REAL not null "
            + ");";

    public static final String DROP_TABLE = "drop table if exists " + TABLE_NAME +";";

    public Refeicao() {
    }

    public Refeicao(Float custo_refeicao, Float refeicao_dia, Float total) {
        this.custo_refeicao = custo_refeicao;
        this.refeicao_dia = refeicao_dia;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getCusto_refeicao() {
        return custo_refeicao;
    }

    public void setCusto_refeicao(Float custo_refeicao) {
        this.custo_refeicao = custo_refeicao;
    }

    public Float getRefeicao_dia() {
        return refeicao_dia;
    }

    public void setRefeicao_dia(Float refeicao_dia) {
        this.refeicao_dia = refeicao_dia;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
