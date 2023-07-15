package com.umitytsr.dictionaryapp.data.source.remote

import com.umitytsr.dictionaryapp.data.service.MeaningApiService
import com.umitytsr.dictionaryapp.data.service.SynonymsApiService
import javax.inject.Inject

class DictionaryRemoteDataSource @Inject constructor(
    private val meaningApiService: MeaningApiService,
    private val synonymsApiService: SynonymsApiService
) {
    suspend fun getWordMeaning(word: String) =  meaningApiService.getWordMeaning(word)
    suspend fun getWordSynonyms(word: String) = synonymsApiService.getWordSynonyms(word)
}