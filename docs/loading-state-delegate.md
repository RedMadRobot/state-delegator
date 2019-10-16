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
