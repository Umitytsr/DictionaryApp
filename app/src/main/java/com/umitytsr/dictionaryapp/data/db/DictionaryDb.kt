package com.umitytsr.dictionaryapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.umitytsr.dictionaryapp.data.model.local.DictionaryWord

@Database(
    entities = [DictionaryWord::class],
    version = 1,
    exportSchema = false
)
abstract class DictionaryDb: RoomDatabase() {
    abstract fun directionaryPropertyDao(): DictionaryDao
}