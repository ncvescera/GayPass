package com.example.gaypass

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import com.example.gaypass.managers.ThemeManager

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val textView = findViewById<TextView>(R.id.warningText)
        textView.movementMethod = LinkMovementMethod.getInstance()

        // apply theme
        ThemeManager(this, window, supportActionBar, findViewById(R.id.info_box)).applyTheme()
    }
}