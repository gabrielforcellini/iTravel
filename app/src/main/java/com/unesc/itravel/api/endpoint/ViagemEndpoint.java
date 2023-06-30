package com.unesc.itravel.api.endpoint;

import com.unesc.itravel.api.model.get.Viagem;
import com.unesc.itravel.api.model.get.ViagemCustoGasolina;
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
    Call<Resposta> postCustoGasolina(@Body ViagemCustoGasolina viagemCustoGasolina);
}

//Exemplo de como usar alerta
//    SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
//                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//                        pDialog.setTitleText("Aguarde");
//                        pDialog.setContentText("Enviando dados ao servidor ...");
//                        pDialog.setCancelable(false);
//                        pDialog.show();
//
//
//                        pDialog.cancel();