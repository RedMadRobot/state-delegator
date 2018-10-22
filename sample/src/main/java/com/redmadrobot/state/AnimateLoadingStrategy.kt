package com.redmadrobot.state

import android.view.View
import android.view.animation.AnimationUtils
import com.redmadrobot.R
import com.redmadrobot.lib.sd.base.ShowOnEnterGoneOnExitStrategy
import com.redmadrobot.lib.sd.base.State
import kotlinx.android.synthetic.main.activity_screen_state.view.*

class AnimateLoadingStrategy(private val sceneRoot: View)
    : ShowOnEnterGoneOnExitStrategy<MyScreenState>() {

    override fun onStateEnter(state: State<MyScreenState>, prevState: State<MyScreenState>?) {
        super.onStateEnter(state, prevState)
        with(sceneRoot) {
            text_loading.y = text_loading.y + 200
            text_loading.animate().translationYBy(-200f)
            text_loading.startAnimation(AnimationUtils.loadAnimation(context, R.anim.blink))
        }
    }

    override fun onStateExit(state: State<MyScreenState>, nextState: State<MyScreenState>?) {
        sceneRoot.text_loading.clearAnimation()
        super.onStateExit(state, nextState)
    }
}