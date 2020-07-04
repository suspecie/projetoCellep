package br.com.suspecie.projetocellep

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        //CRIANDO O SPINNER
        // etapa 1: criar uma lista do spinner
        val listaSexo = arrayListOf("Selecione o sexo", "Feminino", "Masculino", "Outros")
        //para adicionar mais um item na lista
        listaSexo.add("Prefiro não informar")

        //etapa2: criar um adapter(criar um layout para o spinner) do spinner
        //ja existe um layout(R - recurso) pronto para o android
        val spinnerAdapter = ArrayAdapter(
            this@CadastroActivity,
            android.R.layout.simple_spinner_dropdown_item,
            listaSexo
        )

        //etapa3: linkar o adapter com o spinner
        spnSexo.adapter = spinnerAdapter

        //clique do botao cadastrar
        btnCadastroCadastrar.setOnClickListener {
            val name = edtCadastroNome.text.toString().trim()
            val lastName = edtCadastroSobrenome.text.toString().trim()
            val email = edtCadastroEmail.text.toString().trim().toLowerCase()
            val password = edtCadastroPassword.text.toString().trim()
            val gender = spnSexo.selectedItem.toString()

            //verificar se os campos estao vazios
            if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() ||
                        gender === "Selecione o sexo"
            ) {
                //criando uma caixa de alerta
                AlertDialog.Builder(this@CadastroActivity)
                    .setTitle("Atenção")
                    .setMessage("Preencha todos os campos")
                    .setPositiveButton("OK") {_, _ ->
                        //funcao lambda
                    }
                    .setCancelable(false)
                    .create()
                    .show()
            } else {
                //Gravar os dados do usuário no Shared Preferences (pasta que guardamos dados internos do android)
                //serve somente para dados que nao sao senssiveis
                //primeiro parametro: nome do arquivo
                //segundo parametro: modo de acesso
                //depois vamos editar ele para salvar os dados
                //para verificar onde estao os dados ir no
                //Device File Explorer no android studio > data > data >
                // br.com.suspecie.projetocellep > shared_prefs
                getSharedPreferences("cadastro-$email", Context.MODE_PRIVATE)
                    .edit()
                    .apply {
                        //chave , valor
                        putString("nome", name)
                        putString("sobrenome", lastName)
                        putString("email", email)
                        putString("senha", password)
                        putString("sexo", gender)
                    }
                    .apply()

                //criando uma mensagem de sucesso ao usuario
                Toast.makeText(
                    this@CadastroActivity,
                    "Usuário cadastrado com sucesso!",
                    Toast.LENGTH_LONG
                ).show()

                //limpar todos os campos depois que o cadastro foi realizado
                edtCadastroNome.setText("")
                edtCadastroSobrenome.text.clear()
                edtCadastroNome.text.clear()
                edtCadastroEmail.text.clear()
                edtCadastroPassword.text.clear()
                spnSexo.setSelection(0)

                //abrir a tela main
                startActivity(Intent(this@CadastroActivity, MainActivity::class.java)
                    .apply {
                        //passagem de parametros de uma tela para outra
                        putExtra("email", email)
                    }
                )
                // fechar todas as telas do empilhamento
                finishAffinity()
            }
        }

    }
}
