package com.unesc.itravel;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class Dados1Activity extends AppCompatActivity {

    private Button btn_next;

    private RadioGroup radioGroup;

    private EditText edt_viajantes;

    private EditText edt_qtd_dias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_dados_1);

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
                    RadioButton radioButtonSelecionado = findViewById(radioButtonId);

                    if (radioButtonSelecionado.getId() == R.id.radio_btn_carro) {
                        startActivity(new Intent(Dados1Activity.this, GasolinaActivity.class));
                    } else {
                        startActivity(new Intent(Dados1Activity.this, TarifaAereaActivity.class));
                    }
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Há campos que precisam ser preenchidos.", Snackbar.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}
