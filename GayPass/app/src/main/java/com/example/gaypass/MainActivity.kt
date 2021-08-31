package com.example.gaypass

import android.content.Intent
import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.gaypass.Managers.SettingsManager
import com.example.gaypass.Managers.ThemeManager
import com.example.gaypass.Utils.RandomGenerator
import java.io.File
import java.io.FileOutputStream


class MainActivity : AppCompatActivity() {
    // GUI elements
    private lateinit var imageView:     ImageView
    private lateinit var bg:            ConstraintLayout
    private lateinit var quoteTextView: TextView
    private lateinit var waringTextView:TextView
    private lateinit var layout:        ConstraintLayout

    // utils vars
    private lateinit var imageUri: Uri
    private          var isPassLoaded = false
    private          var counter      = 0

    // utils Objects
    private           val randomGenerator = RandomGenerator()
    private lateinit  var audioManager: AudioManager
    private lateinit  var mediaPlayer: MediaPlayer
    private lateinit  var settingManager: SettingsManager
    private lateinit var themeManager: ThemeManager

    // Constants
    private          val PICK_IMAGE = 100
    private lateinit var DATA_PATH: String
    private          var MAX_VOL: Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // change the Activity's title
        setRandomTitle()

        DATA_PATH = "${filesDir.absoluteFile}/gaypass.png"

        // init settingManager & setting the theme
        settingManager = SettingsManager(this)
        themeManager = ThemeManager(this, window, supportActionBar, findViewById(R.id.layout))

        themeManager.applyTheme()

        // utils Object init
        mediaPlayer = MediaPlayer.create(this, R.raw.imgay)
        audioManager =  getSystemService(android.content.Context.AUDIO_SERVICE) as AudioManager

        // get the max level volume for Loud A.F. mode
        MAX_VOL = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)

        // GUI getting refs
        layout = findViewById(R.id.layout)
        imageView = findViewById(R.id.imageView)
        bg = findViewById(R.id.bg)
        quoteTextView = findViewById(R.id.quoteText)
        waringTextView = findViewById(R.id.warningText)

        // onClickListeners
        /*
        // ill leave this for the new easteregg !
        imageView.setOnClickListener {
            if (counter < 10)
                counter ++
            else {
                //enableGayestMode()
                //Toast.makeText(this, "GayestMode Active !", Toast.LENGTH_SHORT).show()
            }
        }
        */

        // try to load the qr if previously stored
        isPassLoaded = loadPass()
        if(isPassLoaded) {
            printText()
            playSound()

        }
        else {
            // set invisible the quotes TextView
            quoteTextView.visibility = View.INVISIBLE

        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()

        themeManager.applyTheme()

        // update Emojy title
        if (settingManager.emojyOnlyOnStart == false)
            setRandomTitle()

        // redraw the quote
        if (isPassLoaded) {
            if (!settingManager.quotesOnlyOnStart)
                printText()

            // Sounds Only on StrtUp
            if (settingManager.soundOnlyOnStart == false)
                playSound()
        }
    }

    private fun setRandomTitle() {
        title = "${getString(R.string.app_name)} ${randomGenerator.getRandomEmojy()}"
    }

    private fun playSound() {
        // check volume level
        val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        if (currentVolume <= 5 && !settingManager.loudAF)
            Toast.makeText(this, getString(R.string.lowaudio_warning), Toast.LENGTH_SHORT).show()

        // play sound
        if (settingManager.soundNever == false) {
            if(settingManager.loudAF)
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, MAX_VOL, 0) // set max vol
            mediaPlayer.start()
        }
    }


    // ------------------ OPTION MENU SECTION ------------------ //

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menu_update -> {
                // open the gallery
                val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, PICK_IMAGE)

                true
            }
            R.id.menu_delete -> {
                // delete the QR from the Private Storage
                val file = File(DATA_PATH)
                val deleted: Boolean = file.delete()

                if (deleted)
                    Toast.makeText(this, getString(R.string.qrdelete_success), Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(this, getString(R.string.qrdelete_error), Toast.LENGTH_LONG).show()

                // clear the ImageView
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        this, // Context
                        R.drawable.ic_baseline_image_search_24 // Drawable
                    )
                )

                // update the TextViews visibilities
                quoteTextView.visibility = View.INVISIBLE
                waringTextView.visibility = View.VISIBLE

                // says that the qr is not loaded
                isPassLoaded = false

                true
            }
            R.id.menu_settings -> {
                // start the SettingsActivity
                val info = Intent(this, SettingsActivity::class.java)
                startActivityForResult(info, PICK_IMAGE)

                true
            }
            R.id.menu_info -> {
                // start the InfoActivity
                val info = Intent(this, InfoActivity::class.java)
                startActivityForResult(info, PICK_IMAGE)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // ------------------ ACTIVITY RESULT SECTION ------------------ //

    // handles the return values of the other Activityes
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // update the ImageView with the selected pic (if the operation is successfully)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            if (data != null) {
                imageUri = data.data!!
                imageView.setImageURI(imageUri)

                // set the Quotes TextView with a random quote
                printText()

                // save the QR Code in the Private Storage
                saveUriToFile(imageUri)

                // says that the qr is loaded
                isPassLoaded = true

                Toast.makeText(this, getString(R.string.qrupload_success), Toast.LENGTH_LONG).show()
            } else
                Toast.makeText(this, getString(R.string.qrupload_error), Toast.LENGTH_LONG).show()
        }
    }

    // ------------------ QR MANAGEMENT SECTION ------------------ //

    // update the Quotes TextView with a random quote
    private fun printText() {
        // update the TextView with a random quote
        quoteTextView.text = if (settingManager.noQuotes) "" else randomGenerator.getRandomQuote()

        // update the TextViews' visibility
        waringTextView.visibility = View.INVISIBLE
        quoteTextView.visibility = View.VISIBLE

    }

    // try to load the pass from the Private Storage (if exists)
    // Return True if correctly loaded, else False
    private fun loadPass(): Boolean {
        // chech if the file exists
        val file = File(DATA_PATH)
        if (file.exists()) {
            // load the image and update the quotes TextView text
            imageView.setImageURI(Uri.parse(DATA_PATH))
            printText()

            return true
        }

        return false
    }

    // save the content of a given URI to Private Storage
    private fun saveUriToFile(uri: Uri){
        val inputStream = contentResolver.openInputStream(uri)
        val output = FileOutputStream(File(DATA_PATH))
        inputStream?.copyTo(output, 4 * 1024)
    }
}

