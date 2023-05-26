package com.unesc.itravel;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.unesc.itravel.Utils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class GasolinaActivity extends AppCompatActivity {

    private Button btn_next;

    private Button btn_previous;

    private EditText edt_km;
    private EditText edt_media_litro;
    private EditText edt_custo_litro;
    private EditText edt_total;
    private EditText edt_total_veic;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasolina);

        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_voltar);
        edt_km = findViewById(R.id.edt_km);
        edt_media_litro = findViewById(R.id.edt_media_litro);
        edt_custo_litro = findViewById(R.id.edt_custo_litro);
        edt_total_veic = findViewById(R.id.edt_total_veic);

        List<EditText> campos = Arrays.asList(
                edt_km,
                edt_media_litro,
                edt_custo_litro,
                edt_total_veic
        );

        btn_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!Utils.validarCamposVazios(campos)) {
                    return;
                }
                startActivity(new Intent(GasolinaActivity.this, TarifaAereaActivity.class));
            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Utils.validarCamposVazios(campos)) {
                    return;
                }
                startActivity(new Intent(GasolinaActivity.this, Dados1Activity.class));
            }
        });


    }
}
