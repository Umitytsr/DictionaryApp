package com.umitytsr.dictionaryapp.ui.detailer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umitytsr.dictionaryapp.R
import com.umitytsr.dictionaryapp.data.model.remote.meaning.WordMeaningResponse
import com.umitytsr.dictionaryapp.databinding.ItemRowMeaningBinding
import com.umitytsr.dictionaryapp.databinding.ItemRowWordBinding
import com.umitytsr.dictionaryapp.util.Constants

class DetailerAdapter(wordMeaning: WordMeaningResponse): RecyclerView.Adapter<DetailerAdapter.WordViewHolder>() {

    private val word = wordMeaning.word
    private val partOfSpeech = wordMeaning.meanings

    inner class WordViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(
            word: String,
            partOfSpeech: String,
            definition: String
            ){
            if (adapterPosition == Constants.ITEM_WORD){
                val binding = ItemRowWordBinding.bind(itemView)
                binding.wordText.text = word
            }else if (adapterPosition == Constants.ITEM_MEANING){
                val binding = ItemRowMeaningBinding.bind(itemView)
                with(binding) {
                    partOfSpeechText.text = partOfSpeech
                    wordMeaning.text = definition
                }
            }else{

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view : View = when(viewType){
            Constants.ITEM_WORD -> layoutInflater.inflate(R.layout.item_row_word,parent,false)
            Constants.ITEM_MEANING -> layoutInflater.inflate(R.layout.item_row_meaning,parent,false)
            else -> throw IllegalArgumentException()
        }
        return WordViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(
            word = word[position],
            partOfSpeech = partOfSpeech[position]
        )
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            Constants.ITEM_WORD
        }else{
            Constants.ITEM_MEANING
        }
    }
}