package com.example.gaypass

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.gaypass.Managers.SettingsManager
import com.example.gaypass.Managers.ThemeManager
import com.google.android.material.switchmaterial.SwitchMaterial


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

        // apply theme
        themeManager.applyTheme()

        // GUI get refs
        neverPlaySounds = findViewById(R.id.sound_never)
        soundsOnlyOnStart = findViewById(R.id.sound_onlyonstart)
        emojyOnlyOnStart = findViewById(R.id.gui_emojyOnlyStart)

        // set switches with stored value
        intiRadioButtons()
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

    // --- RADIO BUTTON SECTION --- //
    // initialize radio buttons (themes) with the selected theme
    private fun intiRadioButtons() {
        when(settingManager.currentTheme) {
            ThemeManager.DEFAULT -> {
                findViewById<RadioButton>(R.id.default_theme).isChecked = true
            }
            ThemeManager.GAYEST_THEME -> {
                findViewById<RadioButton>(R.id.gayest_theme).isChecked = true
            }
            ThemeManager.GAYLATENTE_THEME -> {
                findViewById<RadioButton>(R.id.gayLatente_theme).isChecked = true
            }
            ThemeManager.RAINBOW_THEME -> {
                findViewById<RadioButton>(R.id.rainbow_theme).isChecked = true
            }
        }
    }

    // function to handle the theme selection
    // is setted on the radiobutton's field onClickListener in the activiry_setting.xml file
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.default_theme -> {
                    // Apply DEFAULT Theme
                    if (checked) {
                        themeManager.theme = ThemeManager.DEFAULT
                    }
                }
                R.id.gayest_theme -> {
                    // Apply GAYEST Theme
                    if (checked) {
                        themeManager.theme = ThemeManager.GAYEST_THEME
                    }
                }
                R.id.gayLatente_theme -> {
                    // Apply GAYLATENTE Theme
                    if (checked) {
                        themeManager.theme = ThemeManager.GAYLATENTE_THEME
                    }
                }
                R.id.rainbow_theme -> {
                    // Apply GAYLATENTE Theme
                    if (checked) {
                        themeManager.theme = ThemeManager.RAINBOW_THEME
                    }
                }
            }
        }
    }
}