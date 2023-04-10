package com.naze.numblechatbot.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.naze.numblechatbot.R
import com.naze.numblechatbot.data.local.model.Chat
import com.naze.numblechatbot.databinding.FragmentChatBinding
import com.naze.numblechatbot.domain.viewmodel.ChatViewModel
import com.naze.numblechatbot.domain.viewmodel.SettingViewModel
import com.naze.numblechatbot.util.binding.BindingFragment
import com.naze.numblechatbot.util.extension.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ChatFragment : BindingFragment<FragmentChatBinding>(R.layout.fragment_chat) {

    private val viewModel : ChatViewModel by activityViewModels()
    private val settingViewModel: SettingViewModel by activityViewModels()
    private val chatAdapter = ChatAdapter()

    private var temperature: Double = 0.0
    private var frequencyPenalty: Double = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBtnEvent()
        setAdapter()

        viewModel.getChat()

        getDataStore()
    }

    private fun getDataStore() {
        lifecycleScope.launch {
            settingViewModel.getTemperature(requireContext()).collect {
                println("Temperature $it")
                temperature = it?:1.0
            }
        }

        lifecycleScope.launch {
            settingViewModel.getFrequencyPenalty(requireContext()).collect {
                println("Frequency $it")
                frequencyPenalty = it?:0.0
            }
        }
    }

    private fun setBtnEvent() {
        binding.btnSetting.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fl, SettingFragment(),"Setting")
                addToBackStack(null)
                commit()
            }
        }

        binding.btnSend.setOnClickListener {
            if (binding.etChat.text.isNotEmpty()) {
                viewModel.question(binding.etChat.text.toString(), temperature, frequencyPenalty)
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
            animation = null
        }

        lifecycleScope.launch {
            viewModel.chat.observe(viewLifecycleOwner, Observer {
                chatAdapter.submitList(it)
            })
        }
    }
}