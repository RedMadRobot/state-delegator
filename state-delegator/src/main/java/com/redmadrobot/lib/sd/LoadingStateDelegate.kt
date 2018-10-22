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

    constructor(contentView: List<View>, loadingView: List<View>, stubView: List<View>) :
            super(
                    State(LoadingState.CONTENT, contentView),
                    State(LoadingState.LOADING, loadingView),
                    State(LoadingState.STUB, stubView)
            ) {
        this.stubView = stubView
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