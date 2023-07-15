package com.umitytsr.dictionaryapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.umitytsr.dictionaryapp.data.model.local.DictionaryWord
import com.umitytsr.dictionaryapp.data.model.remote.meaning.WordMeaningResponse
import com.umitytsr.dictionaryapp.data.model.remote.synonyms.WordSynonymsResponse
import com.umitytsr.dictionaryapp.util.DatabaseConverter

@Database(
    entities = [DictionaryWord::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DatabaseConverter::class)
abstract class DictionaryDb: RoomDatabase() {
    abstract fun directionaryPropertyDao(): DictionaryDao
}