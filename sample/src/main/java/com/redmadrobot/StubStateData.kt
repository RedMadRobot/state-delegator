package com.redmadrobot

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.redmadrobot.lib.sd.StubState

data class StubStateData(

        @DrawableRes
        val iconResId: Int? = null,

        @StringRes
        val titleResId: Int? = null,

        @StringRes
        val descriptionResId: Int? = null
): StubState