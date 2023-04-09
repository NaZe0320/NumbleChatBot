package com.naze.numblechatbot.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.naze.numblechatbot.R
import com.naze.numblechatbot.databinding.FragmentSettingBinding
import com.naze.numblechatbot.domain.viewmodel.ChatViewModel
import com.naze.numblechatbot.util.binding.BindingFragment

class SettingFragment : BindingFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    private val viewModel : ChatViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBtn()
    }

    private fun setBtn() {
        binding.btnChatReset.setOnClickListener {
            viewModel.deleteAllChat()
        }
    }
}