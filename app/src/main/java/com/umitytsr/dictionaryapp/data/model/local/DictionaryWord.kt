package com.umitytsr.dictionaryapp.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary_word")
data class DictionaryWord(
    @PrimaryKey(autoGenerate = true)
    val idWord: Int = 0,
    val word: String
)
