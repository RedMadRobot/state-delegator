package com.redmadrobot

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.redmadrobot.lib.sd.StubState
import com.redmadrobot.lib.sd.ZeroStubView
import kotlinx.android.synthetic.main.layout_zero_stub.view.*

class ZeroStubLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle), ZeroStubView {

    init {
        inflate(context, R.layout.layout_zero_stub, this)
    }

    override fun setUpZero(state: StubState) {
        state as StubStateData
        state.iconResId?.let { zero_stub_icon.setImageResource(it) }
        state.titleResId?.let { zero_stub_title.setText(it) }
        state.descriptionResId?.let { zero_stub_description.setText(it) }
    }
}