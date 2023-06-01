package com.unesc.itravel.database.model;

public class ControleGastos {
    public static final String TABLE_NAME = "controle_gastos";
    public static final String
            COLUNA_ID = "_id",
            COLUNA_ID_DADOS = "_idDados",
            COLUNA_BOX_GASOLINA = "_box_gasolina",
            COLUNA_BOX_TARIFA = "_box_tarifa",
            COLUNA_BOX_REFEICAO = "_box_refeicao",
            COLUNA_BOX_HOSPEDAGEM = "_box_hospedagem",
            COLUNA_BOX_ENTRETENIMENTO = "_box_entretenimento";

    private long id;
    private long id_dados;
    private boolean box_gasolina;
    private boolean box_tarifa;
    private boolean box_refeicao;
    private boolean box_hospedagem;
    private boolean box_entretenimento;

    public static final String CREATE_TABLE = "create table " + TABLE_NAME
            + "("
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_ID_DADOS + " integer not null, "
            + COLUNA_BOX_GASOLINA + " boolean not null, "
            + COLUNA_BOX_TARIFA + " boolean not null, "
            + COLUNA_BOX_REFEICAO + " boolean not null, "
            + COLUNA_BOX_HOSPEDAGEM + " boolean not null, "
            + COLUNA_BOX_ENTRETENIMENTO + " boolean not null "
            + ");";

    public static final String DROP_TABLE = "drop table if exists " + TABLE_NAME +";";

    public ControleGastos() {
    }

    public ControleGastos(long id_dados, boolean box_gasolina, boolean box_tarifa, boolean box_refeicao, boolean box_hospedagem, boolean box_entretenimento) {
        this.id_dados = id_dados;
        this.box_gasolina = box_gasolina;
        this.box_tarifa = box_tarifa;
        this.box_refeicao = box_refeicao;
        this.box_hospedagem = box_hospedagem;
        this.box_entretenimento = box_entretenimento;
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

    public boolean isBox_gasolina() {
        return box_gasolina;
    }

    public void setBox_gasolina(boolean box_gasolina) {
        this.box_gasolina = box_gasolina;
    }

    public boolean isBox_tarifa() {
        return box_tarifa;
    }

    public void setBox_tarifa(boolean box_tarifa) {
        this.box_tarifa = box_tarifa;
    }

    public boolean isBox_refeicao() {
        return box_refeicao;
    }

    public void setBox_refeicao(boolean box_refeicao) {
        this.box_refeicao = box_refeicao;
    }

    public boolean isBox_hospedagem() {
        return box_hospedagem;
    }

    public void setBox_hospedagem(boolean box_hospedagem) {
        this.box_hospedagem = box_hospedagem;
    }

    public boolean isBox_entretenimento() {
        return box_entretenimento;
    }

    public void setBox_entretenimento(boolean box_entretenimento) {
        this.box_entretenimento = box_entretenimento;
    }
}
