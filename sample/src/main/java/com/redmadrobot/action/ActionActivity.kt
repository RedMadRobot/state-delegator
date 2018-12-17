package com.redmadrobot.action

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.redmadrobot.R
import kotlinx.android.synthetic.main.activity_action.*

class ActionActivity : AppCompatActivity() {

    private lateinit var screenState: PostActionStateDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action)
        screenState = PostActionStateDelegate(action_progress)

        action_submit_btn.setOnClickListener {
            screenState.showLoading()
            action_submit_btn.postDelayed({ finish() }, 5000L)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        screenState.hideLoading()
    }
}