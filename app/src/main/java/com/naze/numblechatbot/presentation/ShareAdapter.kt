package com.naze.numblechatbot.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.naze.numblechatbot.data.local.model.ChatType
import com.naze.numblechatbot.databinding.ItemChatAnswerShareBinding
import com.naze.numblechatbot.databinding.ItemChatErrorShareBinding
import com.naze.numblechatbot.databinding.ItemChatQuestionShareBinding
import com.naze.numblechatbot.domain.model.ChatShare
import com.naze.numblechatbot.util.ItemDiffCallback

class ShareAdapter(): ListAdapter<ChatShare, RecyclerView.ViewHolder>(
    ItemDiffCallback<ChatShare>(
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
            TYPE_ANSWER -> AnswerViewHolder(ItemChatAnswerShareBinding.inflate(inflater, parent, false))
            TYPE_QUESTION -> QuestionViewHolder(ItemChatQuestionShareBinding.inflate(inflater, parent,false))
            TYPE_ERROR -> ErrorViewHolder(ItemChatErrorShareBinding.inflate(inflater, parent,false))
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
        private val binding: ItemChatQuestionShareBinding,
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatShare) {
            binding.chat = item
            binding.tvChatQuestion.layoutParams.width = LayoutParams.WRAP_CONTENT
            binding.executePendingBindings()
        }

        fun clear() {
            binding.tvChatQuestion.text = null
        }
    }

    inner class AnswerViewHolder(
        private val binding: ItemChatAnswerShareBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatShare) {
            binding.chat = item
            binding.tvChatAnswer.layoutParams.width = LayoutParams.WRAP_CONTENT
            binding.executePendingBindings()
        }

        fun clear() {
            binding.tvChatAnswer.text = null
        }
    }

    inner class ErrorViewHolder(
        private val binding: ItemChatErrorShareBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatShare) {
            binding.chat = item
            binding.tvChatError.layoutParams.width = LayoutParams.WRAP_CONTENT
            binding.executePendingBindings()
        }

        fun clear() {
            binding.tvChatError.text = null
        }
    }
}