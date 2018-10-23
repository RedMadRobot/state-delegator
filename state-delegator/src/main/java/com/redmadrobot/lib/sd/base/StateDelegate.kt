package com.redmadrobot.lib.sd.base

import kotlin.properties.Delegates

open class StateDelegate<T>(
        vararg states: State<T>,
        initialState: InitialState<T> = AllHideInitialState()
) where T : Enum<T> {

    init {
        initialState.apply(states)
    }

    private val mappings: Map<T, State<T>> = states.associateBy { it.name }

    var currentState: T? by Delegates.observable(null) { _, prevStateName: T?, newStateName: T? ->
        if (prevStateName == newStateName) return@observable
        val prevState = mappings[prevStateName]
        val newState = mappings[newStateName]
        prevState?.let { prevState.strategy.onStateExit(prevState, newState) }
        newState?.let { newState.strategy.onStateEnter(newState, prevState) }
    }
}