package com.umitytsr.dictionaryapp.data.model.remote.meaning

import com.google.gson.annotations.SerializedName

data class WordMeaningResponse(
    @SerializedName("license")
    val license: License,
    @SerializedName("meanings")
    val meanings: List<Meaning>,
    @SerializedName("phonetics")
    val phonetics: List<Phonetic>,
    @SerializedName("sourceUrls")
    val sourceUrls: List<String>,
    @SerializedName("word")
    val word: String
)