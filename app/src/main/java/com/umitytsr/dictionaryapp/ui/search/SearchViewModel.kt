package com.umitytsr.dictionaryapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umitytsr.dictionaryapp.data.model.local.DictionaryWord
import com.umitytsr.dictionaryapp.data.model.remote.meaning.WordMeaningResponse
import com.umitytsr.dictionaryapp.data.repo.DictionaryAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val dictionaryRepository: DictionaryAppRepository
) : ViewModel() {

    private val _searchHistory = MutableStateFlow<List<DictionaryWord>>(emptyList())
    val searchHistory: StateFlow<List<DictionaryWord>> = _searchHistory.asStateFlow()

    init {
        loadSearchHistory()
    }

    private fun loadSearchHistory() {
       viewModelScope.launch {
           dictionaryRepository.getRecentSearch().collect{
               _searchHistory.emit(it)
           }
       }
    }
    fun deleteOldSearch(){
        viewModelScope.launch {
            dictionaryRepository.deleteOldSearch()
        }
    }
}