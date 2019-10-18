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

    implementation "com.redmadrobot:state-delegator:1.6"
}
```

And that's all! :-)
