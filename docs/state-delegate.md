If you have a complicated state of screen, you can use `StateDelegate`.
With `StateDelegate` you can specify as many states as you like:

```kotlin
val screenState = StateDelegate(
    State(LOADING, listOf(progress_bar, text_loading), AnimateLoadingStrategy(container_screen_state)),
    State(CONTENT, listOf(content), PrevStateDependingStrategy(container_screen_state)),
    State(STUB, listOf(stub)),
    State(ERROR, listOf(error_button, text_error)),
    State(NO_INTERNET_CONNECTION, listOf(no_internet)),
    State(UPDATE_APP, listOf(update_button, text_update))
)
```

The `State` class has the following constructor:

```kotlin
data class State<T>(
        val name: T,
        val viewsGroup: List<View>,
        val strategy: StateChangeStrategy<T> = ShowOnEnterGoneOnExitStrategy()
) where T : Enum<T>
```
where `name` is a tag of state; `viewsGroup` is a set of `View`, defining how a screen state looks like; `strategy` - business logic of a state screen, by default `strategy = ShowOnEnterGoneOnExitStrategy` - strategy that
changes `View` visibility the following way: if we switch to the state with the `name` tag, new visibility is `View.VISIBLE` to each `View` from `viewsGroup`. If we change the state to another, new visibility is `View.GONE`.

You can specify your strategies in `State` with overriding the interface `StateChangeStrategy`:

```kotlin
interface StateChangeStrategy<T : Enum<T>> {

    fun onStateEnter(state: State<T>, prevState: State<T>?) {}

    fun onStateExit(state: State<T>, nextState: State<T>?) {}
}
```

**Q**: In which cases `onStateEnter()` and `onStateExit()` are used?

**A**: These are the default methods in `StateChangeStrategy` interface, overriding them is optional. You can use these methods, if you need to start/stop animation.

**Q**: In which cases `prevState` and `newState` are used?

**A**: You can use these, if it is necessary to process transition between two states.

The `StateDelegate` constructor has the initial state:

```kotlin
open class StateDelegate<T>(
        vararg states: State<T>,
        initialState: InitialState<T> = AllHideInitialState()
) where T : Enum<T>
```

By default, the visibility `View.GONE` is assigned to all `View`.
