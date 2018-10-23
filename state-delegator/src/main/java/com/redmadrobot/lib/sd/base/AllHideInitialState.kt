package com.redmadrobot.lib.sd.base

import android.view.View

class AllHideInitialState<T : Enum<T>> : InitialState<T> {

    override fun apply(states: Array<out State<T>>) {
        states.forEach { state -> state.viewsGroup.forEach { it.visibility = View.GONE } }
    }
}