package com.umitytsr.dictionaryapp.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.umitytsr.dictionaryapp.domain.model.TypeOfItemWord

abstract class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(typeOfItemWord: TypeOfItemWord)
}