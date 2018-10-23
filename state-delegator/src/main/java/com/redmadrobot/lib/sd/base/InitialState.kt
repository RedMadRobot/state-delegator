package com.redmadrobot.lib.sd.base

interface InitialState<T : Enum<T>> {

    fun apply(states: Array<out State<T>>) {}
}