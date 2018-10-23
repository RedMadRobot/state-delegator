package com.redmadrobot.lib.sd

import android.view.View

@Deprecated("Use StateDelegate")
class MultipleStubsStateDelegate(
        private val contentView: View? = null,
        private val loadingView: View? = null,
        private val stubViews: Map<String, View>? = null
) {
    companion object {
        private const val ERROR_MESSAGE = "You have to set `%s` to use this name"
    }

    fun showContent() {
        requireNotNull(loadingView) { String.format(ERROR_MESSAGE, "contentView") }
        setState(Content)
    }

    fun showLoading() {
        requireNotNull(loadingView) { String.format(ERROR_MESSAGE, "loadingView") }
        setState(Loading)
    }

    fun showStub(stubKey: String, stubState: StubState? = null) {
        stubViews?.let {
            requireNotNull(it[stubKey]) { String.format(ERROR_MESSAGE, stubKey) }
            setState(MultipleStub(stubKey, stubState))
        }
    }

    private fun setState(state: InternalState) {
        when (state) {
            is Loading -> {
                stubViews?.forEach { it.value.visibility = View.GONE }
                        .also { contentView?.visibility = View.GONE }

                loadingView?.visibility = View.VISIBLE
            }

            is MultipleStub -> {
                stubViews?.filter { it.key != state.stubKey }
                        ?.forEach { it.value.visibility = View.GONE }
                        .also { contentView?.visibility = View.GONE }
                        .also { loadingView?.visibility = View.GONE }

                val stubView = stubViews?.get(state.stubKey)

                state.stubState?.let { (stubView as? ZeroStubView)?.setUpZero(it) }

                stubView?.visibility = View.VISIBLE
            }

            is Content -> {

                stubViews?.forEach { it.value.visibility = View.GONE }
                        .also { loadingView?.visibility = View.GONE }

                contentView?.visibility = View.VISIBLE
            }
        }
    }

    interface InternalState
    object Loading : InternalState
    object Content : InternalState
    data class MultipleStub(val stubKey: String, val stubState: StubState?) : InternalState
}