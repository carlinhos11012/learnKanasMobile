package com.example.learnkanas

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.learnkanas.databinding.ActivityMainBinding
import com.example.learnkanas.model.DataStore
import com.example.learnkanas.model.Kana
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var answer: String
    private var randomKanaList = mutableListOf<Kana>()
    private var level = 1
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
                R.id.nav_mixed -> {
                    listType = "mixed"
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
                if (binding.input.text.toString() == answer) {
                    updateUIWithRandomKana()
                    binding.input.text.clear()
                }
            }
        })

        binding.helpButton.setOnClickListener {
            binding.input.text.clear()
            showAllPortugueseViews()
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                level = progress + 1
                binding.difficultyLevel.text = "Dificuldade: $level"
                updateUIWithRandomKana()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Inicializa com o nível de dificuldade padrão
        updateKanaViews(level)
    }

    // Função para atualizar a interface do usuário com um novo randomKana
    fun updateUIWithRandomKana() {
        randomKanaList.clear()
        answer = ""
        repeat(level) {
            randomKanaList.add(datastore.getKana(listType, (0 until datastore.getSize(listType)).random()))
        }
        updateKanaViews(level)
    }

    private fun updateKanaViews(level: Int) {
        // Limpa todas as views de kana antes de adicionar novas
        binding.kanaContainer.removeAllViews()

        val inflater = LayoutInflater.from(this)

        for (i in 0 until level) {
            val kanaView = inflater.inflate(R.layout.item_kana, binding.kanaContainer, false)

            val portugueseView = kanaView.findViewById<TextView>(R.id.portugueseView)
            val kanaTextView = kanaView.findViewById<TextView>(R.id.kanaView)

            val kana = randomKanaList[i]
            answer += kana.portuguese
            portugueseView.text = kana.portuguese
            kanaTextView.text = kana.kana
            portugueseView.visibility = View.GONE

            binding.kanaContainer.addView(kanaView)
        }
    }

    // Função para exibir todos os portugueseView quando o botão de ajuda é pressionado
    private fun showAllPortugueseViews() {
        for (i in 0 until binding.kanaContainer.childCount) {
            val kanaView = binding.kanaContainer.getChildAt(i)
            val portugueseView = kanaView.findViewById<TextView>(R.id.portugueseView)
            portugueseView.visibility = View.VISIBLE
        }
    }
}