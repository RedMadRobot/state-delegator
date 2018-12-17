package com.redmadrobot.lib.sd

import android.view.View
import com.redmadrobot.lib.sd.base.State
import com.redmadrobot.lib.sd.base.StateDelegate
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class StateDelegateTest : Assert() {

    private val context = RuntimeEnvironment.application

    private val contentView = View(context)
    private val loadingView = View(context)
    private val stubView = View(context)

    enum class TestState {
        CONTENT,
        LOADING,
        STUB
    }

    private val screenState = StateDelegate(
            State(TestState.CONTENT, contentView),
            State(TestState.LOADING, loadingView),
            State(TestState.STUB, stubView)
    )

    @Test
    fun showContent() {
        hideAll()
        screenState.currentState = TestState.CONTENT
        assertThat(contentView.visibility, Matchers.equalTo(View.VISIBLE))
        assertThat(loadingView.visibility, Matchers.equalTo(View.GONE))
        assertThat(stubView.visibility, Matchers.equalTo(View.GONE))
    }

    @Test
    fun showLoading() {
        hideAll()
        screenState.currentState = TestState.LOADING
        assertThat(loadingView.visibility, Matchers.equalTo(View.VISIBLE))
        assertThat(contentView.visibility, Matchers.equalTo(View.GONE))
        assertThat(stubView.visibility, Matchers.equalTo(View.GONE))
    }

    @Test
    fun showStub() {
        hideAll()
        screenState.currentState = TestState.STUB
        assertThat(stubView.visibility, Matchers.equalTo(View.VISIBLE))
        assertThat(contentView.visibility, Matchers.equalTo(View.GONE))
        assertThat(loadingView.visibility, Matchers.equalTo(View.GONE))
    }

    private fun hideAll() {
        contentView.visibility = View.GONE
        loadingView.visibility = View.GONE
        stubView.visibility = View.GONE
    }
}