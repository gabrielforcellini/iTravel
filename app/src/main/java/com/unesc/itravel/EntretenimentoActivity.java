package com.unesc.itravel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
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

import com.unesc.itravel.api.Api;
import com.unesc.itravel.api.model.post.ViagemCustoEntretenimentoPost;
import com.unesc.itravel.api.model.post.result.Resposta;
import com.unesc.itravel.database.DAO.DadosUserDAO;
import com.unesc.itravel.database.DAO.EntretenimentoDAO;
import com.unesc.itravel.database.DAO.HospedagemDAO;
import com.unesc.itravel.database.model.DadosUser;
import com.unesc.itravel.database.model.Entretenimento;
import com.unesc.itravel.database.model.Hospedagem;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntretenimentoActivity extends AppCompatActivity {
    private Button btn_next;
    private Button btn_previous;
    private EditText edt_entretenimento;
    private EditText edt_total;
    SharedPreferences preferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entretenimento);
        preferences = PreferenceManager.getDefaultSharedPreferences(EntretenimentoActivity.this);

        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_voltar);
        edt_entretenimento = findViewById(R.id.edt_entretenimento);
        edt_total = findViewById(R.id.edt_total);

        List<EditText> campos = Arrays.asList(
                edt_entretenimento
        );

        btn_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!Utils.validarCamposVazios(campos)){
                    return;
                }
                try {
                    EntretenimentoDAO entretenimentoDAO = new EntretenimentoDAO(EntretenimentoActivity.this);

                    float valor_entretenimento = Float.parseFloat(edt_entretenimento.getText().toString());
                    float total = Float.parseFloat(edt_total.getText().toString());

                    long id_dados = preferences.getLong("id_dados", 99);
                    if (id_dados == 99){
                        Toast.makeText(EntretenimentoActivity.this, "Voce precisa informar os dados da viagem.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Entretenimento entretenimento = new Entretenimento(id_dados, valor_entretenimento, total);
                    entretenimentoDAO.insert(entretenimento);

                    ViagemCustoEntretenimentoPost entretenimentoPost = new ViagemCustoEntretenimentoPost();
                    entretenimentoPost.setId(19);
                    entretenimentoPost.setEntretenimento(edt_total.getText().toString());
                    entretenimentoPost.setValor(valor_entretenimento);
                    entretenimentoPost.setViagemId(id_dados);

                    SweetAlertDialog pDialog = new SweetAlertDialog(EntretenimentoActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Aguarde");
                    pDialog.setContentText("Enviando dados ao servidor ...");
                    pDialog.setCancelable(false);
                    pDialog.show();

                    Api.postViagemCustoEntretenimento(entretenimentoPost, new Callback<Resposta>() {
                        @Override
                        public void onResponse(Call<Resposta> call, Response<Resposta> response) {
                            if (response != null && response.isSuccessful()) {
                                pDialog.cancel();
                                Resposta resposta = response.body();
                                System.out.println("*********");
                                Toast.makeText(EntretenimentoActivity.this, "Custos gasolina gravados com sucesso na API.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Resposta> call, Throwable t) {
                            pDialog.cancel();
                            Toast.makeText(EntretenimentoActivity.this, "Erro ao gravar dados na API.", Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }
                    });

                    Toast.makeText(EntretenimentoActivity.this, "Dados gravados com sucesso.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EntretenimentoActivity.this, ControleGastosActivity.class));
                } catch (Exception e) {
                    Toast.makeText(EntretenimentoActivity.this, "Erro ao gravar os dados.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EntretenimentoActivity.this, HospedagemActivity.class));
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

        edt_entretenimento.addTextChangedListener(textWatcher);
    }

    private void calcularValorTotal() {
        if (TextUtils.isEmpty(edt_entretenimento.getText().toString())) {
            return;
        }

        double valorEntretenimento = Double.parseDouble(edt_entretenimento.getText().toString());

        try {
//            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(EntretenimentoActivity.this);

//            String id_dados = sharedPreferences.getString("id_dados", "");
            long id_dados = preferences.getLong("id_dados", 99);
            String idDados = String.valueOf(id_dados);

            DadosUserDAO dadosUserDAO = new DadosUserDAO(EntretenimentoActivity.this);

            DadosUser dadosUser = dadosUserDAO.findOne(idDados);

            double resultado = valorEntretenimento * dadosUser.getQtd_dias();
            edt_total.setText(String.valueOf(resultado));
        } catch (SQLException e) {
            Toast.makeText(EntretenimentoActivity.this, "Erro ao tentar obter dados do usuário.", Toast.LENGTH_SHORT).show();
        }
    }
}
