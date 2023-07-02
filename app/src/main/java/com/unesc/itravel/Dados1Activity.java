package com.unesc.itravel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.unesc.itravel.api.Api;
import com.unesc.itravel.api.model.post.ViagemPost;
import com.unesc.itravel.api.model.post.result.Resposta;
import com.unesc.itravel.database.DAO.DadosUserDAO;
import com.unesc.itravel.database.model.DadosUser;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dados1Activity extends AppCompatActivity {

    private Button btn_next;

    private RadioGroup radioGroup;

    public EditText edt_viajantes;

    public EditText edt_qtd_dias;

    SharedPreferences preferences;
    SharedPreferences.Editor edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_dados_1);

        preferences = PreferenceManager.getDefaultSharedPreferences(Dados1Activity.this);
        edit = preferences.edit();

        radioGroup = findViewById(R.id.rgp_locomocao);

        btn_next = findViewById(R.id.btn_next);

        edt_viajantes = findViewById(R.id.edt_viajantes);

        edt_qtd_dias = findViewById(R.id.edt_qtd_dias);

        btn_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int radioButtonId = radioGroup.getCheckedRadioButtonId();

                // Pelo menos um RadioButton está selecionado
                if ((radioButtonId != -1) && (!TextUtils.isEmpty(edt_viajantes.getText().toString()))
                        && (!TextUtils.isEmpty(edt_qtd_dias.getText().toString()))) {

                    try {
                        DadosUserDAO dadosUserDAO = new DadosUserDAO(Dados1Activity.this);

                        int viajantes = Integer.parseInt(edt_viajantes.getText().toString());
                        int qtd_dias = Integer.parseInt(edt_qtd_dias.getText().toString());

                        long id_login = preferences.getLong("id_login", 9999);
                        if (id_login == 9999){
                            Toast.makeText(Dados1Activity.this, "Usuário não existe.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        DadosUser dadosUser = new DadosUser(id_login, viajantes, qtd_dias);
                        long idDados = dadosUserDAO.insert(dadosUser);
                        edit.putLong("id_dados", idDados);
                        edit.apply();

                        ViagemPost viagemPost = new ViagemPost();
                        viagemPost.setId(19);
                        viagemPost.setDuracaoViagem(qtd_dias);
                        viagemPost.setTotalViajantes(viajantes);
                        viagemPost.setCustoPorPessoa(0.00);
                        viagemPost.setCustoTotalViagem(0.00);
                        viagemPost.setLocal("Florianopolis");
                        viagemPost.setIdConta(idDados);

                        SweetAlertDialog pDialog = new SweetAlertDialog(Dados1Activity.this, SweetAlertDialog.PROGRESS_TYPE);
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                        pDialog.setTitleText("Aguarde");
                        pDialog.setContentText("Enviando dados ao servidor ...");
                        pDialog.setCancelable(false);
                        pDialog.show();

                        Toast.makeText(Dados1Activity.this, "Dados gravados com sucesso.", Toast.LENGTH_SHORT).show();

                        Api.postViagem(viagemPost, new Callback<Resposta>() {
                            @Override
                            public void onResponse(Call<Resposta> call, Response<Resposta> response) {
                                if (response != null && response.isSuccessful()) {
                                    pDialog.cancel();
                                    Resposta resposta = response.body();
                                    System.out.println("*********");
                                    Toast.makeText(Dados1Activity.this, "Dados gravados com sucesso na API.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Resposta> call, Throwable t) {
                                pDialog.cancel();
                                t.printStackTrace();
                            }
                        });

                        RadioButton radioButtonSelecionado = findViewById(radioButtonId);

                        if (radioButtonSelecionado.getId() == R.id.radio_btn_carro) {
                            startActivity(new Intent(Dados1Activity.this, GasolinaActivity.class));
                        } else {
                            startActivity(new Intent(Dados1Activity.this, TarifaAereaActivity.class));
                        }

                    } catch (Exception e) {
                        Toast.makeText(Dados1Activity.this, "Erro ao gravar os dados.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Dados1Activity.this, "Há campos que precisam ser preenchidos.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}
