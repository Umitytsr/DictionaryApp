package com.umitytsr.dictionaryapp.data.service

import com.umitytsr.dictionaryapp.data.model.remote.synonyms.WordSynonymsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SynonymsApiService {
    @GET("words?rel_syn={word}")
    suspend fun getWordSynonyms(@Query("word") word: String): List<WordSynonymsResponse>
}