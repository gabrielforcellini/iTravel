package com.unesc.itravel.api.endpoint;

import com.unesc.itravel.api.model.get.Viagem;
import com.unesc.itravel.api.model.get.ViagemCustoGasolina;
import com.unesc.itravel.api.model.post.GasolinaPost;
import com.unesc.itravel.api.model.post.ViagemCustoAereoPost;
import com.unesc.itravel.api.model.post.ViagemCustoEntretenimentoPost;
import com.unesc.itravel.api.model.post.ViagemCustoHospedagemPost;
import com.unesc.itravel.api.model.post.ViagemCustoRefeicaoPost;
import com.unesc.itravel.api.model.post.ViagemPost;
import com.unesc.itravel.api.model.post.result.Resposta;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ViagemEndpoint {
    @GET("api/viagem/{id}")
    Call<Viagem> getViagem(@Path("id") int id);

    @POST("api/viagem/inserir")
    Call<Resposta> postViagem(@Body ViagemPost viagem);

    @POST("api/viagem/custo/gasolina")
    Call<Resposta> postCustoGasolina(@Body GasolinaPost viagemCustoGasolina);

    @POST("api/viagem/custo/aereo")
    Call<Resposta> postCustoAereo(@Body ViagemCustoAereoPost viagemCustoAereoPost);

    @POST("api/viagem/custo/refeicao")
    Call<Resposta> postCustoRefeicao(@Body ViagemCustoRefeicaoPost viagemCustoRefeicaoPost);

    @POST("api/viagem/custo/hospedagem")
    Call<Resposta> postCustoHospedagem(@Body ViagemCustoHospedagemPost viagemCustoHospedagemPost);
    @POST("api/viagem/custo/entretenimento")
    Call<Resposta> postCustoEntretenimento(@Body ViagemCustoEntretenimentoPost viagemCustoEntretenimentoPost);
}