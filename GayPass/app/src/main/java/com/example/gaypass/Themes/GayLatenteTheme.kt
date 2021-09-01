package com.example.gaypass.themes

import android.content.Context
import android.view.View
import android.view.Window
import androidx.appcompat.app.ActionBar
import com.example.gaypass.R

class GayLatenteTheme
    (
        private var context: Context,
        private var window: Window,
        private var view: View,
        private var actionBar: ActionBar
    ): Theme(
        R.color.colorPrimaryLatente,
        R.color.colorPrimaryDarkLatente,
        R.color.colorAccentLatente,
        R.color.colorBgRainbow,
        listOf(
            R.drawable.background_main_theme_gaylatente,
            R.drawable.background_info_theme_gaylatente,
            R.drawable.background_settings_theme_gaylatente
        ),
        context.getString(R.string.gayLatenteTheme_text)
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