package com.unesc.itravel.database.model;

public class Gasolina {
    public static final String TABLE_NAME = "gasolina";
    public static final String
            COLUNA_ID = "_id",
            COLUNA_ID_DADOS = "_idDados",
            COLUNA_TOTAL_KM = "_total_km",
            COLUNA_MEDIA_LITRO = "_media_litro",
            COLUNA_CUSTO_LITRO = "_custo_litro",
            COLUNA_TOTAL_VEIC = "_total_veic",
            COLUNA_TOTAL = "_total";

    private long id;
    private long id_dados;
    private Float total_km;
    private Float media_litro;
    private Float custo_litro;
    private Float total_veic;
    private Float total;

    public static final String CREATE_TABLE = "create table " + TABLE_NAME
            + "("
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_ID_DADOS + " integer not null, "
            + COLUNA_TOTAL_KM + " REAL not null, "
            + COLUNA_MEDIA_LITRO + " REAL not null, "
            + COLUNA_CUSTO_LITRO + " REAL not null, "
            + COLUNA_TOTAL_VEIC + " REAL not null, "
            + COLUNA_TOTAL + " REAL not null "
            + ");";

    public static final String DROP_TABLE = "drop table if exists " + TABLE_NAME +";";

    public Gasolina() {
    }

    public Gasolina(long id_dados, Float total_km, Float media_litro, Float custo_litro, Float total_veic, Float total) {
        this.id_dados = id_dados;
        this.total_km = total_km;
        this.media_litro = media_litro;
        this.custo_litro = custo_litro;
        this.total_veic = total_veic;
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

    public Float getTotal_km() {
        return total_km;
    }

    public void setTotal_km(Float total_km) {
        this.total_km = total_km;
    }

    public Float getMedia_litro() {
        return media_litro;
    }

    public void setMedia_litro(Float media_litro) {
        this.media_litro = media_litro;
    }

    public Float getCusto_litro() {
        return custo_litro;
    }

    public void setCusto_litro(Float custo_litro) {
        this.custo_litro = custo_litro;
    }

    public Float getTotal_veic() {
        return total_veic;
    }

    public void setTotal_veic(Float total_veic) {
        this.total_veic = total_veic;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
