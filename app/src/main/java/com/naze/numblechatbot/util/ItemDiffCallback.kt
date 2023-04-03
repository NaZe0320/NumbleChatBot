package com.naze.numblechatbot.util

import android.util.Log
import androidx.recyclerview.widget.DiffUtil

class ItemDiffCallback<T:Any>(
    val onItemsTheSame: (T, T) -> Boolean,
    val onContentsTheSame: (T, T) -> Boolean,
    val getChange: ((oldItem: T, newItem: T) -> Any?)? = null
): DiffUtil.ItemCallback<T>() {
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = onContentsTheSame(oldItem, newItem)
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = onItemsTheSame(oldItem, newItem)
    override fun getChangePayload(oldItem: T, newItem: T): Any? {
        val payload = getChange?.invoke(oldItem, newItem)
        Log.d("DiffCallback", "Payload: $payload")
        return payload
    }
}