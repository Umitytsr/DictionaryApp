package com.umitytsr.dictionaryapp.ui.detailer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umitytsr.dictionaryapp.databinding.ItemRowMeaningBinding
import com.umitytsr.dictionaryapp.databinding.ItemRowSynonymsBinding
import com.umitytsr.dictionaryapp.databinding.ItemRowWordBinding
import com.umitytsr.dictionaryapp.domain.firstCharToUpperCase
import com.umitytsr.dictionaryapp.domain.model.TypeOfItemWord
import com.umitytsr.dictionaryapp.util.Constants
import com.umitytsr.dictionaryapp.util.MyViewHolder

class DetailerAdapter(
    private val wordMeanings: List<TypeOfItemWord>
) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            Constants.ITEM_WORD -> {
                val binding = ItemRowWordBinding.inflate(layoutInflater, parent, false)
                WordTitleViewHolder(binding)
            }

            Constants.ITEM_MEANING -> {
                val binding = ItemRowMeaningBinding.inflate(layoutInflater, parent, false)
                MeaningViewHolder(binding)
            }

            Constants.ITEM_SYNONYMS -> {
                val binding = ItemRowSynonymsBinding.inflate(layoutInflater, parent, false)
                SynonymsViewHolder(binding)
            }

            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(wordMeanings[position])
    }

    override fun getItemCount(): Int {
        return wordMeanings.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                Constants.ITEM_WORD
            }

            wordMeanings.lastIndex -> {
                Constants.ITEM_SYNONYMS
            }

            else -> {
                Constants.ITEM_MEANING
            }
        }
    }

    inner class WordTitleViewHolder(
        private val binding: ItemRowWordBinding
    ) : MyViewHolder(binding.root) {
        override fun bind(typeOfItemWord: TypeOfItemWord) {
            val wordTitleUI = (typeOfItemWord as? TypeOfItemWord.WordTitleUI)
            wordTitleUI?.let { wordTitle ->
                binding.wordText.text = wordTitle.titleWord.firstCharToUpperCase()
            }
        }
    }

    inner class MeaningViewHolder(
        private val binding: ItemRowMeaningBinding
    ) : MyViewHolder(binding.root) {
        override fun bind(typeOfItemWord: TypeOfItemWord) {
            val meaningUI = (typeOfItemWord as? TypeOfItemWord.MeaningUI)
            meaningUI?.let { meaning ->
                with(binding) {
                    wordId.text = meaning.number.toString().plus(" - ")
                    partOfSpeechText.text = meaning.partOfSpeech.firstCharToUpperCase()
                    wordMeaning.text = meaning.definition
                }
            }
        }

    }

    inner class SynonymsViewHolder(
        private val binding: ItemRowSynonymsBinding
    ) : MyViewHolder(binding.root) {
        override fun bind(typeOfItemWord: TypeOfItemWord) {
            val synonymsUI = (typeOfItemWord as? TypeOfItemWord.SynonymsUI)
            synonymsUI?.let { synonyms ->

            }
        }
    }

}