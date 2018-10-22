package com.redmadrobot.lib.sd.base

import android.view.View

open class ShowOnEnterGoneOnExitStrategy<T : Enum<T>> : StateChangeStrategy<T> {

    override fun onStateEnter(state: State<T>, prevState: State<T>?) {
        state.viewsGroup.forEach { it.visibility = View.VISIBLE }
    }

    override fun onStateExit(state: State<T>, nextState: State<T>?) {
        state.viewsGroup.forEach { it.visibility = View.GONE }
    }
}