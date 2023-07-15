package com.umitytsr.dictionaryapp.data.model.remote.meaning

import com.google.gson.annotations.SerializedName

data class Meaning(
    @SerializedName("antonyms")
    val antonyms: List<String>,
    @SerializedName("definitions")
    val definitions: List<Definition>,
    @SerializedName("partOfSpeech")
    val partOfSpeech: String,
    @SerializedName("synonyms")
    val synonyms: List<String>
)