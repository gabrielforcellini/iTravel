package com.unesc.itravel.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

//    public static void getViagem(int id, Callback<Viagem> callback) {
//        ViagemEndPoint endPoint = retrofit.create(ViagemEndPoint.class);
//        Call<Viagem> call = endPoint.getViagem(id);
//        call.enqueue(callback);
//    }
//
//    public static void postViagem(ViagemPost viagem, Callback<Resposta> callback) {
//        ViagemEndPoint endPoint = retrofit.create(ViagemEndPoint.class);
//        Call<Resposta> call = endPoint.postViagem(viagem);
//        call.enqueue(callback);
//    }
}
