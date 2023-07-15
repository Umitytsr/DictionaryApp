package com.umitytsr.dictionaryapp.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "dictionary_word")
data class DictionaryWord(
    @PrimaryKey()
    val word: String,
    val addDate: String = Calendar.getInstance().time.toString()
)
