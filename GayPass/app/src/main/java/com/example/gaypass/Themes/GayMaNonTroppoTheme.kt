package com.example.gaypass.Themes

import android.content.Context
import android.view.View
import android.view.Window
import androidx.appcompat.app.ActionBar
import com.example.gaypass.R

class GayMaNonTroppoTheme(
        private var context: Context,
        private var window: Window,
        private var view: View,
        private var actionBar: ActionBar
    ): Theme(
        R.color.colorPrimaryMaNonTroppo,
        R.color.colorPrimaryDarkMaNonTroppo,
        R.color.colorAccentMaNonTroppo,
        R.color.colorBgRainbow,
        listOf(
            R.drawable.background_main_theme_gaymanontroppo,
            R.drawable.background_info_theme_gaymanontroppo,
            R.drawable.background_settings_theme_gaymanontroppo
        ),
        "GayMaNonTroppo Theme"
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