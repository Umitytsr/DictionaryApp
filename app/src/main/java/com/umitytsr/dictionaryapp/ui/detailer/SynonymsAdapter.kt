package com.umitytsr.dictionaryapp.ui.detailer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.umitytsr.dictionaryapp.data.model.remote.synonyms.WordSynonymsResponse
import com.umitytsr.dictionaryapp.databinding.ItemRowSynonymslistBinding

class SynonymsAdapter(private val synonyms: List<WordSynonymsResponse>) :
    RecyclerView.Adapter<SynonymsAdapter.ChildViewHolder>() {

    inner class ChildViewHolder(private val binding: ItemRowSynonymslistBinding) :
        ViewHolder(binding.root) {
        fun bind(synonyms: WordSynonymsResponse) {
            binding.wordSynonyms.text = synonyms.word
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRowSynonymslistBinding.inflate(layoutInflater, parent, false)

        return ChildViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return synonyms.size
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        holder.bind(synonyms[position])
    }
}