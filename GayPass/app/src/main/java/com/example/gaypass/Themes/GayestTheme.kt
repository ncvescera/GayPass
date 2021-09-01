package com.example.gaypass.themes

import android.content.Context
import android.view.View
import android.view.Window
import androidx.appcompat.app.ActionBar
import com.example.gaypass.R

class GayestTheme
    (
        private var context: Context,
        private var window: Window,
        private var view: View,
        private var actionBar: ActionBar
    ): Theme(
        R.color.colorPrimaryVeryGay,
        R.color.colorPrimaryDarkVeryGay,
        R.color.colorAccentVeryGay,
        R.color.colorBgRainbow,
        listOf(
            R.drawable.background_main_theme_verygay,
            R.drawable.background_info_theme_verygay,
            R.drawable.background_settings_theme_verygay
        ),
        context.getString(R.string.gayestTheme_text)
    ) {
        override fun apply() {
            super.applyTheme(
                context     = context,
                window      = window,
                view        = view,
                actionBar   = actionBar
            )
        }
    }