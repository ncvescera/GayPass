package com.example.gaypass.Managers

import android.content.Context
import android.view.View
import android.view.Window
import androidx.appcompat.app.ActionBar
import com.example.gaypass.Themes.*


class ThemeManager(
     context: Context,
     window: Window,
     actionBar: ActionBar?,
     view: View
) {
    private var settingsManager: SettingsManager = SettingsManager(context)

    // statics vars
    companion object {
        const val DEFAULT           = 0
        const val GAYEST_THEME      = 1
        const val GAYLATENTE_THEME  = 2
        const val RAINBOW_THEME     = 3
        const val GAYMANONTROPPO_THEME     = 4
    }

    private val themes = listOf<Theme>(
        DefaultTheme(context, window, view, actionBar!!),
        GayestTheme(context, window, view, actionBar),
        GayLatenteTheme(context, window, view, actionBar),
        RainbowTheme(context, window, view, actionBar),
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