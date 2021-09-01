package com.example.gaypass.managers

import android.content.Context
import android.view.View
import android.view.Window
import androidx.appcompat.app.ActionBar
import com.example.gaypass.themes.*


class ThemeManager(
     context: Context,
     window: Window,
     actionBar: ActionBar?,
     view: View
) {
    private var settingsManager: SettingsManager = SettingsManager(context)

    // all themes
    val themes = listOf<Theme>(
        DefaultTheme(context, window, view, actionBar!!),
        RainbowTheme(context, window, view, actionBar),
        GayestTheme(context, window, view, actionBar),
        GayLatenteTheme(context, window, view, actionBar),
        GayMaNonTroppoTheme(context, window, view, actionBar)
    )

    var theme: Int
        get() {
            return settingsManager.currentTheme
        }
        set(value) {
            settingsManager.currentTheme = value
            applyTheme()
        }

    fun applyTheme() {
        // choose which theme to apply
        themes[settingsManager.currentTheme].apply()
    }
}