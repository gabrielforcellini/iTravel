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

import com.google.android.material.snackbar.Snackbar;
import com.unesc.itravel.database.DAO.GasolinaDAO;
import com.unesc.itravel.database.DAO.TarifaAereaDAO;
import com.unesc.itravel.database.model.Gasolina;
import com.unesc.itravel.database.model.TarifaAerea;

import java.util.Arrays;
import java.util.List;

public class TarifaAereaActivity extends AppCompatActivity {

    private Button btn_next;
    private Button btn_previous;
    private EditText edt_passagem;
    private EditText edt_alugel_carro;
    private EditText edt_total;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifa_aerea);

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

                    TarifaAerea tarifaAerea = new TarifaAerea(passagem, aluguelCarro, total);
                    tarifaAereaDAO.insert(tarifaAerea);
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

        double valorPassagem = Double.parseDouble(edt_passagem.getText().toString());
        double valorAluguelCarro = Double.parseDouble(edt_alugel_carro.getText().toString());

        double resultado = ((valorPassagem * 3) + valorAluguelCarro);

        edt_total.setText(String.valueOf(resultado));
    }
}
