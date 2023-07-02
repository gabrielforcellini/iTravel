package com.unesc.itravel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.unesc.itravel.Utils;
import com.unesc.itravel.api.Api;
import com.unesc.itravel.api.model.post.GasolinaPost;
import com.unesc.itravel.api.model.post.ViagemPost;
import com.unesc.itravel.api.model.post.result.Resposta;
import com.unesc.itravel.database.DAO.GasolinaDAO;
import com.unesc.itravel.database.DAO.LoginDAO;
import com.unesc.itravel.database.model.Gasolina;
import com.unesc.itravel.database.model.Login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GasolinaActivity extends AppCompatActivity {

    private Button btn_next;

    private Button btn_previous;

    private EditText edt_km;
    private EditText edt_media_litro;
    private EditText edt_custo_litro;
    private EditText edt_total;
    private EditText edt_total_veic;
    private GasolinaDAO gasolinaDAO;
    private Float total_km;
    private Float media_litro;
    private Float custo_litro;
    private Float total_veic;
    private Float total;
    SharedPreferences preferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasolina);

        preferences = PreferenceManager.getDefaultSharedPreferences(GasolinaActivity.this);

        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_voltar);
        edt_km = findViewById(R.id.edt_km);
        edt_media_litro = findViewById(R.id.edt_media_litro);
        edt_custo_litro = findViewById(R.id.edt_custo_litro);
        edt_total_veic = findViewById(R.id.edt_total_veic);
        edt_total = findViewById(R.id.edt_total);

        List<EditText> campos = Arrays.asList(
                edt_km,
                edt_media_litro,
                edt_custo_litro,
                edt_total_veic
        );

        btn_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!Utils.validarCamposVazios(campos)) {
                    return;
                }
                try {
                    gasolinaDAO = new GasolinaDAO(GasolinaActivity.this);

                    total_km = Float.parseFloat(edt_km.getText().toString());
                    media_litro = Float.parseFloat(edt_media_litro.getText().toString());
                    custo_litro = Float.parseFloat(edt_custo_litro.getText().toString());
                    total_veic = Float.parseFloat(edt_total_veic.getText().toString());
                    total = Float.parseFloat(edt_total.getText().toString());

                    long id_dados = preferences.getLong("id_dados", 99);
                    if (id_dados == 99){
                        Toast.makeText(GasolinaActivity.this, "Voce precisa informar os dados da viagem.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Gasolina gasolina = new Gasolina(id_dados, total_km, media_litro, custo_litro, total_veic, total);
                    gasolinaDAO.insert(gasolina);

                    GasolinaPost gasolinaPost = new GasolinaPost();
                    gasolinaPost.setId(19);
                    gasolinaPost.setCustoMedioLitro(custo_litro);
                    gasolinaPost.setMediaKMLitro(media_litro);
                    gasolinaPost.setTotalEstimadoKM(Math.round(total_km)); // utilizado math.round pois na API recebe long
                    gasolinaPost.setTotalVeiculos(Math.round(total_veic));
                    gasolinaPost.setViagemId(id_dados);

                    SweetAlertDialog pDialog = new SweetAlertDialog(GasolinaActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Aguarde");
                    pDialog.setContentText("Enviando dados ao servidor ...");
                    pDialog.setCancelable(false);
                    pDialog.show();

                    Api.postCustoGasolina(gasolinaPost, new Callback<Resposta>() {
                        @Override
                        public void onResponse(Call<Resposta> call, Response<Resposta> response) {
                            if (response != null && response.isSuccessful()) {
                                pDialog.cancel();
                                Resposta resposta = response.body();
                                System.out.println("*********");
                                Toast.makeText(GasolinaActivity.this, "Custos gasolina gravados com sucesso na API.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Resposta> call, Throwable t) {
                            pDialog.cancel();
                            Toast.makeText(GasolinaActivity.this, "Erro ao gravar dados na API.", Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }
                    });

                    Snackbar.make(findViewById(android.R.id.content), "salvo.", Snackbar.LENGTH_SHORT).show();
                    startActivity(new Intent(GasolinaActivity.this, RefeicoesActivity.class));
                } catch (Exception e) {
                    Snackbar.make(findViewById(android.R.id.content), "Erro: "+e.toString(), Snackbar.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Utils.validarCamposVazios(campos)) {
                    return;
                }
                startActivity(new Intent(GasolinaActivity.this, Dados1Activity.class));
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Sem utilidade, porém necessário
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularValorTotal();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Sem utilidade, porém necessário
            }
        };

        edt_km.addTextChangedListener(textWatcher);
        edt_media_litro.addTextChangedListener(textWatcher);
        edt_custo_litro.addTextChangedListener(textWatcher);
        edt_total_veic.addTextChangedListener(textWatcher);

    }

    private void calcularValorTotal() {
        if (TextUtils.isEmpty(edt_km.getText().toString())
                || TextUtils.isEmpty(edt_media_litro.getText().toString())
                || TextUtils.isEmpty(edt_custo_litro.getText().toString())
                || TextUtils.isEmpty(edt_total_veic.getText().toString())) {
            return;
        }

        double totalKm = Double.parseDouble(edt_km.getText().toString());
        double mediaKm = Double.parseDouble(edt_media_litro.getText().toString());
        double custoLitro = Double.parseDouble(edt_custo_litro.getText().toString());
        int totalVeiculos = Integer.parseInt(edt_total_veic.getText().toString());

        double resultado = ((totalKm / mediaKm) * custoLitro) / totalVeiculos;

        edt_total.setText(String.valueOf(resultado));
    }
}
