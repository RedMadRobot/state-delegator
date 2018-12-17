package com.redmadrobot

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.redmadrobot.lib.sd.StubState

data class StubStateData(

        @DrawableRes
        val iconResId: Int? = null,

        @StringRes
        val titleResId: Int? = null,

        @StringRes
        val descriptionResId: Int? = null
) : StubState