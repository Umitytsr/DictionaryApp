package com.umitytsr.dictionaryapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.umitytsr.dictionaryapp.data.model.local.DictionaryWord

@Dao
interface DictionaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchHistory(word: DictionaryWord)

    @Query("SELECT * FROM dictionary_word ORDER BY addDate DESC LIMIT 5")
    suspend fun getRecentSearches(): List<DictionaryWord>

    @Query("DELETE FROM dictionary_word WHERE addDate " +
            "NOT IN (SELECT addDate FROM dictionary_word ORDER BY addDate DESC LIMIT 5)")
    suspend fun deleteOldSearches()
}