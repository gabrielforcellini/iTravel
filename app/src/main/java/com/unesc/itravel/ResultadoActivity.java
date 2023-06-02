package com.unesc.itravel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

        TarifaAerea tarifaAerea = tarifaAereaDAO.findOneByIdDados(id_dados);

        RefeicaoDAO refeicaoDAO = new RefeicaoDAO(ResultadoActivity.this);

        Refeicao refeicao = refeicaoDAO.findOneByIdDados(id_dados);

        EntretenimentoDAO entretenimentoDAO = new EntretenimentoDAO(ResultadoActivity.this);

        Entretenimento entretenimento = entretenimentoDAO.findOneByIdDados(id_dados);

        DadosUserDAO dadosUserDAO = new DadosUserDAO(ResultadoActivity.this);

        DadosUser dadosUser = dadosUserDAO.findOne(id_dados);

        double totalHospedagem = hospedagem.getTotal();
        double totalTarifaAerea = tarifaAerea.getTotal();
        double totalRefeicoes = refeicao.getTotal();
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
    }
}
