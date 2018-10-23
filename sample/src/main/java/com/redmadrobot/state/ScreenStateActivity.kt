package com.redmadrobot.state

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.redmadrobot.R
import com.redmadrobot.lib.sd.base.State
import com.redmadrobot.lib.sd.base.StateDelegate
import com.redmadrobot.state.MyScreenState.*
import kotlinx.android.synthetic.main.activity_screen_state.*

class ScreenStateActivity : AppCompatActivity() {

    private lateinit var screenState: StateDelegate<MyScreenState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_state)
        screenState = StateDelegate(
                State(LOADING, listOf(progress_bar, text_loading), AnimateLoadingStrategy(container_screen_state)),
                State(CONTENT, listOf(content), PrevStateDependingStrategy(container_screen_state)),
                State(STUB, listOf(stub)),
                State(ERROR, listOf(error_button, text_error)),
                State(NO_INTERNET_CONNECTION, listOf(no_internet)),
                State(UPDATE_APP, listOf(update_button, text_update))
        )
        initViews()
    }

    private fun initViews() {
        button_show_loading.setOnClickListener {
            screenState.currentState = LOADING
        }

        button_show_content.setOnClickListener {
            screenState.currentState = CONTENT
        }

        button_show_error.setOnClickListener {
            screenState.currentState = ERROR
        }

        button_show_stub.setOnClickListener {
            screenState.currentState = STUB
        }

        button_show_internet_problem.setOnClickListener {
            screenState.currentState = NO_INTERNET_CONNECTION
        }

        button_show_update_app.setOnClickListener {
            screenState.currentState = UPDATE_APP
        }
    }
}