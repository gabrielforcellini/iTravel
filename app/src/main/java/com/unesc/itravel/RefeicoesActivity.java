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

public class RefeicoesActivity extends AppCompatActivity {
    private Button btn_next;
    private Button btn_previous;
    private EditText edt_cafe_manha;
    private EditText edt_almoco;
    private EditText edt_cafe_tarde;
    private EditText edt_janta;
    private EditText edt_outros;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refeicoes);

        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_voltar);
        edt_cafe_manha = findViewById(R.id.edt_cafe_manha);
        edt_almoco = findViewById(R.id.edt_almoco);
        edt_cafe_tarde = findViewById(R.id.edt_cafe_tarde);
        edt_janta = findViewById(R.id.edt_janta);
        edt_outros = findViewById(R.id.edt_outros);

        List<EditText> campos = Arrays.asList(
                edt_cafe_manha,
                edt_almoco,
                edt_cafe_tarde,
                edt_janta,
                edt_outros
        );

        btn_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!Utils.validarCamposVazios(campos)) {
                    return;
                }
                startActivity(new Intent(RefeicoesActivity.this, HospedagemActivity.class));
            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RefeicoesActivity.this, TarifaAereaActivity.class));
            }
        });
    }
}
