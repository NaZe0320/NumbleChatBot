package com.naze.numblechatbot.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.naze.numblechatbot.R
import com.naze.numblechatbot.databinding.FragmentSettingBinding
import com.naze.numblechatbot.domain.viewmodel.ChatViewModel
import com.naze.numblechatbot.domain.viewmodel.SettingViewModel
import com.naze.numblechatbot.util.binding.BindingFragment
import kotlinx.coroutines.launch

class SettingFragment : BindingFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    private val viewModel : ChatViewModel by activityViewModels()
    private val settingViewModel: SettingViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTextView()
        setBtn()
        setDataStore()
    }

    private fun setDataStore() {
        binding.etTemperature.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (binding.etTemperature.text.isNotEmpty()) {
                    var n = binding.etTemperature.text.toString().toDouble()
                    if (n < 0.0) {
                        n = 0.0
                        binding.tvTemperature.text = "Temperature (current: $n)"
                        binding.etTemperature.setText("$n")
                    } else if (n > 2.0) {
                        n = 2.0
                        binding.tvTemperature.text = "Temperature (current: $n)"
                        binding.etTemperature.setText("$n")
                    }
                    settingViewModel.saveTemperature(requireContext(), n)
                }
            }
        }
        binding.etFrequencyPenalty.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if( binding.etFrequencyPenalty.text.isNotEmpty() ) {
                    var n = binding.etFrequencyPenalty.text.toString().toDouble()
                    if (n < -2.0) {
                        n = -2.0
                        binding.tvFrequencyPenalty.text = "Frequency Penalty (current: $n)"
                        binding.etFrequencyPenalty.setText("$n")
                    } else if (n > 2.0) {
                        n = 2.0
                        binding.tvFrequencyPenalty.text = "Frequency Penalty (current: $n)"
                        binding.etFrequencyPenalty.setText("$n")
                    }
                    settingViewModel.saveFrequencyPenalty(requireContext(), n)
                }
            }
        }
    }

    private fun setTextView() {
        binding.tvTemperature.text = "Temperature (current: ${viewModel.temperature})"
        binding.tvFrequencyPenalty.text = "Frequency Penalty (current: ${viewModel.frequencyPenalty})"
    }
    private fun setBtn() {
        binding.btnChatReset.setOnClickListener {
            viewModel.deleteAllChat()
        }
    }
}