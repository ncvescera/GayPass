package com.example.gaypass.Managers

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.example.gaypass.*
import com.example.gaypass.Utils.Theme

class ThemeManager(private val context: Context, private var window: Window, private var actionBar: ActionBar?, private var view: View) {
    private var settingsManager: SettingsManager = SettingsManager(context)

    companion object {
        const val DEFAULT = 0
        const val GAYEST_THEME = 1
        const val GAYLATENTE_THEME = 2
        const val RAINBOW_THEME = 3
    }

    // --- THEME DEFINITIONS --- //
    private val default_theme = Theme(
        primary = R.color.colorPrimaryDefault,
        primaryDark = R.color.colorPrimaryDarkDefault,
        accent = R.color.colorAccentDefault,
        bgText = R.color.colorBgDefault,
        bgDrawable = null

    )
    private val rainbow_theme = Theme(
        primary = R.color.colorPrimaryRainbow,
        primaryDark = R.color.colorPrimaryDarkRainbow,
        accent = R.color.colorAccentRainbow,
        bgText = R.color.colorBgRainbow,
        bgDrawable = listOf(R.drawable.background_rainbow)
    )
    private val gayest_theme = Theme(
        primary = R.color.colorPrimaryVeryGay,
        primaryDark = R.color.colorPrimaryDarkVeryGay,
        accent = R.color.colorAccentVeryGay,
        bgText = R.color.colorBgRainbow,
        bgDrawable = listOf(
            R.drawable.background_main_theme_verygay,
            R.drawable.background_info_theme_verygay,
            R.drawable.background_settings_theme_verygay
        )
    )
    private val gayLatente_theme = Theme(
        primary = R.color.colorPrimaryLatente,
        primaryDark = R.color.colorPrimaryDarkLatente,
        accent = R.color.colorAccentLatente,
        bgText = R.color.colorBgRainbow,
        bgDrawable = listOf(
            R.drawable.background_main_theme_gaylatente,
            R.drawable.background_info_theme_gaylatente,
            R.drawable.background_settings_theme_gaylatente
        )
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
        // get the center screen elements box
        var box_maincontent = (view as ViewGroup)[0]

        // choose which theme to apply
        when (settingsManager.currentTheme) {
            DEFAULT -> {
                // set nav & status bars color
                window.statusBarColor = ContextCompat.getColor(context, default_theme.primary)
                actionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(context, default_theme.primaryDark)))

                // set custom activity bg
                applyCustomBgDrawable(default_theme.bgDrawable)

                // set the content box color
                box_maincontent.background = ColorDrawable(ContextCompat.getColor(context, default_theme.bgText))
            }

            GAYEST_THEME -> {
                // set nav & status bars color
                window.statusBarColor = ContextCompat.getColor(context, gayest_theme.primary)
                actionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(context, gayest_theme.primaryDark)))

                // set custom activity bg
                applyCustomBgDrawable(gayest_theme.bgDrawable)

                // set the content box color
                box_maincontent.background = ColorDrawable(ContextCompat.getColor(context, gayest_theme.bgText))
            }

            GAYLATENTE_THEME -> {
                // set nav & status bars color
                window.statusBarColor = ContextCompat.getColor(context, gayLatente_theme.primary)
                actionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(context, gayLatente_theme.primaryDark)))

                // set custom activity bg
                applyCustomBgDrawable(gayLatente_theme.bgDrawable)

                // set the content box color
                box_maincontent.background = ColorDrawable(ContextCompat.getColor(context, gayLatente_theme.bgText))
            }

            RAINBOW_THEME -> {
                // set nav & status bars color
                window.statusBarColor = ContextCompat.getColor(context, rainbow_theme.primary)
                actionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(context, rainbow_theme.primaryDark)))

                // set custom activity bg
                applyCustomBgDrawable(rainbow_theme.bgDrawable)

                // set the content box color
                box_maincontent.background = ColorDrawable(ContextCompat.getColor(context, rainbow_theme.bgText))
            }
        }
    }

    private fun applyCustomBgDrawable(bgs: List<Int>?) {
        when {
            bgs == null -> {
                // no bg
                view.background = null
            }
            bgs.size == 1 -> {
                // one bg for all activityes
                view.background = ContextCompat.getDrawable(context, bgs[0])
            }
            bgs.size == 3 -> {
                // set custom bg for different activityes
                when (context as Activity) {
                    is MainActivity -> {
                        view.background = ContextCompat.getDrawable(context, bgs[0])
                    }
                    is InfoActivity -> {
                        view.background = ContextCompat.getDrawable(context, bgs[1])
                    }
                    is SettingsActivity -> {
                        view.background = ContextCompat.getDrawable(context, bgs[2])
                    }
                }
            }
            else -> {
                // wrong number of elements
                // no bg
                view.background = null
            }
        }

    }
}