package com.unesc.itravel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.unesc.itravel.api.Api;
import com.unesc.itravel.api.model.post.ViagemPost;
import com.unesc.itravel.api.model.post.result.Resposta;
import com.unesc.itravel.database.DAO.DadosUserDAO;
import com.unesc.itravel.database.DAO.EntretenimentoDAO;
import com.unesc.itravel.database.DAO.HospedagemDAO;
import com.unesc.itravel.database.DAO.RefeicaoDAO;
import com.unesc.itravel.database.DAO.ResultadoDAO;
import com.unesc.itravel.database.DAO.TarifaAereaDAO;
import com.unesc.itravel.database.model.DadosUser;
import com.unesc.itravel.database.model.Entretenimento;
import com.unesc.itravel.database.model.Hospedagem;
import com.unesc.itravel.database.model.Refeicao;
import com.unesc.itravel.database.model.Resultado;
import com.unesc.itravel.database.model.TarifaAerea;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultadoActivity extends AppCompatActivity {
    private Button btn_next;
    private Button btn_previous;

    private EditText edt_total;
    private EditText edt_total_pessoa;

    SharedPreferences preferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        preferences = PreferenceManager.getDefaultSharedPreferences(ResultadoActivity.this);

//        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_previous);
        edt_total = findViewById(R.id.edt_total);
        edt_total_pessoa = findViewById(R.id.edt_total_pessoa);

        calcularValorTotalAndInserirInformacoes();

//        btn_next.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(new Intent(ResultadoActivity.this, GasolinaActivity.class));
//            }
//        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ResultadoActivity.this, ControleGastosActivity.class));
            }
        });
    }

    private void calcularValorTotalAndInserirInformacoes() {

        long id_dados = preferences.getLong("id_dados", 99);
        if (id_dados == 99){
            Toast.makeText(ResultadoActivity.this, "Voce precisa informar os dados da viagem.", Toast.LENGTH_SHORT).show();
            return;
        }

        HospedagemDAO hospedagemDAO = new HospedagemDAO(ResultadoActivity.this);
        String idDados = String.valueOf(id_dados);

        Hospedagem hospedagem = hospedagemDAO.findOneByIdDados(idDados);

        TarifaAereaDAO tarifaAereaDAO = new TarifaAereaDAO(ResultadoActivity.this);

        TarifaAerea tarifaAerea = tarifaAereaDAO.findOneByIdDados(idDados);

        RefeicaoDAO refeicaoDAO = new RefeicaoDAO(ResultadoActivity.this);

        Refeicao refeicao = refeicaoDAO.findOneByIdDados(idDados);

        EntretenimentoDAO entretenimentoDAO = new EntretenimentoDAO(ResultadoActivity.this);

        Entretenimento entretenimento = entretenimentoDAO.findOneByIdDados(idDados);

        DadosUserDAO dadosUserDAO = new DadosUserDAO(ResultadoActivity.this);

        DadosUser dadosUser = dadosUserDAO.findOne(idDados);

        double totalHospedagem = hospedagem.getTotal();
        double totalTarifaAerea = tarifaAerea != null && tarifaAerea.getTotal() != null ? tarifaAerea.getTotal() : 0.00;
        double totalRefeicoes = refeicao != null ? refeicao.getTotal() : 0.00;
        double totalEntretenimento = entretenimento.getTotal();

        double total = totalHospedagem + totalTarifaAerea + totalRefeicoes + totalEntretenimento;
        double total_pessoas = total / dadosUser.getViajantes();

        edt_total.setText(String.valueOf(total));
        edt_total_pessoa.setText(String.valueOf(total_pessoas));

        try {
            ResultadoDAO resultadoDAO = new ResultadoDAO(ResultadoActivity.this);

            float custo_total = Float.parseFloat(edt_total.getText().toString());
            float total_pessoa = Float.parseFloat(edt_total_pessoa.getText().toString());

            Resultado resultado = new Resultado(id_dados, custo_total, total_pessoa);
            resultadoDAO.insert(resultado);
            Toast.makeText(ResultadoActivity.this, "Total das despesas da viagem.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(ResultadoActivity.this, "Erro ao mostrar dados.", Toast.LENGTH_SHORT).show();
        }

        ViagemPost viagemPost = new ViagemPost();
        viagemPost.setId(preferences.getInt("KEY_ID", 0));
        viagemPost.setDuracaoViagem(dadosUser.getQtd_dias());
        viagemPost.setTotalViajantes(dadosUser.getViajantes());
        viagemPost.setCustoPorPessoa(total_pessoas);
        viagemPost.setCustoTotalViagem(total);
        viagemPost.setLocal("Florianopolis");
        viagemPost.setIdConta(preferences.getLong("id_dados", 0));

        SweetAlertDialog pDialog = new SweetAlertDialog(ResultadoActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Aguarde");
        pDialog.setContentText("Enviando resultados ao servidor ...");
        pDialog.setCancelable(false);
        pDialog.show();

        Toast.makeText(ResultadoActivity.this, "Dados gravados com sucesso.", Toast.LENGTH_SHORT).show();

        Api.postViagem(viagemPost, new Callback<Resposta>() {
            @Override
            public void onResponse(Call<Resposta> call, Response<Resposta> response) {
                if (response != null && response.isSuccessful()) {
                    pDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<Resposta> call, Throwable t) {
                pDialog.cancel();
                t.printStackTrace();
            }
        });


    }
}
