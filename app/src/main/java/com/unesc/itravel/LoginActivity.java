package com.unesc.itravel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.unesc.itravel.database.DAO.LoginDAO;
import com.unesc.itravel.database.model.Login;

public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText senha;
    private Button salvar;
    private TextView link_criar_conta;
    SharedPreferences preferences;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        edit = preferences.edit();

        login = findViewById(R.id.edt_nome);
        senha = findViewById(R.id.edt_senha);
        salvar = findViewById(R.id.btn_login);
        link_criar_conta = findViewById(R.id.link_criar_conta);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginText = login.getText().toString();
                String senhaText = senha.getText().toString();

                if (!validaLogin(loginText, senhaText)) {
                    Snackbar.make(findViewById(android.R.id.content), "Usuário ou senha incorretos.", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(LoginActivity.this, "Login feito com sucesso.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, Dados1Activity.class));
            }
        });

        link_criar_conta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CriaContaActivity.class));
            }
        });
    }

    private boolean validaLogin(String usuario, String senha) {
        LoginDAO loginDao = new LoginDAO(LoginActivity.this);
        Login login = loginDao.findOne(usuario);
        edit.putLong("id_login", login.getId());
        edit.apply();

        if(login == null) {
            return false;
        }
        if (usuario.equals("")) {
            return false;
        }
        if (senha.equals("")) {
            return false;
        }
        if (login.getLogin().equals(usuario) && login.getSenha().equals(senha)) {
            return true;
        }
        return false;
    }
}
