package com.unesc.itravel.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unesc.itravel.api.endpoint.ViagemEndpoint;
import com.unesc.itravel.api.model.get.Viagem;
import com.unesc.itravel.api.model.get.ViagemCustoAereo;
import com.unesc.itravel.api.model.post.GasolinaPost;
import com.unesc.itravel.api.model.post.ViagemCustoAereoPost;
import com.unesc.itravel.api.model.post.ViagemCustoEntretenimentoPost;
import com.unesc.itravel.api.model.post.ViagemCustoHospedagemPost;
import com.unesc.itravel.api.model.post.ViagemCustoRefeicaoPost;
import com.unesc.itravel.api.model.post.ViagemPost;
import com.unesc.itravel.api.model.post.result.Resposta;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    public static final String BASE_URL = "http://api.nossosmedicos.com.br/";

    private static final Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public static void getViagem(int id, Callback<Viagem> callback) {
        ViagemEndpoint endPoint = retrofit.create(ViagemEndpoint.class);
        Call<Viagem> call = endPoint.getViagem(id);
        call.enqueue(callback);
    }

    public static void postViagem(ViagemPost viagem, Callback<Resposta> callback) {
        ViagemEndpoint endPoint = retrofit.create(ViagemEndpoint.class);
        Call<Resposta> call = endPoint.postViagem(viagem);
        call.enqueue(callback);
    }

    public static void postCustoGasolina(GasolinaPost gasolina, Callback<Resposta> callback) {
        ViagemEndpoint endpoint = retrofit.create(ViagemEndpoint.class);
        Call<Resposta> call = endpoint.postCustoGasolina(gasolina);
        call.enqueue(callback);
    }

    public static void postViagemCustoAereo(ViagemCustoAereoPost custoAereo, Callback<Resposta> callback) {
        ViagemEndpoint endPoint = retrofit.create(ViagemEndpoint.class);
        Call<Resposta> call = endPoint.postCustoAereo(custoAereo);
        call.enqueue(callback);
    }

    public static void postViagemCustoRefeicao(ViagemCustoRefeicaoPost custoRefeicao, Callback<Resposta> callback) {
        ViagemEndpoint endPoint = retrofit.create(ViagemEndpoint.class);
        Call<Resposta> call = endPoint.postCustoRefeicao(custoRefeicao);
        call.enqueue(callback);
    }

    public static void postViagemCustoHospedagem(ViagemCustoHospedagemPost custoHospedagem, Callback<Resposta> callback) {
        ViagemEndpoint endPoint = retrofit.create(ViagemEndpoint.class);
        Call<Resposta> call = endPoint.postCustoHospedagem(custoHospedagem);
        call.enqueue(callback);
    }
    public static void postViagemCustoEntretenimento(ViagemCustoEntretenimentoPost custoEntretenimento, Callback<Resposta> callback) {
        ViagemEndpoint endPoint = retrofit.create(ViagemEndpoint.class);
        Call<Resposta> call = endPoint.postCustoEntretenimento(custoEntretenimento);
        call.enqueue(callback);
    }
}
