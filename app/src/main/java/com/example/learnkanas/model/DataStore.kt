package com.example.learnkanas.model

object DataStore {
    var kanas: MutableList<Kana> = arrayListOf()

    init {
        addKana(Kana("あ", "a"))
        addKana(Kana("え", "e"))
        addKana(Kana("い", "i"))
        addKana(Kana("お", "o"))
        addKana(Kana("う", "u"))
    }

    fun getKana(position: Int): Kana{
        return kanas[position]
    }

    fun addKana(kana: Kana) {
        kanas.add(kana)
    }

    fun editKana(position: Int, kana: Kana){
        kanas[position] = kana
    }

    fun removeKana(position: Int){
        kanas.removeAt(position)
    }

    fun getSize(): Int{
        return kanas.size
    }
}