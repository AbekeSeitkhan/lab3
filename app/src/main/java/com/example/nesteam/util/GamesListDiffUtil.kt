package com.example.nesteam.util

import androidx.recyclerview.widget.DiffUtil
import com.example.nesteam.data.model.entity.Result

class GamesListDiffUtil : DiffUtil.ItemCallback<Result>(){
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}