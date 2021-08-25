package com.example.gaypass.Themes

import android.R
import androidx.appcompat.app.ActionBar
import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.core.view.get
import com.example.gaypass.InfoActivity
import com.example.gaypass.MainActivity
import com.example.gaypass.SettingsActivity
import com.google.android.material.switchmaterial.SwitchMaterial

abstract class Theme(val primary: Int, val primaryDark: Int, val accent: Int, val bgText: Int, val bgDrawable: List<Int>?, val name: String) {
    private val SECONDARY_THRASHOLD = 53

    abstract fun apply()

    protected open fun applyTheme(context: Context, window: Window, view: View, actionBar: ActionBar) {
        // get Activity Gui first element
        val box_maincontent = (view as ViewGroup)[0]

        // set nav & status bars color
        window.statusBarColor = ContextCompat.getColor(context, primary)
        actionBar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    context,
                    primaryDark
                )
            )
        )

        // set custom activity bg
        applyCustomBgDrawable(context, view, bgDrawable)

        // set the content box color
        box_maincontent.background = ColorDrawable(
            ContextCompat.getColor(
                context,
                bgText
            )
        )

        // set switches colors
        setSwitchesColors(context, box_maincontent, accent)
    }

    protected open fun setSwitchesColors(context: Context, box_maincontent: View, primaryColor: Int) {
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
                            intArrayOf(-R.attr.state_enabled),
                            intArrayOf(R.attr.state_checked),
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
                            intArrayOf(R.attr.state_checked),
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

    protected open fun applyCustomBgDrawable(context: Context, view: View, bgs: List<Int>?) {
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