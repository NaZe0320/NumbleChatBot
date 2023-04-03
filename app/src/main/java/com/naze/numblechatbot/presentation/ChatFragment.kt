package com.naze.numblechatbot.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.naze.numblechatbot.R
import com.naze.numblechatbot.databinding.FragmentChatBinding
import com.naze.numblechatbot.util.binding.BindingFragment

class ChatFragment : BindingFragment<FragmentChatBinding>(R.layout.fragment_chat) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSetting.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fl, SettingFragment(),"Setting")
                addToBackStack(null)
                commit()
            }
        }
    }
}