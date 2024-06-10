package com.example.learnkanas

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.learnkanas.databinding.ActivityMainBinding
import com.example.learnkanas.model.DataStore
import com.example.learnkanas.model.Kana
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var randomKana: Kana
    private var listType = "hiragana"
    private val datastore = DataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        datastore.loadKanas(this)

        // Inicializa a interface com o primeiro randomKana
        updateUIWithRandomKana()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_hiragana -> {
                    listType = "hiragana"
                    updateUIWithRandomKana()
                    true
                }
                R.id.nav_katakana -> {
                    listType = "katakana"
                    updateUIWithRandomKana()
                    true
                }
                else -> false
            }
        }

        binding.input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (randomKana.portuguese == binding.input.text.toString()) {
                    updateUIWithRandomKana()
                    binding.input.text.clear()
                }
            }
        })

        binding.helpButton.setOnClickListener {
            binding.input.text.clear()
            binding.portugueseView.visibility = View.VISIBLE
        }
    }

    // Função para atualizar a interface do usuário com um novo randomKana
    fun updateUIWithRandomKana() {
        randomKana = datastore.getKana(listType, (0 until datastore.getSize(listType)).random())
        binding.kanaView.text = randomKana.kana
        binding.portugueseView.text = randomKana.portuguese
        binding.portugueseView.visibility = View.GONE
    }
}