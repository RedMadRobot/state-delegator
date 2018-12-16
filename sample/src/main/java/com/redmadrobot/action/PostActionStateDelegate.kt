package com.redmadrobot.action

import android.view.View
import com.redmadrobot.lib.sd.base.ShowOnEnterGoneOnExitStrategy
import com.redmadrobot.lib.sd.base.StateChangeStrategy
import com.redmadrobot.lib.sd.base.StateDelegate
import com.redmadrobot.lib.sd.base.State
import kotlinx.android.synthetic.main.layout_progress.view.*

class PostActionStateDelegate(
        progressView: View
) : StateDelegate<PostActionScreenState>(
        State(
                PostActionScreenState.LOADING,
                listOf(progressView),
                ProgressStrategy(progressView)
        ),
        State(
                PostActionScreenState.CONTENT,
                emptyList(),
                NoChangeStrategy()
        )
) {

    fun showLoading() {
        this.currentState = PostActionScreenState.LOADING
    }

    fun hideLoading() {
        this.currentState = PostActionScreenState.CONTENT
    }
}

enum class PostActionScreenState {
    LOADING, CONTENT
}

class ProgressStrategy<T : Enum<T>>(private val progress: View) : ShowOnEnterGoneOnExitStrategy<T>() {

    override fun onStateEnter(state: State<T>, prevState: State<T>?) {
        super.onStateEnter(state, prevState)
        progress.progress_view.playAnimation()
    }

    override fun onStateExit(state: State<T>, nextState: State<T>?) {
        progress.progress_view.cancelAnimation()
        super.onStateExit(state, nextState)
    }
}

class NoChangeStrategy<T : Enum<T>> : StateChangeStrategy<T>
