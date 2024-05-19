package com.example.learnkanas

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learnkanas.databinding.ActivityMainBinding
import com.example.learnkanas.model.DataStore
import com.example.learnkanas.model.Kana
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var randomKana: Kana
    private val datastore = DataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Função para atualizar a interface do usuário com um novo randomKana
        fun updateUIWithRandomKana() {
            findViewById<TextView>(R.id.kanaView).text = randomKana.kana
            findViewById<TextView>(R.id.portugueseView).text = randomKana.portuguese
            findViewById<TextView>(R.id.portugueseView).visibility = View.GONE
        }


        randomKana = datastore.getKana((0 until datastore.kanas.size).random())

        // Inicializa a interface com o primeiro randomKana
        updateUIWithRandomKana()

        binding.submitButton.setOnClickListener {
            val input = binding.input.text.toString()
            if (randomKana.portuguese == input) {
                randomKana = datastore.getKana((0 until datastore.kanas.size).random())
                updateUIWithRandomKana()
                Toast.makeText(this, "Parabéns", Toast.LENGTH_LONG).show()
            } else {
                findViewById<TextView>(R.id.portugueseView).visibility = View.VISIBLE
                Toast.makeText(this, "Errou", Toast.LENGTH_LONG).show()
            }
        }
    }
}