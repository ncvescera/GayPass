package com.example.gaypass

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SettingsManager(context: Context) {
    private var activity = context as Activity
    private var sharedPref: SharedPreferences


    init {
        sharedPref = activity.getSharedPreferences("settings", Context.MODE_PRIVATE)

    }

    // Proprieties for identifying the value on the SharedProprieties
    private val gayestModeAlways_prop = object { val id = "gmode_always"; val value = false }
    private val soundOnlyOnStart_prop = object { val id = "sound_onlystart"; val value = false }
    private val soundNever_prop = object { val id = "sound_never"; val value = false }

    // public getters
    var gayestModeAlways: Boolean
        get() = sharedPref.getBoolean(gayestModeAlways_prop.id, gayestModeAlways_prop.value)
        set(value) {
            with (sharedPref.edit()) {
                putBoolean(gayestModeAlways_prop.id, value)
                apply()
            }
        }

    var soundOnlyOnStart: Boolean
        get() = sharedPref.getBoolean(soundOnlyOnStart_prop.id, soundOnlyOnStart_prop.value)
        set(value) {
            with (sharedPref.edit()) {
                putBoolean(soundOnlyOnStart_prop.id, value)
                apply()
            }
        }

    var soundNever: Boolean
        get() = sharedPref.getBoolean(soundNever_prop.id, soundNever_prop.value)
        set(value) {
            with (sharedPref.edit()) {
                putBoolean(soundNever_prop.id, value)
                apply()
            }
        }

}