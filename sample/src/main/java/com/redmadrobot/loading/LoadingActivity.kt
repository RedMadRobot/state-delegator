package com.redmadrobot.loading

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.redmadrobot.R
import com.redmadrobot.StubStateData
import com.redmadrobot.lib.sd.LoadingStateDelegate
import kotlinx.android.synthetic.main.activity_loading.*

class LoadingActivity  : AppCompatActivity() {

    private var handler = Handler()

    private lateinit var screenState: LoadingStateDelegate

    private val showLoadingTask = { screenState.showLoading() }
    private val showZeroScreenTask = { screenState.showStub() }
    private val showZeroWithStubScreenTask = { screenState.showStub(StubStateData(
            iconResId = R.mipmap.ic_launcher,
            titleResId = R.string.loading_zero_title,
            descriptionResId = R.string.loading_zero_description
    )) }
    private val showContentTask = { screenState.showContent() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        screenState = LoadingStateDelegate(loading_content, loading_progress, loading_zero_stub)
    }

    override fun onResume() {
        super.onResume()
        handler.post(showLoadingTask)
        handler.postDelayed(showZeroScreenTask, 3_000L)
        handler.postDelayed(showLoadingTask, 6_000L)
        handler.postDelayed(showContentTask, 9_000L)
        handler.postDelayed(showZeroWithStubScreenTask, 12_000L)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(showLoadingTask)
        handler.removeCallbacks(showZeroScreenTask)
        handler.removeCallbacks(showContentTask)
        handler.removeCallbacks(showZeroWithStubScreenTask)
    }
}