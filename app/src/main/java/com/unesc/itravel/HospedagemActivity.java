package com.unesc.itravel;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.unesc.itravel.database.DAO.HospedagemDAO;
import com.unesc.itravel.database.DAO.RefeicaoDAO;
import com.unesc.itravel.database.model.Hospedagem;
import com.unesc.itravel.database.model.Refeicao;

import java.util.Arrays;
import java.util.List;

public class HospedagemActivity extends AppCompatActivity {
    private Button btn_next;
    private Button btn_previous;
    private EditText edt_diaria;
    private EditText edt_noite;
    private EditText edt_quarto;
    private EditText edt_total;

    SharedPreferences preferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospedagem);

        preferences = PreferenceManager.getDefaultSharedPreferences(HospedagemActivity.this);

        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_voltar);
        edt_diaria = findViewById(R.id.edt_diaria);
        edt_noite = findViewById(R.id.edt_noite);
        edt_quarto = findViewById(R.id.edt_quarto);
        edt_total = findViewById(R.id.edt_total);

        List<EditText> campos = Arrays.asList(
                edt_diaria,
                edt_noite,
                edt_quarto
        );

        btn_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!Utils.validarCamposVazios(campos)) {
                    return;
                }
                try {
                    HospedagemDAO hospedagemDAO = new HospedagemDAO(HospedagemActivity.this);

                    float valor_diaria = Float.parseFloat(edt_diaria.getText().toString());
                    float total_noites = Float.parseFloat(edt_noite.getText().toString());
                    float total_quartos = Float.parseFloat(edt_quarto.getText().toString());
                    float total = Float.parseFloat(edt_total.getText().toString());

                    long id_dados = preferences.getLong("id_dados", 99);
                    if (id_dados == 99){
                        Toast.makeText(HospedagemActivity.this, "Voce precisa informar os dados da viagem.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Hospedagem hospedagem = new Hospedagem(id_dados, valor_diaria, total_noites, total_quartos, total);
                    hospedagemDAO.insert(hospedagem);
                    Toast.makeText(HospedagemActivity.this, "Dados gravados com sucesso.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(HospedagemActivity.this, EntretenimentoActivity.class));
                } catch (Exception e) {
                    Toast.makeText(HospedagemActivity.this, "Erro ao gravar os dados.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HospedagemActivity.this, RefeicoesActivity.class));
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

        edt_diaria.addTextChangedListener(textWatcher);
        edt_noite.addTextChangedListener(textWatcher);
        edt_quarto.addTextChangedListener(textWatcher);
    }

    private void calcularValorTotal() {
        if (TextUtils.isEmpty(edt_diaria.getText().toString()) || TextUtils.isEmpty(edt_noite.getText().toString()) || TextUtils.isEmpty(edt_quarto.getText().toString())) {
            return;
        }

        double valorDiaria = Double.parseDouble(edt_diaria.getText().toString());
        double qtdNoite = Double.parseDouble(edt_noite.getText().toString());
        double qtdQuarto = Double.parseDouble(edt_quarto.getText().toString());

        double resultado = (valorDiaria * qtdNoite) * qtdQuarto;

        edt_total.setText(String.valueOf(resultado));
    }
}
