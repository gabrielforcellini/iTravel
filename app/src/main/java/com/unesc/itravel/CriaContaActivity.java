package com.unesc.itravel;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.unesc.itravel.database.DAO.LoginDAO;
import com.unesc.itravel.database.model.Login;

public class CriaContaActivity extends AppCompatActivity {
    private EditText edt_nome;
    private EditText edt_usuario;
    private EditText edt_senha;
    private EditText edt_confirma_senha;
    private Button btn_salvar;
    private Button btn_voltar;
    private LoginDAO loginDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_conta);

        edt_nome = findViewById(R.id.edt_nome);
        edt_usuario = findViewById(R.id.edt_usuario);
        edt_senha = findViewById(R.id.edt_senha);
        edt_confirma_senha = findViewById(R.id.edt_confirma_senha);
        btn_salvar = findViewById(R.id.btn_salvar);
        btn_voltar = findViewById(R.id.btn_voltar);

        edt_senha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = edt_nome.getText().toString();
                String usuario = edt_usuario.getText().toString();
                String senha = edt_senha.getText().toString();
                String confirma_senha = edt_confirma_senha.getText().toString();

                if (!senha.equals(confirma_senha)) {
                    Snackbar.make(findViewById(android.R.id.content), "As senhas não coincidem", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                loginDAO = new LoginDAO(CriaContaActivity.this);

                Login login = new Login(nome, usuario, senha);
                loginDAO.insert(login);
                Snackbar.make(findViewById(android.R.id.content), "Usuário criado.", Snackbar.LENGTH_SHORT).show();
                startActivity(new Intent(CriaContaActivity.this, Dados1Activity.class));
            }
        });

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CriaContaActivity.this, LoginActivity.class));
            }
        });
    }
}
