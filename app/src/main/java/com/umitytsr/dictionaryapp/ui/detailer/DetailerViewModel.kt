package com.umitytsr.dictionaryapp.ui.detailer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umitytsr.dictionaryapp.domain.model.TypeOfItemWord
import com.umitytsr.dictionaryapp.data.repo.DictionaryAppRepository
import com.umitytsr.dictionaryapp.domain.getTypeOfItemWord
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

    private val _wordMeaning = MutableStateFlow<List<TypeOfItemWord>>(emptyList())
    val wordMeaning: StateFlow<List<TypeOfItemWord>> = _wordMeaning.asStateFlow()

    fun getWordDetails(word: String) {
        viewModelScope.launch {
            val meaningDeferred = async { dictionaryAppRepository.getWordMeaning(word).first() }
            val synonymsDeferred =
                async { dictionaryAppRepository.getWordSynonyms(word).first() }
            val meaningResponse = meaningDeferred.await()
            val synonymsResponse = synonymsDeferred.await()

            val typeOfItemWord = getTypeOfItemWord(
                meaningResponse.first(),
                synonymsResponse
            )
            _wordMeaning.emit(typeOfItemWord)
        }
    }


    suspend fun insertWord(word: String) {
        dictionaryAppRepository.insertWordSearch(word)
    }

}