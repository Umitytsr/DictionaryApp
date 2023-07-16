package com.umitytsr.dictionaryapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umitytsr.dictionaryapp.data.model.local.DictionaryWord
import com.umitytsr.dictionaryapp.databinding.ItemRowHistoryBinding

class SearchAdapter(
    private val wordHistory: List<DictionaryWord>,
    private val dictionaryItemClickListener: DictionaryItemClickListener
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    inner class SearchViewHolder(private val binding: ItemRowHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(wordHistory: DictionaryWord) {
            val word = wordHistory.word.lowercase()
            with(binding) {
                historyTextView.text = word
                historyCardView.setOnClickListener {
                    dictionaryItemClickListener.dictionaryItemClicked(word)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRowHistoryBinding.inflate(layoutInflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return wordHistory.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(wordHistory[position])
    }

    interface DictionaryItemClickListener {
        fun dictionaryItemClicked(word: String)
    }
}