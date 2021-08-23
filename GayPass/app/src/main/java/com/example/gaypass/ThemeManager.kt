package com.example.gaypass

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.core.view.get

class ThemeManager(private val context: Context, private var window: Window, private var actionBar: ActionBar?, private var view: View) {
    private var settingsManager: SettingsManager = SettingsManager(context)

    companion object {
        const val DEFAULT = 0
        const val GAYEST_THEME = 1
        const val GAYLATENTE_THEME = 2
    }

    private val default_theme = Theme(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent)
    private val gayest_theme = Theme(R.color.colorPrimaryVeryGay, R.color.colorPrimaryDarkVeryGay, R.color.colorAccentVeryGay)
    private val gayLatente_theme = Theme(R.color.colorPrimaryLatente, R.color.colorPrimaryDarkLatente, R.color.colorAccentLatente)

    var theme: Int
        get() {
            return settingsManager.currentTheme
        }
        set(value) {
            settingsManager.currentTheme = value
            applyTheme()
        }

    private fun background() {
        when (context as Activity) {
            is MainActivity -> {

            }
            is InfoActivity -> {

            }
            is SettingsActivity -> {

            }
        }
    }

    fun applyTheme() {
        when (settingsManager.currentTheme) {
            DEFAULT -> {
                window.statusBarColor = ContextCompat.getColor(context, default_theme.primary)
                actionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(context, default_theme.primaryDark)))

                when (context as Activity) {
                    is MainActivity -> {
                        view.background = context.getDrawable(R.drawable.background_main_theme_gaymanontroppo)
                    }
                    is InfoActivity -> {
                        view.background = context.getDrawable(R.drawable.background_info_theme_gaymanontroppo)
                    }
                    is SettingsActivity -> {
                        view.background = context.getDrawable(R.drawable.background_settings_theme_gaymanontroppo)
                    }
                }

                // set the content box color
                var box_maincontent = (view as ViewGroup).get(0)
                box_maincontent.background = ColorDrawable(Color.parseColor("#C1FFFFFF"))
            }
            GAYEST_THEME -> {
                // set statusBar and actionBar colors
                window.statusBarColor = ContextCompat.getColor(context, gayest_theme.primary)
                actionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(context, gayest_theme.primaryDark)))

                // set custom bg for different activityes
                when (context as Activity) {
                    is MainActivity -> {
                        view.background = context.getDrawable(R.drawable.background_main_theme_verygay)
                    }
                    is InfoActivity -> {
                        view.background = context.getDrawable(R.drawable.background_info_theme_verygay)
                    }
                    is SettingsActivity -> {
                        view.background = context.getDrawable(R.drawable.background_settings_theme_verygay)
                    }
                }

                // set the content box color
                var box_maincontent = (view as ViewGroup).get(0)
                box_maincontent.background = ColorDrawable(Color.parseColor("#90FFFFFF"))
            }
            GAYLATENTE_THEME -> {
                window.statusBarColor = ContextCompat.getColor(context, gayLatente_theme.primary)
                actionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(context, gayLatente_theme.primaryDark)))

                when (context as Activity) {
                    is MainActivity -> {
                        view.background = context.getDrawable(R.drawable.background_main_theme_gaylatente)
                    }
                    is InfoActivity -> {
                        view.background = context.getDrawable(R.drawable.background_info_theme_gaylatente)
                    }
                    is SettingsActivity -> {
                        view.background = context.getDrawable(R.drawable.background_settings_theme_gaylatente)
                    }
                }

                // set the content box color
                var box_maincontent = (view as ViewGroup).get(0)
                box_maincontent.background = ColorDrawable(Color.parseColor("#C0FFFFFF"))
            }
        }
    }
}