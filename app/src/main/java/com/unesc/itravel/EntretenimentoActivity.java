package com.unesc.itravel;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class EntretenimentoActivity extends AppCompatActivity {
    private Button btn_next;
    private Button btn_previous;
    private EditText edt_entretenimento;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entretenimento);

        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_voltar);
        edt_entretenimento = findViewById(R.id.edt_entretenimento);

        List<EditText> campos = Arrays.asList(
                edt_entretenimento
        );

        btn_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!Utils.validarCamposVazios(campos)){
                    return;
                }
                startActivity(new Intent(EntretenimentoActivity.this, ControleGastosActivity.class));
            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EntretenimentoActivity.this, HospedagemActivity.class));
            }
        });
    }
}
