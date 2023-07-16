package com.umitytsr.dictionaryapp.di

import android.content.Context
import androidx.room.Room
import com.umitytsr.dictionaryapp.data.db.DictionaryDao
import com.umitytsr.dictionaryapp.data.db.DictionaryDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMovieDb(@ApplicationContext context: Context): DictionaryDb {
        return Room.databaseBuilder(
            context,
            DictionaryDb::class.java,
            "dictionary_database",
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(dictionaryDb: DictionaryDb): DictionaryDao {
        return dictionaryDb.directionaryPropertyDao()
    }
}