package com.umitytsr.dictionaryapp.ui.detailer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umitytsr.dictionaryapp.data.model.remote.meaning.WordMeaningResponse
import com.umitytsr.dictionaryapp.data.model.remote.synonyms.WordSynonymsResponse
import com.umitytsr.dictionaryapp.data.repo.DictionaryAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailerViewModel @Inject constructor(
    private val dictionaryAppRepository: DictionaryAppRepository
) : ViewModel() {

    @HiltViewModel
    class DetailerViewModel @Inject constructor(
        private val dictionaryAppRepository: DictionaryAppRepository
    ) : ViewModel() {

        private val _wordMeaning = MutableStateFlow<List<WordMeaningResponse>>(emptyList())
        val wordMeaning: StateFlow<List<WordMeaningResponse>> = _wordMeaning.asStateFlow()

        private val _wordSynonyms = MutableStateFlow<List<WordSynonymsResponse>>(emptyList())
        val wordSynonyms: StateFlow<List<WordSynonymsResponse>> = _wordSynonyms.asStateFlow()

        fun getWordDetails(word: String) {
            viewModelScope.launch {
                val meaningDeferred = async { dictionaryAppRepository.getWordMeaning(word).first() }
                val synonymsDeferred =
                    async { dictionaryAppRepository.getWordSynonyms(word).first() }
                val meaning = meaningDeferred.await()
                val synonyms = synonymsDeferred.await()

                _wordMeaning.emit(listOf(meaning))
                _wordSynonyms.emit(synonyms)

                createData(meaning, synonyms)
            }
        }

        fun createData(
            wordMeaningResponse: WordMeaningResponse,
            wordSynonymsResponseList: List<WordSynonymsResponse>
        ) {
            // handle data here
        }

        suspend fun a(word: String) {
            dictionaryAppRepository.insertWordSearch(word)
        }
    }
}