package com.redmadrobot.lib.sd.base

interface StateChangeStrategy<T : Enum<T>> {

    fun onStateEnter(state: State<T>, prevState: State<T>?) {}

    fun onStateExit(state: State<T>, nextState: State<T>?) {}
}