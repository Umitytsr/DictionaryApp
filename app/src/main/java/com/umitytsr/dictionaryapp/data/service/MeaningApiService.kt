package com.umitytsr.dictionaryapp.data.service

import com.umitytsr.dictionaryapp.data.model.remote.meaning.WordMeaningResponse
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Named

interface MeaningApiService {
    @GET("v2/entries/en/{word}")
    suspend fun getWordMeaning(@Path("word") word: String) : WordMeaningResponse
}