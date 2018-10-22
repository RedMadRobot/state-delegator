# state-delegator

`state-delegator` - is set of classes that helps you to manage screen state.

### Example

Changing the screen state, you always have to show/hide certain views:

```kotlin
when (state) {
    LOADING -> {
        loading_view.visibility = View.VISIBLE
        content_view.visibility = View.GONE
        stub_view.visibility = View.GONE
    }
    CONTENT -> {
        loading_view.visibility = View.GONE
        content_view.visibility = View.VISIBLE
        stub_view.visibility = View.GONE
    }
    STUB -> {
        loading_view.visibility = View.GONE
        content_view.visibility = View.GONE
        stub_view.visibility = View.VISIBLE
    }
}
```

When expanding functionality, you have to fix boilerplate code every time.
To reduce a boilerplate code we use `LoadingStateDelegate` from `state-delegator`:

```kotlin
val screenState = LoadingStateDelegate(content_view, loading_view, stub_view)
...
when (state) {
    LOADING -> screenState.showLoading() 
    CONTENT -> screenState.showContent()
    STUB -> screenState.showStub()
}
```

With `LoadingStateDelegate` the source code becomes cleaner and much more compact.

#### LoadingStateDelegate

To control the visibility of the content, loading, zero-screen, `LoadingStateDelegate` must be initialized with the appropriate views:

```kotlin
class LoadingStateDelegate(
        private val contentView: View? = null,
        private val loadingView: View? = null,
        private val stubView: View? = null
): StateDelegate
```

where `contentView` is the content view,` loadingView` is the progress view, `stubView` is the zero-screen view.

To show the loading status, you must call the `showLoading()` method, to show the content view  — `showContent()`, to show the zero-screen view — `showStub(stubState: StubState? = null)`, where `StubState` — class that helps you to initialize the zero-screen, by default you can omit it.

Also you can use constructor with an array of `View`:

```kotlin
class LoadingStateDelegate(
        private val contentView: List<View>,
        private val loadingView: List<View>,
        private val stubView: List<View>
): StateDelegate
```

You should use the constructor above, when `View`, included in` contentView`, `loadingView` or in` stubView` arrays, don't have a common parent `View` or they are scattered around the `layout`. If you use `constraint-layout` it is recommended to use in this case the class `Group` and the standard constructor `LoadingStateDelegate`.

When working with `LoadingStateDelegate` in Fragment/ Activity, don't use the `by lazy`:

```kotlin
class LoadingActivity  : AppCompatActivity() {

    // don't do that 
    private val screenState by lazy { LoadingStateDelegate(content_view, loading_view, stub_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
    }
...
```

This can lead to errors. If we return to `Activity` from back stack, the `View` is recreated in this case, but the delegate who already refers to the old` View` is not. Use `lateinit` and re-create the delegate every time in the component lifecycle methods.

#### StateDelegate

If you have complicated states of screen, you can use `StateDelegate`
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
where `name` is a tag of state; `viewsGroup` is a set of` View`, defining how the screen state will look like; `strategy` - business logic of a state, by default`strategy = ShowOnEnterGoneOnExitStrategy` - strategy that
changes the `View` visibility following way: if we switched to the state with the`name` tag, new visibility is `View.VISIBLE` to each` View` from `viewsGroup`, if we change the state to another - new visibility is `View.GONE`.

You can specify your strategies in `State` with overriding the interface `StateChangeStrategy`:

```kotlin
interface StateChangeStrategy<T : Enum<T>> {

    fun onStateEnter(state: State<T>, prevState: State<T>?) {}

    fun onStateExit(state: State<T>, nextState: State<T>?) {}
}
```

**Q**: Why are `onStateEnter()` and `onStateExit()` needed?
**A**: These are the default methods in the `StateChangeStrategy` interface, overriding them is optional. You can use this methods, if you need to start/stop animation at changing state.

**Q**: Why is the `prevState` and `newState` passed in `onStateEnter()` and `onStateExit()`?
**A**: You can use this, if it is necessary to process transition between two states.

The `StateDelegate` constructor has the initial state `initialState`:

```kotlin
open class StateDelegate<T>(
        vararg states: State<T>,
        initialState: InitialState<T> = AllHideInitialState()
) where T : Enum<T>
```

By default, all `View` are assigned the visibility `View.GONE` when a `StateDelegate` is created.

#### How to add?

You need add this to your gradle build-script:

```groovy
dependencies {
    ...

    implementation "com.redmadrobot:statedelegator:1.5"
}
```

And that's all! :-)

#### Sources

The project consists of two modules - `state-delegator` and` sample`.

`state-delegator` - library codebase.

`sample` is an example of using state-delegator.