package com.umitytsr.dictionaryapp.ui.detailer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umitytsr.dictionaryapp.data.model.remote.meaning.WordMeaningResponse
import com.umitytsr.dictionaryapp.data.model.remote.synonyms.WordSynonymsResponse
import com.umitytsr.dictionaryapp.data.repo.DictionaryAppRepository
import com.umitytsr.dictionaryapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailerViewModel @Inject constructor(
    private val dictionaryAppRepository: DictionaryAppRepository
) : ViewModel() {

    private val _wordMeaning = MutableStateFlow<List<WordMeaningResponse>>(emptyList())
    val wordMeaning: StateFlow<List<WordMeaningResponse>> = _wordMeaning.asStateFlow()

    private val _wordSynonyms = MutableStateFlow<List<WordSynonymsResponse>>(emptyList())
    val wordSynonyms: StateFlow<List<WordSynonymsResponse>> = _wordSynonyms.asStateFlow()

    fun getWordMeaning(word: String) {
        viewModelScope.launch {
            dictionaryAppRepository.getWordMeaning(word).collect{
                _wordMeaning.emit(listOf(it))
            }
        }
    }

    fun getWordSynonyms(word: String) {
        viewModelScope.launch {
            dictionaryAppRepository.getWordSynonyms(word).collect{
                _wordSynonyms.emit(it)
            }
        }
    }

    suspend fun a(word:String){
        dictionaryAppRepository.insertWordSearch(word)
    }

}