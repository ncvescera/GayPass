package com.example.gaypass

import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.gaypass.Managers.SettingsManager
import com.example.gaypass.Managers.ThemeManager
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlin.math.sign


class SettingsActivity : AppCompatActivity() {
    private lateinit var neverPlaySounds: SwitchMaterial
    private lateinit var soundsOnlyOnStart: SwitchMaterial
    private lateinit var emojyOnlyOnStart: SwitchMaterial

    private lateinit  var settingManager: SettingsManager
    private lateinit  var themeManager: ThemeManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // utils Managers
        settingManager = SettingsManager(this)
        themeManager = ThemeManager(this, window, supportActionBar, findViewById(R.id.settings_container))

        // --- generating radio buttons ---//
        var radioButtons = arrayListOf<RadioButton>()                       // list for storing all the radio buttons
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup_themes)   // radioGroup in the Activiry

        for (i in themeManager.themes.indices) {
            // create new Radio Button
            var tmp = RadioButton(this)

            // set text
            tmp.text = themeManager.themes[i].name

            // set checked if is the active theme
            tmp.isChecked = (i == themeManager.theme)

            // set click listener
            tmp.setOnClickListener {
                themeManager.theme = i
            }

            // set id
            tmp.id = i + 100    // must set the id otherwise a radio button remains clicked

            // add to the list
            radioButtons.add(tmp)

            // add to the RadioGroup
            radioGroup.addView(tmp)
        }

        // apply theme
        themeManager.applyTheme()

        // GUI get refs
        neverPlaySounds = findViewById(R.id.sound_never)
        soundsOnlyOnStart = findViewById(R.id.sound_onlyonstart)
        emojyOnlyOnStart = findViewById(R.id.gui_emojyOnlyStart)

        // set switches with stored value
        neverPlaySounds.isChecked       = settingManager.soundNever
        soundsOnlyOnStart.isChecked     = settingManager.soundOnlyOnStart
        emojyOnlyOnStart.isChecked      = settingManager.emojyOnlyOnStart

        // onClickListeners
        // --- Never Play Sounds --- //
        neverPlaySounds.setOnClickListener {
            val this_button = it as SwitchMaterial

            // set new Value
            settingManager.soundNever = this_button.isChecked

            soundsOnlyOnStart.isChecked = false
            settingManager.soundOnlyOnStart = false
        }

        // --- Sounds Only on StartUp --- //
        soundsOnlyOnStart.setOnClickListener {
            val this_button = it as SwitchMaterial

            // set new Value
            settingManager.soundOnlyOnStart = this_button.isChecked

            neverPlaySounds.isChecked = false
            settingManager.soundNever = false
        }

        // --- Emojy Only on StartUp --- //
        emojyOnlyOnStart.setOnClickListener {
            val this_button = it as SwitchMaterial

            // set new Value
            settingManager.emojyOnlyOnStart = this_button.isChecked
        }
    }
}