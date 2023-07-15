package com.umitytsr.dictionaryapp.domain

import com.umitytsr.dictionaryapp.data.model.local.DictionaryWord

fun String.toDictionaryWord(): DictionaryWord {
    return DictionaryWord(word = this)
}