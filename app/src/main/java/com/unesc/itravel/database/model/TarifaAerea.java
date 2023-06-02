package com.unesc.itravel.database.model;

public class TarifaAerea {
    public static final String TABLE_NAME = "tarifa_aerea";
    public static final String
            COLUNA_ID = "_id",
            COLUNA_ID_DADOS = "_idDados",
            COLUNA_PASSAGEM = "_passagem",
            COLUNA_ALUGUEL_CARRO = "_aluguel_carro",
            COLUNA_TOTAL = "_total";

    private long id;
    private long id_dados;
    private Float passagem;
    private Float aluguel_carro;
    private Float total;

    public static final String CREATE_TABLE = "create table " + TABLE_NAME
            + "("
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_ID_DADOS + " integer not null, "
            + COLUNA_PASSAGEM + " REAL not null, "
            + COLUNA_ALUGUEL_CARRO + " REAL not null, "
            + COLUNA_TOTAL + " REAL not null "
            + ");";

    public static final String DROP_TABLE = "drop table if exists " + TABLE_NAME +";";

    public TarifaAerea() {
    }

    public TarifaAerea(long id_dados, Float passagem, Float aluguel_carro, Float total) {
        this.id_dados = id_dados;
        this.passagem = passagem;
        this.aluguel_carro = aluguel_carro;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_dados() {
        return id_dados;
    }

    public void setId_dados(long id_dados) {
        this.id_dados = id_dados;
    }

    public Float getPassagem() {
        return passagem;
    }

    public void setPassagem(Float passagem) {
        this.passagem = passagem;
    }

    public Float getAluguel_carro() {
        return aluguel_carro;
    }

    public void setAluguel_carro(Float aluguel_carro) {
        this.aluguel_carro = aluguel_carro;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
