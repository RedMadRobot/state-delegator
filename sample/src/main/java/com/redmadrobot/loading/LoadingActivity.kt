package com.redmadrobot.loading

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.redmadrobot.R
import com.redmadrobot.StubStateData
import com.redmadrobot.lib.sd.LoadingStateDelegate
import kotlinx.android.synthetic.main.activity_loading.*

class LoadingActivity : AppCompatActivity() {

    private lateinit var screenState: LoadingStateDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        screenState = LoadingStateDelegate(loading_content, loading_progress, loading_zero_stub)

        loading_show_loading_btn.setOnClickListener {
            screenState.showLoading()
        }
        loading_show_content_btn.setOnClickListener {
            screenState.showContent()
        }
        loading_show_stub_btn.setOnClickListener {
            screenState.showStub(StubStateData(
                    iconResId = R.mipmap.ic_launcher,
                    titleResId = R.string.loading_zero_title,
                    descriptionResId = R.string.loading_zero_description
            ))
        }
    }
}