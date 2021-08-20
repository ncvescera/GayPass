package com.example.gaypass

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial


class SettingsActivity : AppCompatActivity() {
    private lateinit var gayestModeAlwaysOn: SwitchMaterial
    private lateinit var neverPlaySounds: SwitchMaterial
    private lateinit  var settingManager: SettingsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // get SettingManager
        settingManager = SettingsManager(this)

        // GUI get refs
        gayestModeAlwaysOn = findViewById(R.id.gayestMode)
        neverPlaySounds = findViewById(R.id.sound_never)

        // set switches with stored value
        gayestModeAlwaysOn.isChecked = settingManager.gayestModeAlways
        neverPlaySounds.isChecked =settingManager.soundNever

        // onChangeListeneres
        gayestModeAlwaysOn.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            val newValue = isChecked
            settingManager.gayestModeAlways = newValue
        })

        neverPlaySounds.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            val newValue = isChecked
            settingManager.soundNever = newValue
        })
    }
}