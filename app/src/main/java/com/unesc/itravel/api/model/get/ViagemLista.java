package com.unesc.itravel.api.model.get;

import java.io.Serializable;
import java.util.ArrayList;

public class ViagemLista implements Serializable {

    private int id;
    private int totalViajantes;
    private int duracaoViagem;
    private double custoTotalViagem;
    private double custoPorPessoa;
    private String local;
    private String dt_inc;
    private int usuario;
    private int idConta;
    private ArrayList<ViagemCustoGasolina> listaViagemCustoGasolina;
    private ArrayList<ViagemCustoAereo> listaViagemCustoAereo;
    private ArrayList<ViagemCustoEntretenimento> listaViagemCustoEntretenimento;
    private ArrayList<ViagemCustoHospedagem> viagemCustoHospedagem;
    private ArrayList<ViagemCustoRefeicao> viagemCustoRefeicao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getDt_inc() {
        return dt_inc;
    }

    public void setDt_inc(String dt_inc) {
        this.dt_inc = dt_inc;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public ArrayList<ViagemCustoGasolina> getListaViagemCustoGasolina() {
        return listaViagemCustoGasolina;
    }

    public void setListaViagemCustoGasolina(ArrayList<ViagemCustoGasolina> listaViagemCustoGasolina) {
        this.listaViagemCustoGasolina = listaViagemCustoGasolina;
    }

    public ArrayList<ViagemCustoAereo> getListaViagemCustoAereo() {
        return listaViagemCustoAereo;
    }

    public void setListaViagemCustoAereo(ArrayList<ViagemCustoAereo> listaViagemCustoAereo) {
        this.listaViagemCustoAereo = listaViagemCustoAereo;
    }

    public ArrayList<ViagemCustoEntretenimento> getListaViagemCustoEntretenimento() {
        return listaViagemCustoEntretenimento;
    }

    public void setListaViagemCustoEntretenimento(ArrayList<ViagemCustoEntretenimento> listaViagemCustoEntretenimento) {
        this.listaViagemCustoEntretenimento = listaViagemCustoEntretenimento;
    }

    public ArrayList<ViagemCustoHospedagem> getViagemCustoHospedagem() {
        return viagemCustoHospedagem;
    }

    public void setViagemCustoHospedagem(ArrayList<ViagemCustoHospedagem> viagemCustoHospedagem) {
        this.viagemCustoHospedagem = viagemCustoHospedagem;
    }

    public ArrayList<ViagemCustoRefeicao> getViagemCustoRefeicao() {
        return viagemCustoRefeicao;
    }

    public void setViagemCustoRefeicao(ArrayList<ViagemCustoRefeicao> viagemCustoRefeicao) {
        this.viagemCustoRefeicao = viagemCustoRefeicao;
    }
}
