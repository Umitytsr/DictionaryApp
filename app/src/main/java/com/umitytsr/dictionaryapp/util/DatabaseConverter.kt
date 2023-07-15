package com.umitytsr.dictionaryapp.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.umitytsr.dictionaryapp.data.model.remote.meaning.Definition
import com.umitytsr.dictionaryapp.data.model.remote.meaning.License
import com.umitytsr.dictionaryapp.data.model.remote.meaning.Meaning
import com.umitytsr.dictionaryapp.data.model.remote.meaning.Phonetic

class DatabaseConverter{
    private val gson = Gson()

    @TypeConverter
    fun fromLicenseJson(json: String): License {
        val type = object : TypeToken<License>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun toLicenseJson(license: License): String {
        return gson.toJson(license)
    }

    @TypeConverter
    fun fromPhoneticListJson(json: String): List<Phonetic> {
        val type = object : TypeToken<List<Phonetic>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun toPhoneticListJson(phonetics: List<Phonetic>): String {
        return gson.toJson(phonetics)
    }

    @TypeConverter
    fun fromMeaningListJson(json: String): List<Meaning> {
        val type = object : TypeToken<List<Meaning>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun toMeaningListJson(meanings: List<Meaning>): String {
        return gson.toJson(meanings)
    }

    @TypeConverter
    fun fromStringListJson(json: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun toStringListJson(strings: List<String>): String {
        return gson.toJson(strings)
    }

    @TypeConverter
    fun fromDefinitionListJson(json: String): List<Definition> {
        val type = object : TypeToken<List<Definition>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun toDefinitionListJson(definitions: List<Definition>): String {
        return gson.toJson(definitions)
    }
}