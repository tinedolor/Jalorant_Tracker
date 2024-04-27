package com.example.tracker.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class card(
    @StringRes val StringResourceId: Int,
    @DrawableRes val ImageResourceId: Int,
)
