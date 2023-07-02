package com.unesc.itravel.api.model.post;

import java.io.Serializable;

public class GasolinaPost implements Serializable {
    private long id;
    private long viagemId;
    private long totalEstimadoKM;
    private double mediaKMLitro;
    private double custoMedioLitro;
    private int totalVeiculos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getViagemId() {
        return viagemId;
    }

    public void setViagemId(long viagemId) {
        this.viagemId = viagemId;
    }

    public long getTotalEstimadoKM() {
        return totalEstimadoKM;
    }

    public void setTotalEstimadoKM(long totalEstimadoKM) {
        this.totalEstimadoKM = totalEstimadoKM;
    }

    public double getMediaKMLitro() {
        return mediaKMLitro;
    }

    public void setMediaKMLitro(double mediaKMLitro) {
        this.mediaKMLitro = mediaKMLitro;
    }

    public double getCustoMedioLitro() {
        return custoMedioLitro;
    }

    public void setCustoMedioLitro(double custoMedioLitro) {
        this.custoMedioLitro = custoMedioLitro;
    }

    public int getTotalVeiculos() {
        return totalVeiculos;
    }

    public void setTotalVeiculos(int totalVeiculos) {
        this.totalVeiculos = totalVeiculos;
    }
}
