package com.example.gaypass

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SettingsManager(context: Context) {
    private var activity = context as Activity
    private var sharedPref: SharedPreferences


    init {
        sharedPref = activity.getPreferences(Context.MODE_PRIVATE)

    }

    // Proprieties for identifying the value on the SharedProprieties
    private val gayestModeAlways_prop = object { val id = "gmode_always"; val value = false }
    private val soundOnlyOnStart_prop = object { val id = "sound_onlystart"; val value = false }
    private val soundNever_prop = object { val id = "sound_never"; val value = false }

    // public getters
    val gayestModeAlways: Boolean
        get() = sharedPref.getBoolean(gayestModeAlways_prop.id, gayestModeAlways_prop.value)

    val soundOnlyOnStart: Boolean
        get() = sharedPref.getBoolean(soundOnlyOnStart_prop.id, soundOnlyOnStart_prop.value)

    val soundNever: Boolean
        get() = sharedPref.getBoolean(soundNever_prop.id, soundNever_prop.value)


}