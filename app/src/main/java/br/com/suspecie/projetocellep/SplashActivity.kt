package br.com.suspecie.projetocellep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /*
        {} -> Bloco ou Escopo de programaçao
        [] ->  listas e colecoes
        () -> condicao, passagem de parametros ou metodo construtor
         */

        // Pausa a execução do app
        Handler().postDelayed({
            //funcao lambda
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }, 5000)
    }
}
