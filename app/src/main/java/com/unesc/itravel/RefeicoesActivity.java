package com.unesc.itravel;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.unesc.itravel.database.DAO.RefeicaoDAO;
import com.unesc.itravel.database.DAO.TarifaAereaDAO;
import com.unesc.itravel.database.model.Refeicao;
import com.unesc.itravel.database.model.TarifaAerea;

import java.util.Arrays;
import java.util.List;

public class RefeicoesActivity extends AppCompatActivity {
    private Button btn_next;
    private Button btn_previous;
    private EditText edt_custo_refeicao;
    private EditText edt_refeicao_dia;
    private EditText edt_total_refeicao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refeicoes);

        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_voltar);
        edt_custo_refeicao = findViewById(R.id.edt_custo_refeicao);
        edt_refeicao_dia = findViewById(R.id.edt_refeicao_dia);
        edt_total_refeicao = findViewById(R.id.edt_total_refeicao);

        List<EditText> campos = Arrays.asList(
                edt_custo_refeicao,
                edt_refeicao_dia
        );

        btn_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!Utils.validarCamposVazios(campos)) {
                    return;
                }
                try {
                    RefeicaoDAO refeicaoDAO = new RefeicaoDAO(RefeicoesActivity.this);

                    float custo = Float.parseFloat(edt_custo_refeicao.getText().toString());
                    float refeicao_dia = Float.parseFloat(edt_refeicao_dia.getText().toString());
                    float total = Float.parseFloat(edt_total_refeicao.getText().toString());

                    Refeicao refeicao = new Refeicao(custo, refeicao_dia, total);
                    refeicaoDAO.insert(refeicao);
                    Toast.makeText(RefeicoesActivity.this, "Dados gravados com sucesso.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RefeicoesActivity.this, HospedagemActivity.class));
                } catch (Exception e) {
                    Toast.makeText(RefeicoesActivity.this, "Erro ao gravar os dados.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RefeicoesActivity.this, TarifaAereaActivity.class));
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // vazio, porém necessário
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularValorTotal();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // vazio, porém necessário
            }
        };

        edt_custo_refeicao.addTextChangedListener(textWatcher);
        edt_refeicao_dia.addTextChangedListener(textWatcher);
    }

    private void calcularValorTotal() {
        if (TextUtils.isEmpty(edt_custo_refeicao.getText().toString()) || TextUtils.isEmpty(edt_refeicao_dia.getText().toString())) {
            return;
        }

        double custoRefeicao = Double.parseDouble(edt_custo_refeicao.getText().toString());
        double qtdRefeicaoDia = Double.parseDouble(edt_refeicao_dia.getText().toString());

        double resultado = ((qtdRefeicaoDia * 3) * custoRefeicao) * 5;

        edt_total_refeicao.setText(String.valueOf(resultado));
    }
}
