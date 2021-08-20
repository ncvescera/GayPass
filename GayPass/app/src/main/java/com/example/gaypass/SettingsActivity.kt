package com.example.gaypass

import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial


class SettingsActivity : AppCompatActivity() {
    private lateinit var gayestModeAlwaysOn: SwitchMaterial
    private lateinit var neverPlaySounds: SwitchMaterial
    private lateinit var soundsOnlyOnStart: SwitchMaterial

    private lateinit  var settingManager: SettingsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // get SettingManager
        settingManager = SettingsManager(this)

        // GUI get refs
        gayestModeAlwaysOn = findViewById(R.id.gayestMode)
        neverPlaySounds = findViewById(R.id.sound_never)
        soundsOnlyOnStart = findViewById(R.id.sound_onlyonstart)

        // set switches with stored value
        gayestModeAlwaysOn.isChecked    = settingManager.gayestModeAlways
        neverPlaySounds.isChecked       = settingManager.soundNever
        soundsOnlyOnStart.isChecked     = settingManager.soundOnlyOnStart

        // onClickListeners
        // --- GAYESTMODE --- //
        gayestModeAlwaysOn.setOnClickListener {
            val this_button = it as SwitchMaterial

            // set new Value
            settingManager.gayestModeAlways = this_button.isChecked

        }

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
    }
}