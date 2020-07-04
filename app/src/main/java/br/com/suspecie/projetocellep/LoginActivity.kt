package br.com.suspecie.projetocellep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //clique do botão entrar
        btnLoginEntrar.setOnClickListener {
            //capturando texto digitado pelo usuário nos EditTexts
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            //verificar se existe algum texto digitado
            if (email.isEmpty()) {
                edtEmail.error = "Campo obrigatório"
                //foca no campo quando mostra o erro para o usuario
                edtEmail.requestFocus()
            } else if (password.isEmpty()) {
                edtPassword.error = "Campo obrigatório"
                edtPassword.requestFocus()
            } else if (email == "suspecie@email.com" && password == "1234") {
                //apresentando uma mensagem ao usuário
                Toast.makeText(this@LoginActivity, "Usuário logado com sucesso!", Toast.LENGTH_LONG ).show()
                //abrir a tela main - primeiro parametro: onde estamos - segundo parametro: para onde iremos
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                //tirar a tela de login do empilhamento para liberar memória
                finish()
            } else {
                Toast.makeText(this@LoginActivity, "E-mail ou Senha incorretos", Toast.LENGTH_LONG).show()
            }
        }

        //abrindo tela de cadastro
        btnLoginCadastrar.setOnClickListener {
            startActivity(Intent(this@LoginActivity, CadastroActivity::class.java))
            finish()
        }
    }
}
