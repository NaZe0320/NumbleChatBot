package com.naze.numblechatbot.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.naze.numblechatbot.R
import com.naze.numblechatbot.databinding.ActivityMainBinding
import com.naze.numblechatbot.util.binding.BindingActivity
import com.naze.numblechatbot.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragment(ChatFragment(),"Chat")
    }


    private fun setFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl, fragment, tag)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        for (fragment: Fragment in supportFragmentManager.fragments) {
            when (fragment.tag) {
                "Chat" -> {
                    val currentTime = System.currentTimeMillis()
                    if (currentTime - backPressedTime < 2000) {
                        super.onBackPressed()
                        finish()
                    } else {
                        backPressedTime = currentTime
                        this.showToast("한 번 더 누르면 종료됩니다.")
                    }
                }
                "Share" -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fl, ChatFragment(), "Chat")
                    transaction.commit()
                }
                else -> {
                    supportFragmentManager.popBackStackImmediate()
                }
            }
        }
    }
}