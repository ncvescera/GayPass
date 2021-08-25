package com.example.gaypass.Themes

import androidx.appcompat.app.ActionBar
import android.content.Context
import android.view.View
import android.view.Window
import com.example.gaypass.R

class DefaultTheme
    (
        private var context: Context,
        private var window: Window,
        private var view: View,
        private var actionBar: ActionBar
    ): Theme(
        R.color.colorPrimaryDefault,
        R.color.colorPrimaryDarkDefault,
        R.color.colorAccentDefault,
        R.color.colorBgDefault,
        null
    ) {
        override fun apply() {
            super.applyTheme(
                context = context,
                window = window,
                view = view,
                actionBar = actionBar
            )
        }

        override fun applyCustomBgDrawable(context: Context, view: View, bgs: List<Int>?) {
            view.background = null
        }

    }