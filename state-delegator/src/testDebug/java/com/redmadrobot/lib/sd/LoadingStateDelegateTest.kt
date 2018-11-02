package com.redmadrobot.lib.sd

import android.view.View
import org.hamcrest.Matchers.equalTo
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class LoadingStateDelegateTest : Assert() {

    private val context = RuntimeEnvironment.application

    private val contentView = View(context)
    private val loadingView = View(context)
    private val stubView = View(context)

    private val screenState = LoadingStateDelegate(contentView, loadingView, stubView)

    private val withoutContentState = LoadingStateDelegate(loadingView = loadingView, stubView =  stubView)
    private val withoutLoadingState = LoadingStateDelegate(contentView = contentView, stubView = stubView)
    private val withoutStubState = LoadingStateDelegate(contentView = contentView, loadingView = loadingView)

    @Test
    fun showContent() {
        hideAll()
        screenState.showContent()
        assertThat(contentView.visibility, equalTo(View.VISIBLE))
        assertThat(loadingView.visibility, equalTo(View.GONE))
        assertThat(stubView.visibility, equalTo(View.GONE))
    }

    @Test
    fun showLoading() {
        hideAll()
        screenState.showLoading()
        assertThat(loadingView.visibility, equalTo(View.VISIBLE))
        assertThat(contentView.visibility, equalTo(View.GONE))
        assertThat(stubView.visibility, equalTo(View.GONE))
    }

    @Test
    fun showStub() {
        hideAll()
        screenState.showStub()
        assertThat(stubView.visibility, equalTo(View.VISIBLE))
        assertThat(contentView.visibility, equalTo(View.GONE))
        assertThat(loadingView.visibility, equalTo(View.GONE))
    }

    @Test
    fun withoutAnyState() {
        hideAll()
        withoutContentState.showLoading()
        assertThat(stubView.visibility, equalTo(View.GONE))
        assertThat(contentView.visibility, equalTo(View.GONE))
        assertThat(loadingView.visibility, equalTo(View.VISIBLE))
        withoutContentState.showStub()
        assertThat(stubView.visibility, equalTo(View.VISIBLE))
        assertThat(contentView.visibility, equalTo(View.GONE))
        assertThat(loadingView.visibility, equalTo(View.GONE))
        withoutContentState.showContent()
        assertThat(stubView.visibility, equalTo(View.GONE))
        assertThat(contentView.visibility, equalTo(View.GONE))
        assertThat(loadingView.visibility, equalTo(View.GONE))

        hideAll()
        withoutLoadingState.showContent()
        assertThat(stubView.visibility, equalTo(View.GONE))
        assertThat(contentView.visibility, equalTo(View.VISIBLE))
        assertThat(loadingView.visibility, equalTo(View.GONE))
        withoutLoadingState.showStub()
        assertThat(stubView.visibility, equalTo(View.VISIBLE))
        assertThat(contentView.visibility, equalTo(View.GONE))
        assertThat(loadingView.visibility, equalTo(View.GONE))
        withoutLoadingState.showLoading()
        assertThat(stubView.visibility, equalTo(View.GONE))
        assertThat(contentView.visibility, equalTo(View.GONE))
        assertThat(loadingView.visibility, equalTo(View.GONE))

        hideAll()
        withoutStubState.showLoading()
        assertThat(stubView.visibility, equalTo(View.GONE))
        assertThat(contentView.visibility, equalTo(View.GONE))
        assertThat(loadingView.visibility, equalTo(View.VISIBLE))
        withoutStubState.showStub()
        assertThat(stubView.visibility, equalTo(View.GONE))
        assertThat(contentView.visibility, equalTo(View.GONE))
        assertThat(loadingView.visibility, equalTo(View.GONE))
        withoutStubState.showContent()
        assertThat(stubView.visibility, equalTo(View.GONE))
        assertThat(contentView.visibility, equalTo(View.VISIBLE))
        assertThat(loadingView.visibility, equalTo(View.GONE))

    }

    @Test
    fun testMultipleViews() {
        val contentViewLayout = listOf(View(context), View(context))
        val loadingViewLayout = listOf(View(context), View(context))
        val stubViewLayout = listOf(View(context))

        val screenState = LoadingStateDelegate(contentViewLayout, loadingViewLayout, stubViewLayout)

        screenState.showContent()
        contentViewLayout.forEach { assertThat(it.visibility, equalTo(View.VISIBLE)) }
        loadingViewLayout.forEach { assertThat(it.visibility, equalTo(View.GONE)) }
        stubViewLayout.forEach { assertThat(it.visibility, equalTo(View.GONE)) }

        screenState.showLoading()
        contentViewLayout.forEach { assertThat(it.visibility, equalTo(View.GONE)) }
        loadingViewLayout.forEach { assertThat(it.visibility, equalTo(View.VISIBLE)) }
        stubViewLayout.forEach { assertThat(it.visibility, equalTo(View.GONE)) }

        screenState.showStub()
        contentViewLayout.forEach { assertThat(it.visibility, equalTo(View.GONE)) }
        loadingViewLayout.forEach { assertThat(it.visibility, equalTo(View.GONE)) }
        stubViewLayout.forEach { assertThat(it.visibility, equalTo(View.VISIBLE)) }
    }

    private fun hideAll() {
        contentView.visibility = View.GONE
        loadingView.visibility = View.GONE
        stubView.visibility = View.GONE
    }
}