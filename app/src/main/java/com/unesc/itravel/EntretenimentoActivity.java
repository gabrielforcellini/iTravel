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

import com.unesc.itravel.database.DAO.EntretenimentoDAO;
import com.unesc.itravel.database.DAO.HospedagemDAO;
import com.unesc.itravel.database.model.Entretenimento;
import com.unesc.itravel.database.model.Hospedagem;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class EntretenimentoActivity extends AppCompatActivity {
    private Button btn_next;
    private Button btn_previous;
    private EditText edt_entretenimento;
    private EditText edt_total;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entretenimento);

        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_voltar);
        edt_entretenimento = findViewById(R.id.edt_entretenimento);
        edt_total = findViewById(R.id.edt_total);

        List<EditText> campos = Arrays.asList(
                edt_entretenimento
        );

        btn_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!Utils.validarCamposVazios(campos)){
                    return;
                }
                try {
                    EntretenimentoDAO entretenimentoDAO = new EntretenimentoDAO(EntretenimentoActivity.this);

                    float valor_entretenimento = Float.parseFloat(edt_entretenimento.getText().toString());
                    float total = Float.parseFloat(edt_total.getText().toString());

                    Entretenimento entretenimento = new Entretenimento(valor_entretenimento, total);
                    entretenimentoDAO.insert(entretenimento);
                    Toast.makeText(EntretenimentoActivity.this, "Dados gravados com sucesso.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EntretenimentoActivity.this, ControleGastosActivity.class));
                } catch (Exception e) {
                    Toast.makeText(EntretenimentoActivity.this, "Erro ao gravar os dados.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EntretenimentoActivity.this, HospedagemActivity.class));
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

        edt_entretenimento.addTextChangedListener(textWatcher);
    }

    private void calcularValorTotal() {
        if (TextUtils.isEmpty(edt_entretenimento.getText().toString())) {
            return;
        }

        double valorEntretenimento = Double.parseDouble(edt_entretenimento.getText().toString());

        double resultado = valorEntretenimento * 5;

        edt_total.setText(String.valueOf(resultado));
    }
}
