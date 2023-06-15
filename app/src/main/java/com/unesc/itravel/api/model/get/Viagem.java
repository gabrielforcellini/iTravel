package com.unesc.itravel.api.model.get;

import java.io.Serializable;
import java.util.ArrayList;

public class Viagem implements Serializable {
    private boolean sucesso;
    private ArrayList<ViagemLista> listaViagem;

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public ArrayList<ViagemLista> getListaViagem() {
        return listaViagem;
    }

    public void setListaViagem(ArrayList<ViagemLista> listaViagem) {
        this.listaViagem = listaViagem;
    }
}
