package com.unesc.itravel.api.model.post;

import java.io.Serializable;

public class ViagemPost implements Serializable {

    private int id;
    private int totalViajantes;
    private int duracaoViagem;
    private double custoTotalViagem;
    private double custoPorPessoa;

    private String local;

    private int idConta;

    public int getTotalViajantes() {
        return totalViajantes;
    }

    public void setTotalViajantes(int totalViajantes) {
        this.totalViajantes = totalViajantes;
    }

    public int getDuracaoViagem() {
        return duracaoViagem;
    }

    public void setDuracaoViagem(int duracaoViagem) {
        this.duracaoViagem = duracaoViagem;
    }

    public double getCustoTotalViagem() {
        return custoTotalViagem;
    }

    public void setCustoTotalViagem(double custoTotalViagem) {
        this.custoTotalViagem = custoTotalViagem;
    }

    public double getCustoPorPessoa() {
        return custoPorPessoa;
    }

    public void setCustoPorPessoa(double custoPorPessoa) {
        this.custoPorPessoa = custoPorPessoa;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
