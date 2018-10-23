package com.redmadrobot.state

import android.support.v4.content.ContextCompat
import android.view.View
import com.redmadrobot.lib.sd.base.ShowOnEnterGoneOnExitStrategy
import com.redmadrobot.lib.sd.base.State

class PrevStateDependingStrategy(private val sceneRoot: View)
    : ShowOnEnterGoneOnExitStrategy<MyScreenState>() {

    override fun onStateEnter(state: State<MyScreenState>, prevState: State<MyScreenState>?) {
        super.onStateEnter(state, prevState)
        with(sceneRoot) {
            when (prevState) {
                MyScreenState.ERROR -> setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
                MyScreenState.LOADING -> setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_blue_bright))
                else -> setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))
            }
        }
    }

    override fun onStateExit(state: State<MyScreenState>, nextState: State<MyScreenState>?) {
        sceneRoot.setBackgroundColor(ContextCompat.getColor(sceneRoot.context, android.R.color.white))
        super.onStateExit(state, nextState)
    }
}