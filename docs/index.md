State delegator is a collection of classes that helps you to manage a screen state and reduces boilerplate code.

#### Quick example

Basic example is implementation of Loading, Content, Error (LCE) pattern:

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
