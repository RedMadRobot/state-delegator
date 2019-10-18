package com.redmadrobot.lib.sd

import android.view.View
import com.redmadrobot.lib.sd.base.State
import com.redmadrobot.lib.sd.base.StateDelegate

class LoadingStateDelegate : StateDelegate<LoadingStateDelegate.LoadingState> {

    companion object {

        private fun convertToList(view: View?): List<View> {
            return if (view != null) listOf(view) else emptyList()
        }
    }

    private val stubView: List<View>

    constructor(contentView: View? = null, loadingView: View? = null, stubView: View? = null) :
            super(
                    State(LoadingState.CONTENT, convertToList(contentView)),
                    State(LoadingState.LOADING, convertToList(loadingView)),
                    State(LoadingState.STUB, convertToList(stubView))
            ) {
        this.stubView = convertToList(stubView)
    }

    constructor(
            contentView: List<View>? = null,
            loadingView: List<View>? = null,
            stubView: List<View>? = null
    ) :
            super(
                    State(LoadingState.CONTENT, contentView ?: listOf()),
                    State(LoadingState.LOADING, loadingView ?: listOf()),
                    State(LoadingState.STUB, stubView ?: listOf())
            ) {
        this.stubView = stubView ?: listOf()
    }

    fun showContent() {
        this.currentState = LoadingState.CONTENT
    }

    fun showLoading() {
        this.currentState = LoadingState.LOADING
    }

    fun showStub(stubState: StubState? = null) {
        this.currentState = LoadingState.STUB
        stubState ?: return
        stubView.forEach { view -> if (view is ZeroStubView) view.setUpZero(stubState) }
    }

    enum class LoadingState {
        LOADING,
        CONTENT,
        STUB
    }
}