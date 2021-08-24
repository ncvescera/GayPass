package com.example.gaypass

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.gaypass.Managers.ThemeManager

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        // apply theme
        ThemeManager(this, window, supportActionBar, findViewById(R.id.info_box)).applyTheme()
    }
}