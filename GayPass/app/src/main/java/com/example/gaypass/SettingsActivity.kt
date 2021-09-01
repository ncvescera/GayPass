package com.example.gaypass

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.gaypass.managers.SettingsManager
import com.example.gaypass.managers.ThemeManager
import com.google.android.material.switchmaterial.SwitchMaterial


class SettingsActivity : AppCompatActivity() {
    // GUI Switches
    private lateinit var neverPlaySounds:   SwitchMaterial
    private lateinit var soundsOnlyOnStart: SwitchMaterial
    private lateinit var loudAF:            SwitchMaterial
    private lateinit var emojyOnlyOnStart:  SwitchMaterial
    private lateinit var quotesOnlyOnStart: SwitchMaterial
    private lateinit var quotesOnClick:     SwitchMaterial
    private lateinit var noQuotes:          SwitchMaterial

    // utils Managers
    private lateinit  var settingManager:   SettingsManager
    private lateinit  var themeManager:     ThemeManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // GUI get refs
        neverPlaySounds     = findViewById(R.id.sound_never)
        soundsOnlyOnStart   = findViewById(R.id.sound_onlyonstart)
        loudAF              = findViewById(R.id.sound_loudaf)
        emojyOnlyOnStart    = findViewById(R.id.gui_emojyOnlyStart)
        quotesOnlyOnStart   = findViewById(R.id.gui_quotesOnlyStart)
        quotesOnClick       = findViewById(R.id.gui_quotesOnClick)
        noQuotes            = findViewById(R.id.gui_noQuotes)

        // utils Managers
        settingManager  = SettingsManager(this)
        themeManager    = ThemeManager(this, window, supportActionBar, findViewById(R.id.settings_container))

        // generating radio buttons
        generateThemesButtons()

        // init switches with stored value
        neverPlaySounds.isChecked       = settingManager.soundNever
        soundsOnlyOnStart.isChecked     = settingManager.soundOnlyOnStart
        loudAF.isChecked                = settingManager.loudAF
        emojyOnlyOnStart.isChecked      = settingManager.emojyOnlyOnStart
        quotesOnlyOnStart.isChecked     = settingManager.quotesOnlyOnStart
        quotesOnClick.isChecked         = settingManager.quotesOnClick
        noQuotes.isChecked              = settingManager.noQuotes

        // apply theme
        themeManager.applyTheme()

        // onClickListeners
        // --- Never Play Sounds --- //
        neverPlaySounds.setOnClickListener {
            val this_button = it as SwitchMaterial

            // set new Value
            settingManager.soundNever = this_button.isChecked

            // disable conflicting buttons
            soundsOnlyOnStart.isChecked     = false
            loudAF.isChecked                = false
            settingManager.soundOnlyOnStart = false
            settingManager.loudAF           = false
        }

        // --- Sounds Only on StartUp --- //
        soundsOnlyOnStart.setOnClickListener {
            val this_button = it as SwitchMaterial

            // set new Value
            settingManager.soundOnlyOnStart = this_button.isChecked

            // disable conflicting buttons
            neverPlaySounds.isChecked = false
            settingManager.soundNever = false
        }

        // --- Loud AF Mode --- //
        loudAF.setOnClickListener {
            val this_button = it as SwitchMaterial

            // set new Value
            settingManager.loudAF = this_button.isChecked

            // disable conflicting buttons
            neverPlaySounds.isChecked = false
            settingManager.soundNever = false
        }

        // --- Emojy Only on StartUp --- //
        emojyOnlyOnStart.setOnClickListener {
            val this_button = it as SwitchMaterial

            // set new Value
            settingManager.emojyOnlyOnStart = this_button.isChecked
        }

        quotesOnlyOnStart.setOnClickListener {
            val this_button = it as SwitchMaterial

            // set new Value
            settingManager.quotesOnlyOnStart = this_button.isChecked

            // disable conflicting buttons
            noQuotes.isChecked      = false
            settingManager.noQuotes = false
        }

        quotesOnClick.setOnClickListener {
            val this_button = it as SwitchMaterial

            // set new Value
            settingManager.quotesOnClick = this_button.isChecked

            // disable conflicting buttons
            noQuotes.isChecked      = false
            settingManager.noQuotes = false
        }

        noQuotes.setOnClickListener {
            val this_button = it as SwitchMaterial

            // set new Value
            settingManager.noQuotes = this_button.isChecked

            // disable conflicting buttons
            quotesOnlyOnStart.isChecked         = false
            quotesOnClick.isChecked             = false
            settingManager.quotesOnlyOnStart    = false
            settingManager.quotesOnClick        = false
        }
    }

    private fun generateThemesButtons() {
        var radioButtons = arrayListOf<RadioButton>()                         // list for storing all the radio buttons
        val radioGroup   = findViewById<RadioGroup>(R.id.radioGroup_themes)   // radioGroup in the Activity

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
    }
}