package com.umitytsr.dictionaryapp.data.model.remote.meaning

import com.google.gson.annotations.SerializedName

data class License(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)