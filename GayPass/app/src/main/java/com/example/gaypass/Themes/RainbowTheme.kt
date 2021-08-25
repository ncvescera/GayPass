package com.example.gaypass.Themes

import android.content.Context
import android.view.View
import android.view.Window
import androidx.appcompat.app.ActionBar
import com.example.gaypass.R

class RainbowTheme(
        private var context: Context,
        private var window: Window,
        private var view: View,
        private var actionBar: ActionBar
    ): Theme(
        R.color.colorPrimaryRainbow,
        R.color.colorPrimaryDarkRainbow,
        R.color.colorAccentRainbow,
        R.color.colorBgRainbow,
        listOf(R.drawable.background_rainbow)
    ) {
        override fun apply() {
            super.applyTheme(
                context = context,
                window = window,
                view = view,
                actionBar = actionBar
            )
        }
    }