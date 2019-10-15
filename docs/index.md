State delegator is a collection of classes that helps you to manage a screen state.

#### Quick example

When changing a screen state you always have to show or hide the certain views:

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

To reduce a boilerplate code we use `LoadingStateDelegate` class:

```kotlin
val screenState = LoadingStateDelegate(content_view, loading_view, stub_view)
...
when (state) {
    LOADING -> screenState.showLoading()
    CONTENT -> screenState.showContent()
    STUB -> screenState.showStub()
}
```

With `LoadingStateDelegate` source code becomes cleaner and more compact.

### LoadingStateDelegate

To control visibility of content, loading and zero-screen, `LoadingStateDelegate` must be initialized with the appropriate views:

```kotlin
class LoadingStateDelegate(
        private val contentView: View? = null,
        private val loadingView: View? = null,
        private val stubView: View? = null
): StateDelegate
```

To show loading status you must call `showLoading()` method, to show content view  - `showContent()` and to show zero-screen view - `showStub(stubState: StubState? = null)`, where `StubState` - class that initializes zero-screen, by default you can omit it.

Also you can use the constructor with an array of `View`:

```kotlin
class LoadingStateDelegate(
        private val contentView: List<View>,
        private val loadingView: List<View>,
        private val stubView: List<View>
): StateDelegate
```

You should use the constructor above in case of views are scattered around the `layout`. If you use `constraint-layout` it is recommended to use `Group` class and the standard constructor `LoadingStateDelegate`.

When working with `LoadingStateDelegate` in Fragment/Activity don't use the `by lazy`:

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

This can lead to errors. If `Activity` returns from the back stack, `View` is recreated, but the delegate is not. It refers to the old `View` reference. Use `lateinit` and re-create delegate every time in the component lifecycle methods.

### StateDelegate

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

### Download

To get started using `state-delegator` is to add it as a gradle dependency. You need to make sure you have the JCenter repository included in the build.gradle file in the root of your project:

```gradle
repositories {
    jcenter()
}
```

Next add a dependency in the `build.gradle` file of your app module. The
following will add a dependency to the library:

```gradle
dependencies {
    ...

    implementation "com.redmadrobot:state-delegator:1.5"
}
```

And that's all! :-)

### Source code

The project has two models - `state-delegator` and `sample`.

`state-delegator` - the library codebase.

`sample` is an example of usage `state-delegator`.

### License

```
MIT License

Copyright (c) 2019 Redmadrobot

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
