package com.umitytsr.dictionaryapp.domain

import com.umitytsr.dictionaryapp.data.model.remote.meaning.WordMeaningResponse
import com.umitytsr.dictionaryapp.data.model.remote.synonyms.WordSynonymsResponse
import com.umitytsr.dictionaryapp.domain.model.TypeOfItemWord

fun getTypeOfItemWord(
    wordMeaningResponse: WordMeaningResponse,
    wordSynonymsResponseList: List<WordSynonymsResponse>
): List<TypeOfItemWord> {
    val list = arrayListOf<TypeOfItemWord>()
    val meanings = wordMeaningResponse.meanings

    meanings.map {
        it.partOfSpeech
    }.let {
        val wordTitleUI = TypeOfItemWord.WordTitleUI(wordMeaningResponse.word, it)
        list.add(wordTitleUI)
    }

    meanings.forEach { meaning ->
        meaning.definitions.forEachIndexed { nestedIndex, definitions ->
            val meaningUI = TypeOfItemWord.MeaningUI(
                number = nestedIndex + 1,
                partOfSpeech = meaning.partOfSpeech,
                definition = definitions.definition,
                example = definitions.example
            )
            list.add(meaningUI)
        }
    }

    TypeOfItemWord.SynonymsUI(wordSynonymsResponseList).also { synonymsUI ->
        list.add(synonymsUI)
    }

    return list
}