package com.unesc.itravel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.unesc.itravel.database.DAO.ControleGastosDAO;
import com.unesc.itravel.database.DAO.EntretenimentoDAO;
import com.unesc.itravel.database.model.ControleGastos;
import com.unesc.itravel.database.model.Entretenimento;

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

    SharedPreferences preferences;
    SharedPreferences.Editor edit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_gastos);

        preferences = PreferenceManager.getDefaultSharedPreferences(ControleGastosActivity.this);
        edit = preferences.edit();

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
                try {
                    ControleGastosDAO controleGastosDAO = new ControleGastosDAO(ControleGastosActivity.this);

                    boolean box_gasolina = Boolean.parseBoolean(check_box_gasolina.getText().toString());
                    boolean box_tarifa = Boolean.parseBoolean(check_box_tarifa.getText().toString());
                    boolean box_refeicao = Boolean.parseBoolean(check_box_refeicao.getText().toString());
                    boolean box_hospedagem = Boolean.parseBoolean(check_box_hospedagem.getText().toString());
                    boolean box_entretenimento = Boolean.parseBoolean(check_box_entretenimento.getText().toString());

                    long id_dados = preferences.getLong("id_dados", 99);
                    if (id_dados == 99){
                        Toast.makeText(ControleGastosActivity.this, "Voce precisa informar os dados da viagem.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    ControleGastos controleGastos = new ControleGastos(id_dados, box_gasolina, box_tarifa, box_refeicao, box_hospedagem, box_entretenimento);
                    controleGastosDAO.insert(controleGastos);
                    Toast.makeText(ControleGastosActivity.this, "Dados gravados com sucesso.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ControleGastosActivity.this, ResultadoActivity.class));
                } catch (Exception e) {
                    Toast.makeText(ControleGastosActivity.this, "Erro ao gravar os dados.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ControleGastosActivity.this, EntretenimentoActivity.class));
            }
        });
    }
}
