package br.com.suspecie.projetocellep

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //recuperar o e-mail da intent
        val emailRecuperado = intent.getStringExtra("email")

        //abrindo o shared preferences
        //imutavel é quando o valor não muda
        val minhaPreferencia = getSharedPreferences("cadastro-$emailRecuperado", Context.MODE_PRIVATE)

        //recuperando os dados do shared preferences e substituir no textView
        //a key é a key que esta no arquivo
        val nome = minhaPreferencia.getString("nome", "Chave não encontrada")
        val sobrenome = minhaPreferencia.getString("sobrenome", "Chave não encontrada")

        txvMainNomeCompleto.text = "$nome $sobrenome"
        txvMainEmail.text = minhaPreferencia.getString("email", "Chave não encontrada")
        txvMainSexo.text = minhaPreferencia.getString("sexo", "Chave não encontrada")

        btnMainSair.setOnClickListener {

            AlertDialog.Builder(this@MainActivity)
                .setTitle("Atenção")
                .setMessage("Você deseja mesmo sair?")
                .setPositiveButton("Sim") {_, _ ->
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    finish()
                }
                .setNegativeButton("Não") {dialog,_ ->
                    dialog.dismiss()
                }
                .setCancelable(false)
                .create()
                .show()
        }

        btnMainSiteCellep.setOnClickListener {
            startActivity(Intent(this@MainActivity, WebActivity::class.java))
            finish()
        }

    }
}
