package com.unesc.itravel;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.unesc.itravel.database.DAO.DadosUserDAO;
import com.unesc.itravel.database.DAO.HospedagemDAO;
import com.unesc.itravel.database.model.DadosUser;
import com.unesc.itravel.database.model.Hospedagem;

public class Dados1Activity extends AppCompatActivity {

    private Button btn_next;

    private RadioGroup radioGroup;

    public EditText edt_viajantes;

    public EditText edt_qtd_dias;
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

                    try {
                        DadosUserDAO dadosUserDAO = new DadosUserDAO(Dados1Activity.this);

                        float viajantes = Float.parseFloat(edt_viajantes.getText().toString());
                        float qtd_dias = Float.parseFloat(edt_qtd_dias.getText().toString());

                        DadosUser dadosUser = new DadosUser(viajantes, qtd_dias);
                        dadosUserDAO.insert(dadosUser);
                        Toast.makeText(Dados1Activity.this, "Dados gravados com sucesso.", Toast.LENGTH_SHORT).show();

                        RadioButton radioButtonSelecionado = findViewById(radioButtonId);

                        if (radioButtonSelecionado.getId() == R.id.radio_btn_carro) {
                            startActivity(new Intent(Dados1Activity.this, GasolinaActivity.class));
                        } else {
                            startActivity(new Intent(Dados1Activity.this, TarifaAereaActivity.class));
                        }

                    } catch (Exception e) {
                        Toast.makeText(Dados1Activity.this, "Erro ao gravar os dados.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Dados1Activity.this, "Há campos que precisam ser preenchidos.", Toast.LENGTH_SHORT).show();
//                    Snackbar.make(findViewById(android.R.id.content), "Há campos que precisam ser preenchidos.", Snackbar.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}
