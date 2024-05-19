package com.example.learnkanas.view
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.widget.LinearLayout
//import androidx.recyclerview.widget.RecyclerView
//import com.example.learnkanas.databinding.AdapterKanasBinding
//import com.example.learnkanas.model.Kana
//
//class KanasAdapter(var kanas: MutableList<Kana>): RecyclerView.Adapter<KanasAdapter.KanaHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KanaHolder {
//        AdapterKanasBinding.inflate(LayoutInflater.from(parent.context), parent, false).run {
//            return KanaHolder(this)
//        }
//    }
//
//    override fun onBindViewHolder(holder: KanaHolder, position: Int) {
//        kanas[position].run {
//            holder.binding.kanaView.text = this.kana
//            holder.binding.portugueseView.text = this.portuguese
//        }
//    }
//
//    override fun getItemCount(): Int = 1
//
//    inner class KanaHolder(var binding: AdapterKanasBinding):
//        RecyclerView.ViewHolder(binding.root)
//}