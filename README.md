State Delegator
===============

![Logo](logo/logo.png)

[![Version](https://img.shields.io/maven-central/v/com.redmadrobot/state-delegator?style=flat-square)][mavenCentral]
[![License](https://img.shields.io/github/license/RedMadRobot/state-delegator?style=flat-square)][license]

State delegator is a collection of classes that helps you to manage a screen state and reduces boilerplate code.

### Example

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

### Overview

[Check documentation](https://redmadrobot.github.io/state-delegator/)

[Check sample](https://github.com/RedMadRobot/state-delegator/tree/master/sample)

### Download

To get started using **state-delegator** is to add it as a gradle dependency.
You need to make sure you have the MavenCentral repository included in the `build.gradle` file in the root of your project:

```gradle
repositories {
    mavenCentral()
}
```

Next add a dependency in the `build.gradle` file of your app module. The
following will add a dependency to the library:

```gradle
dependencies {
    ...

    implementation "com.redmadrobot:state-delegator:1.7"
}
```

And that's all! :-)

### License

    MIT License

    Copyright (c) 2020 Redmadrobot

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

[mavenCentral]: https://search.maven.org/artifact/com.redmadrobot/state-delegator
[license]: LICENSE
