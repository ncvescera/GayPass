package com.example.gaypass.managers

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SettingsManager(context: Context) {
    private var activity = context as Activity
    private var sharedPref: SharedPreferences = activity.getSharedPreferences("settings", Context.MODE_PRIVATE)

    // --- PROP SECTION --- //
    // Proprieties for identifying the value on the SharedProprieties

    // themes
    private val currentTheme_prop       = SettingProp("current_theme", 0)

    // sounds
    private val soundOnlyOnStart_prop   = SettingProp("sound_onlystart", false)
    private val soundNever_prop         = SettingProp("sound_never", false)
    private val loudAF_prop             = SettingProp("sound_loudaf", false)

    // gui
    private val emojyOnlyOnStart_prop   = SettingProp("emojy_onlystart",false)
    private val quotesOnlyOnStart_prop  = SettingProp("quotes_OnlyStart", false)
    private val noQuotes_prop           = SettingProp("noquotes", false)
    private val quotesOnClick_prob      = SettingProp("quotesOnClick", false)

    // --- PUBLIC ATTRS SECTION ---//
    // public getters & setters

    /* ** THEME ** */
    var currentTheme: Int
        get() = sharedPref.getInt(currentTheme_prop.id, currentTheme_prop.default_value as Int)
        set(value) {
            with (sharedPref.edit()) {
                putInt(currentTheme_prop.id, value)
                apply()
            }
        }

    /* ** SOUNDS  ** */
    var soundOnlyOnStart: Boolean
        get() = sharedPref.getBoolean(soundOnlyOnStart_prop.id, soundOnlyOnStart_prop.default_value as Boolean)
        set(value) {
            with (sharedPref.edit()) {
                putBoolean(soundOnlyOnStart_prop.id, value)
                apply()
            }
        }

    var soundNever: Boolean
        get() = sharedPref.getBoolean(soundNever_prop.id, soundNever_prop.default_value as Boolean)
        set(value) {
            with (sharedPref.edit()) {
                putBoolean(soundNever_prop.id, value)
                apply()
            }
        }

    var loudAF: Boolean
        get() = sharedPref.getBoolean(loudAF_prop.id, loudAF_prop.default_value as Boolean)
        set(value) {
            with (sharedPref.edit()) {
                putBoolean(loudAF_prop.id, value)
                apply()
            }
        }

    /* ** GUI ** */
    var emojyOnlyOnStart: Boolean
        get() = sharedPref.getBoolean(emojyOnlyOnStart_prop.id, emojyOnlyOnStart_prop.default_value as Boolean)
        set(value) {
            with (sharedPref.edit()) {
                putBoolean(emojyOnlyOnStart_prop.id, value)
                apply()
            }
        }

    var quotesOnlyOnStart: Boolean
        get() = sharedPref.getBoolean(quotesOnlyOnStart_prop.id, quotesOnlyOnStart_prop.default_value as Boolean)
        set(value) {
            with(sharedPref.edit()) {
                putBoolean(quotesOnlyOnStart_prop.id, value)
                apply()
            }
        }

    var noQuotes: Boolean
        get() = sharedPref.getBoolean(noQuotes_prop.id, noQuotes_prop.default_value as Boolean)
        set(value) {
            with (sharedPref.edit()) {
                putBoolean(noQuotes_prop.id, value)
                apply()
            }
        }

    var quotesOnClick: Boolean
        get() = sharedPref.getBoolean(quotesOnClick_prob.id, quotesOnClick_prob.default_value as Boolean)
        set(value) {
            with (sharedPref.edit()) {
                putBoolean(quotesOnClick_prob.id, value)
                apply()
            }
        }
}