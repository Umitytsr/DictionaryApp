package com.umitytsr.dictionaryapp.data.model.remote.synonyms

import com.google.gson.annotations.SerializedName

data class WordSynonymsResponse(
    @SerializedName("score")
    val score: Int,
    @SerializedName("word")
    val word: String
)