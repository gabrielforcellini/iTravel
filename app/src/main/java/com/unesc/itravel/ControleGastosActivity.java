package com.unesc.itravel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.List;

public class ControleGastosActivity extends AppCompatActivity {
    private Button btn_next;
    private Button btn_previous;
    private CheckBox check_box_gasolina;
    private CheckBox check_box_tarifa;
    private CheckBox check_box_refeicao;
    private CheckBox check_box_hospedagem;
    private CheckBox check_box_entretenimento;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_gastos);

        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_voltar);
        check_box_gasolina = findViewById(R.id.check_box_gasolina);
        check_box_tarifa = findViewById(R.id.check_box_tarifa);
        check_box_refeicao = findViewById(R.id.check_box_refeicao);
        check_box_hospedagem = findViewById(R.id.check_box_hospedagem);
        check_box_entretenimento = findViewById(R.id.check_box_entretenimento);

        List<CheckBox> campos = Arrays.asList(
                check_box_gasolina,
                check_box_tarifa,
                check_box_refeicao,
                check_box_hospedagem,
                check_box_entretenimento
        );

        btn_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!Utils.validarCheckBoxesVazios(campos)) {
                    Snackbar.make(findViewById(android.R.id.content), "Há campos que precisam ser preenchidos.", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                startActivity(new Intent(ControleGastosActivity.this, ResultadoActivity.class));
            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ControleGastosActivity.this, EntretenimentoActivity.class));
            }
        });
    }
}
