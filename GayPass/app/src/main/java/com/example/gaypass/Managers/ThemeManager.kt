package com.example.gaypass.Managers

import android.R.attr.checked
import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.core.view.get
import com.example.gaypass.*
import com.example.gaypass.Utils.Theme
import com.google.android.material.switchmaterial.SwitchMaterial


class ThemeManager(
    private val context: Context,
    private var window: Window,
    private var actionBar: ActionBar?,
    private var view: View
) {
    private var settingsManager: SettingsManager = SettingsManager(context)

    companion object {
        const val DEFAULT           = 0
        const val GAYEST_THEME      = 1
        const val GAYLATENTE_THEME  = 2
        const val RAINBOW_THEME     = 3
    }

    // --- THEME DEFINITIONS --- //
    private val default_theme = Theme(
        primary     = R.color.colorPrimaryDefault,
        primaryDark = R.color.colorPrimaryDarkDefault,
        accent      = R.color.colorAccentDefault,
        bgText      = R.color.colorBgDefault,
        bgDrawable  = null

    )
    private val rainbow_theme = Theme(
        primary     = R.color.colorPrimaryRainbow,
        primaryDark = R.color.colorPrimaryDarkRainbow,
        accent      = R.color.colorAccentRainbow,
        bgText      = R.color.colorBgRainbow,
        bgDrawable  = listOf(R.drawable.background_rainbow)
    )
    private val gayest_theme = Theme(
        primary     = R.color.colorPrimaryVeryGay,
        primaryDark = R.color.colorPrimaryDarkVeryGay,
        accent      = R.color.colorAccentVeryGay,
        bgText      = R.color.colorBgRainbow,
        bgDrawable  = listOf(
            R.drawable.background_main_theme_verygay,
            R.drawable.background_info_theme_verygay,
            R.drawable.background_settings_theme_verygay
        )
    )
    private val gayLatente_theme = Theme(
        primary     = R.color.colorPrimaryLatente,
        primaryDark = R.color.colorPrimaryDarkLatente,
        accent      = R.color.colorAccentLatente,
        bgText      = R.color.colorBgRainbow,
        bgDrawable  = listOf(
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
        val box_maincontent = (view as ViewGroup)[0]

        // choose which theme to apply
        when (settingsManager.currentTheme) {
            DEFAULT -> {
                // set nav & status bars color
                window.statusBarColor = ContextCompat.getColor(context, default_theme.primary)
                actionBar?.setBackgroundDrawable(
                    ColorDrawable(
                        ContextCompat.getColor(
                            context,
                            default_theme.primaryDark
                        )
                    )
                )

                // set custom activity bg
                applyCustomBgDrawable(default_theme.bgDrawable)

                // set the content box color
                box_maincontent.background = ColorDrawable(
                    ContextCompat.getColor(
                        context,
                        default_theme.bgText
                    )
                )

                // set switches colors
                setSwitchesColors(box_maincontent, default_theme.accent)
            }

            GAYEST_THEME -> {
                // set nav & status bars color
                window.statusBarColor = ContextCompat.getColor(context, gayest_theme.primary)
                actionBar?.setBackgroundDrawable(
                    ColorDrawable(
                        ContextCompat.getColor(
                            context,
                            gayest_theme.primaryDark
                        )
                    )
                )

                // set custom activity bg
                applyCustomBgDrawable(gayest_theme.bgDrawable)

                // set the content box color
                box_maincontent.background = ColorDrawable(
                    ContextCompat.getColor(
                        context,
                        gayest_theme.bgText
                    )
                )

                // set switches colors
                setSwitchesColors(box_maincontent, gayest_theme.accent)

            }

            GAYLATENTE_THEME -> {
                // set nav & status bars color
                window.statusBarColor = ContextCompat.getColor(context, gayLatente_theme.primary)
                actionBar?.setBackgroundDrawable(
                    ColorDrawable(
                        ContextCompat.getColor(
                            context,
                            gayLatente_theme.primaryDark
                        )
                    )
                )

                // set custom activity bg
                applyCustomBgDrawable(gayLatente_theme.bgDrawable)

                // set the content box color
                box_maincontent.background = ColorDrawable(
                    ContextCompat.getColor(
                        context,
                        gayLatente_theme.bgText
                    )
                )

                // set switches colors
                setSwitchesColors(box_maincontent, gayLatente_theme.accent)

            }

            RAINBOW_THEME -> {
                // set nav & status bars color
                window.statusBarColor = ContextCompat.getColor(context, rainbow_theme.primary)
                actionBar?.setBackgroundDrawable(
                    ColorDrawable(
                        ContextCompat.getColor(
                            context,
                            rainbow_theme.primaryDark
                        )
                    )
                )

                // set custom activity bg
                applyCustomBgDrawable(rainbow_theme.bgDrawable)

                // set the content box color
                box_maincontent.background = ColorDrawable(
                    ContextCompat.getColor(
                        context,
                        rainbow_theme.bgText
                    )
                )

                // set switches colors
                setSwitchesColors(box_maincontent, rainbow_theme.accent)

            }
        }
    }

    // constant for creating secondary colors
    private val SECONDARY_THRASHOLD = 53

    private fun setSwitchesColors(box_maincontent: View, primaryColor: Int) {
        // generate secondary color
        val tmp = ContextCompat.getColor(context, primaryColor)
        val newcolor = Color.rgb(tmp.red, tmp.green + SECONDARY_THRASHOLD, tmp.blue)

        val box_childs = box_maincontent as ViewGroup
        for (i in 0 until box_childs.childCount) {
            when (box_childs[i]) {
                is SwitchMaterial -> {
                    // get child refs
                    val child = box_childs[i] as SwitchMaterial

                    // set checked/unchecked primary colors
                    child.thumbTintList = ColorStateList(
                        arrayOf(
                            intArrayOf(-android.R.attr.state_enabled),
                            intArrayOf(android.R.attr.state_checked),
                            intArrayOf()
                        ),
                        intArrayOf(
                            0,                                                //useless
                            ContextCompat.getColor(context, primaryColor),    // checked primary color
                            Color.WHITE                                       // unchecked primary color
                        )
                    )

                    // set checked/unchecked secondary colors
                    val t = ColorStateList(
                        arrayOf(
                            intArrayOf(android.R.attr.state_checked),
                            intArrayOf()
                        ),
                        intArrayOf(
                            newcolor,       // unchecked secondary color
                            Color.LTGRAY,   // unchecked secondary color
                            0               //useless
                        )
                    )

                    // set child final colors
                    child.trackTintList = t;
                }
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