package com.umitytsr.dictionaryapp.data.repo

import com.umitytsr.dictionaryapp.data.model.local.DictionaryWord
import com.umitytsr.dictionaryapp.data.model.remote.meaning.WordMeaningResponse
import com.umitytsr.dictionaryapp.data.model.remote.synonyms.WordSynonymsResponse
import com.umitytsr.dictionaryapp.data.source.local.DictionaryLocalDataSource
import com.umitytsr.dictionaryapp.data.source.remote.DictionaryRemoteDataSource
import com.umitytsr.dictionaryapp.domain.toDictionaryWord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DictionaryAppRepository @Inject constructor(
    private val remoteDataSource: DictionaryRemoteDataSource,
    private val localDataSource: DictionaryLocalDataSource
){
    suspend fun getWordMeaning(word: String) = flow {
        val propertiesMeaning = remoteDataSource.getWordMeaning(word)
        emit(propertiesMeaning)
    }

    suspend fun getWordSynonyms(word: String) = flow {
        val propertiesSynonyms = remoteDataSource.getWordSynonyms(word)
        emit(propertiesSynonyms)
    }

    suspend fun getRecentSearch(): Flow<List<DictionaryWord>> = flow {
        emit(localDataSource.getRecentSearches())
    }

    suspend fun deleteOldSearch(){
        localDataSource.deleteOldSearches()
    }

    suspend fun insertWordSearch(word: String){
        val dictionaryWord = word.toDictionaryWord()
        localDataSource.insertWordMeaningResponse(dictionaryWord)
    }
}