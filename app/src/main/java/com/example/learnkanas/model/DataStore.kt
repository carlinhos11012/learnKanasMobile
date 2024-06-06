package com.example.learnkanas.model
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

object DataStore {
    var hiraganaList: MutableList<Kana> = mutableListOf()
    var katakanaList: MutableList<Kana> = mutableListOf()

    fun loadKanas(context: Context) {
        hiraganaList = loadJson(context, "hiragana.json")
        katakanaList = loadJson(context, "katakana.json")
    }

    private fun loadJson(context: Context, fileName: String): MutableList<Kana> {
        val inputStream = context.assets.open(fileName)
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<Kana>>() {}.type
        return Gson().fromJson(reader, type)
    }

    fun getKana(listType: String, position: Int): Kana {
        return when (listType) {
            "hiragana" -> hiraganaList[position]
            "katakana" -> katakanaList[position]
            else -> throw IllegalArgumentException("Invalid list type")
        }
    }

    fun getSize(listType: String): Int {
        return when (listType) {
            "hiragana" -> hiraganaList.size
            "katakana" -> katakanaList.size
            else -> throw IllegalArgumentException("Invalid list type")
        }
    }

}