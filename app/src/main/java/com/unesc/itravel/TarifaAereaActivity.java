package com.unesc.itravel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.unesc.itravel.api.Api;
import com.unesc.itravel.api.model.post.GasolinaPost;
import com.unesc.itravel.api.model.post.ViagemCustoAereoPost;
import com.unesc.itravel.api.model.post.result.Resposta;
import com.unesc.itravel.database.DAO.DadosUserDAO;
import com.unesc.itravel.database.DAO.GasolinaDAO;
import com.unesc.itravel.database.DAO.TarifaAereaDAO;
import com.unesc.itravel.database.model.DadosUser;
import com.unesc.itravel.database.model.Gasolina;
import com.unesc.itravel.database.model.TarifaAerea;

import java.util.Arrays;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TarifaAereaActivity extends AppCompatActivity {

    private Button btn_next;
    private Button btn_previous;
    private EditText edt_passagem;
    private EditText edt_alugel_carro;
    private EditText edt_total;

    SharedPreferences preferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifa_aerea);

        preferences = PreferenceManager.getDefaultSharedPreferences(TarifaAereaActivity.this);

        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_voltar);
        edt_passagem = findViewById(R.id.edt_passagem);
        edt_alugel_carro = findViewById(R.id.edt_alugel_carro);
        edt_total = findViewById(R.id.edt_total);

        List<EditText> campos = Arrays.asList(
                edt_passagem,
                edt_alugel_carro,
                edt_total
        );

        btn_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!Utils.validarCamposVazios(campos)) {
                    return;
                }
                try {
                    TarifaAereaDAO tarifaAereaDAO = new TarifaAereaDAO(TarifaAereaActivity.this);

                    float passagem = Float.parseFloat(edt_passagem.getText().toString());
                    float aluguelCarro = Float.parseFloat(edt_alugel_carro.getText().toString());
                    float total = Float.parseFloat(edt_total.getText().toString());
                    long id_dados = preferences.getLong("id_dados", 99);
                    if (id_dados == 99){
                        Toast.makeText(TarifaAereaActivity.this, "Voce precisa informar os dados da viagem.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    TarifaAerea tarifaAerea = new TarifaAerea(id_dados, passagem, aluguelCarro, total);
                    tarifaAereaDAO.insert(tarifaAerea);

                    ViagemCustoAereoPost custoAereoPost = new ViagemCustoAereoPost();
                    custoAereoPost.setId(19);
                    custoAereoPost.setCustoPessoa(total);
                    custoAereoPost.setCustoAluguelVeiculo(aluguelCarro);
                    custoAereoPost.setViagemId(id_dados);

                    SweetAlertDialog pDialog = new SweetAlertDialog(TarifaAereaActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Aguarde");
                    pDialog.setContentText("Enviando dados ao servidor ...");
                    pDialog.setCancelable(false);
                    pDialog.show();

                    Api.postViagemCustoAereo(custoAereoPost, new Callback<Resposta>() {
                        @Override
                        public void onResponse(Call<Resposta> call, Response<Resposta> response) {
                            if (response != null && response.isSuccessful()) {
                                pDialog.cancel();
                                Resposta resposta = response.body();
                                System.out.println("*********");
                                Toast.makeText(TarifaAereaActivity.this, "Custos aereos gravados com sucesso na API.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Resposta> call, Throwable t) {
                            pDialog.cancel();
                            Toast.makeText(TarifaAereaActivity.this, "Erro ao gravar dados na API.", Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }
                    });

                    Toast.makeText(TarifaAereaActivity.this, "Dados gravados com sucesso.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(TarifaAereaActivity.this, RefeicoesActivity.class));
                } catch (Exception e) {
                    Toast.makeText(TarifaAereaActivity.this, "Erro ao gravar os dados.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Utils.validarCamposVazios(campos)) {
                    return;
                }
                startActivity(new Intent(TarifaAereaActivity.this, Dados1Activity.class));
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

        edt_passagem.addTextChangedListener(textWatcher);
        edt_alugel_carro.addTextChangedListener(textWatcher);
    }

    private void calcularValorTotal() {
        if (TextUtils.isEmpty(edt_passagem.getText().toString()) || TextUtils.isEmpty(edt_alugel_carro.getText().toString())) {
            return;
        }
        DadosUserDAO dadosUserDAO = new DadosUserDAO(TarifaAereaActivity.this);

        long id_dados = preferences.getLong("id_dados", 99);
        String idDados = String.valueOf(id_dados);
        DadosUser dadosUser = dadosUserDAO.findOne(idDados);

        double valorPassagem = Double.parseDouble(edt_passagem.getText().toString());
        double valorAluguelCarro = Double.parseDouble(edt_alugel_carro.getText().toString());

        double resultado = ((valorPassagem * dadosUser.getViajantes()) + valorAluguelCarro);

        edt_total.setText(String.valueOf(resultado));
    }
}
