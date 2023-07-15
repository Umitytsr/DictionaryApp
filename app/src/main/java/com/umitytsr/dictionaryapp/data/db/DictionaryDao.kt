package com.umitytsr.dictionaryapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.umitytsr.dictionaryapp.data.model.local.DictionaryWord
import com.umitytsr.dictionaryapp.data.model.remote.meaning.WordMeaningResponse

@Dao
interface DictionaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchHistory(word: DictionaryWord)

    @Query("SELECT * FROM dictionary_word ORDER BY idWord DESC LIMIT 5")
    suspend fun getRecentSearches(): List<DictionaryWord>

    @Query("DELETE FROM dictionary_word WHERE idWord " +
            "NOT IN (SELECT idWord FROM dictionary_word ORDER BY idWord DESC LIMIT 5)")
    suspend fun deleteOldSearches()
}