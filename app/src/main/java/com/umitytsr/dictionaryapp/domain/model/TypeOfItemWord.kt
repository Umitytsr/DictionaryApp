package com.umitytsr.dictionaryapp.domain.model

import com.umitytsr.dictionaryapp.data.model.remote.synonyms.WordSynonymsResponse

sealed class TypeOfItemWord {
    data class WordTitleUI(
        val titleWord: String,
        val meaningTitle: List<String>
    ) : TypeOfItemWord()

    data class MeaningUI(
        val number: Int,
        val partOfSpeech: String,
        val definition: String,
        val example: String?
    ) : TypeOfItemWord()

    data class SynonymsUI(
        val synonymsResponse: List<WordSynonymsResponse>
    ) : TypeOfItemWord()
}
