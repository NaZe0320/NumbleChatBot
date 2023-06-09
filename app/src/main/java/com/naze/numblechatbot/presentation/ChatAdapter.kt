package com.naze.numblechatbot.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.naze.numblechatbot.data.local.model.Chat
import com.naze.numblechatbot.data.local.model.ChatType
import com.naze.numblechatbot.databinding.ItemChatAnswerBinding
import com.naze.numblechatbot.databinding.ItemChatErrorBinding
import com.naze.numblechatbot.databinding.ItemChatQuestionBinding
import com.naze.numblechatbot.util.ItemDiffCallback

class ChatAdapter(): ListAdapter<Chat, RecyclerView.ViewHolder>(
    ItemDiffCallback<Chat>(
        onContentsTheSame = {old, new -> old == new},
        onItemsTheSame = { old, new -> old.id == new.id},
    )
) {
    companion object {
        const val TYPE_QUESTION = 0
        const val TYPE_ANSWER = 1
        const val TYPE_ERROR = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_ANSWER -> AnswerViewHolder(ItemChatAnswerBinding.inflate(inflater, parent, false))
            TYPE_QUESTION -> QuestionViewHolder(ItemChatQuestionBinding.inflate(inflater, parent,false))
            TYPE_ERROR -> ErrorViewHolder(ItemChatErrorBinding.inflate(inflater, parent,false))
            else -> throw IllegalArgumentException("Unknown viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is QuestionViewHolder -> {
                holder.clear()
                holder.bind(item)
            }
            is AnswerViewHolder -> {
                holder.clear()
                holder.bind(item)
            }
            is ErrorViewHolder -> {
                holder.clear()
                holder.bind(item)
            }
            else -> throw IllegalArgumentException("Unknown ViewHolder type: ${holder.javaClass.simpleName}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type) {
            ChatType.ANSWER -> TYPE_ANSWER
            ChatType.QUESTION -> TYPE_QUESTION
            ChatType.ERROR -> TYPE_ERROR
        }
    }

    inner class QuestionViewHolder(
        private val binding: ItemChatQuestionBinding,
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Chat) {
            binding.chat = item
            binding.tvChatQuestion.text = item.message
            binding.tvChatQuestion.layoutParams.width = LayoutParams.WRAP_CONTENT
            binding.executePendingBindings()
        }

        fun clear() {
            binding.tvChatQuestion.text = null
        }
    }

    inner class AnswerViewHolder(
        private val binding: ItemChatAnswerBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Chat) {
            binding.chat = item
            binding.tvChatAnswer.text = item.message
            binding.tvChatAnswer.layoutParams.width = LayoutParams.WRAP_CONTENT
            binding.executePendingBindings()
        }

        fun clear() {
            binding.tvChatAnswer.text = null
        }
    }

    inner class ErrorViewHolder(
        private val binding: ItemChatErrorBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Chat) {
            binding.chat = item
            binding.tvChatError.text = item.message
            binding.tvChatError.layoutParams.width = LayoutParams.WRAP_CONTENT
            binding.executePendingBindings()
        }

        fun clear() {
            binding.tvChatError.text = null
        }
    }
}