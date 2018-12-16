package com.redmadrobot

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.redmadrobot.action.ActionActivity
import com.redmadrobot.loading.LoadingActivity
import com.redmadrobot.state.ScreenStateActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_open_loading_state_activity.setOnClickListener {
            startActivity(Intent(this, LoadingActivity::class.java))
        }
        button_open_screen_state_activity.setOnClickListener {
            startActivity(Intent(this, ScreenStateActivity::class.java))
        }
        button_open_action_activity.setOnClickListener {
            startActivity(Intent(this, ActionActivity::class.java))
        }
    }
}
