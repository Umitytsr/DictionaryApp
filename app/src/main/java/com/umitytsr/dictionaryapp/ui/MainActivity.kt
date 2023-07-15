package com.umitytsr.dictionaryapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umitytsr.dictionaryapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}