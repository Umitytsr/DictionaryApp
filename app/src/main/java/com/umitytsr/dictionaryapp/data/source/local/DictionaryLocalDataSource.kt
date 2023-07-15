package com.umitytsr.dictionaryapp.data.source.local

import com.umitytsr.dictionaryapp.data.db.DictionaryDao
import com.umitytsr.dictionaryapp.data.model.local.DictionaryWord
import javax.inject.Inject

class DictionaryLocalDataSource @Inject constructor(private val dictionaryDao: DictionaryDao) {
    suspend fun insertWordMeaningResponse(word: DictionaryWord) {
        dictionaryDao.insertSearchHistory(word)
    }

    suspend fun getRecentSearches(): List<DictionaryWord> {
        return dictionaryDao.getRecentSearches()
    }

    suspend fun deleteOldSearches() {
        dictionaryDao.deleteOldSearches()
    }
}