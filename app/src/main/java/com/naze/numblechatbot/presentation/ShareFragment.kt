package com.naze.numblechatbot.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.naze.numblechatbot.R
import com.naze.numblechatbot.data.local.model.ChatType
import com.naze.numblechatbot.databinding.FragmentShareBinding
import com.naze.numblechatbot.domain.viewmodel.ChatViewModel
import com.naze.numblechatbot.util.binding.BindingFragment
import com.naze.numblechatbot.util.extension.showToast
import kotlinx.coroutines.launch

class ShareFragment : BindingFragment<FragmentShareBinding>(R.layout.fragment_share) {

    private val viewModel : ChatViewModel by activityViewModels()
    private val chatAdapter = ShareAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBtnEvent()
        setAdapter()

        viewModel.chatToShare()
    }

    private fun setBtnEvent() {
        binding.btnShare.setOnClickListener {
            shareData()
            val transaction =parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fl, ChatFragment(), "Chat")
            transaction.commit()
        }

        binding.btnSetting.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fl, SettingFragment(),"Setting")
                addToBackStack(null)
                commit()
            }
        }

        binding.btnSend.setOnClickListener {
            if (binding.etChat.text.isNotEmpty()) {
                viewModel.question(binding.etChat.text.toString())
                binding.etChat.text.clear()
            } else {
                requireContext().showToast("빈 칸은 입력할 수 없습니다.")
            }
        }
    }

    private fun setAdapter() {
        binding.rvChatList.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        lifecycleScope.launch {
            viewModel.chatShare.observe(viewLifecycleOwner, Observer {
                chatAdapter.submitList(it)
                binding.rvChatList.scrollToPosition(it?.size?.minus(1) ?: 0)
            })
        }
    }

    private fun shareData() {
        val list = chatAdapter.currentList

        var shareText = ""

        for (i in list) {
            if (i.checked) {
                when (i.type) {
                    ChatType.QUESTION -> {
                        shareText += "[질문]: ${i.message} \n"
                        Log.d("TEST", "1")
                    }
                    ChatType.ANSWER -> {
                        shareText += "[응답]: ${i.message} \n"
                        Log.d("TEST", "2")
                    }
                    ChatType.ERROR -> {
                        shareText += "[에러]: ${i.message} \n"
                        Log.d("TEST", "3")
                    }
                    else -> {}
                }
            }
        }
        Log.d("TEST","list : ${shareText}")
        val intent= Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,shareText)
        startActivity(Intent.createChooser(intent,"공유하기"))
    }
}