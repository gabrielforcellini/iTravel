package com.unesc.itravel;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class TarifaAereaActivity extends AppCompatActivity {

    private Button btn_next;
    private Button btn_previous;
    private EditText edt_passagem;
    private EditText edt_total;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifa_aerea);

        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_voltar);
        edt_passagem = findViewById(R.id.edt_passagem);
        edt_total = findViewById(R.id.edt_total);

        List<EditText> campos = Arrays.asList(
                edt_passagem
        );

        btn_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!Utils.validarCamposVazios(campos)) {
                    return;
                }
                startActivity(new Intent(TarifaAereaActivity.this, RefeicoesActivity.class));
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
    }
}
