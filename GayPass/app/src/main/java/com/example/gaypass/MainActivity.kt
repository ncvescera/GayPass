package com.example.gaypass


import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.gaypass.managers.SettingsManager
import com.example.gaypass.managers.ThemeManager
import com.example.gaypass.utils.RandomGenerator
import android.graphics.Bitmap
import com.example.gaypass.utils.DialogMaker
import com.example.gaypass.managers.ImageManager
import com.example.gaypass.utils.MLScanner


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
    //private          var counter      = 0

    // utils Objects
    private lateinit  var imageManager:     ImageManager
    private lateinit  var audioManager:     AudioManager
    private lateinit  var mediaPlayer:      MediaPlayer
    private lateinit  var settingManager:   SettingsManager
    private lateinit  var themeManager:     ThemeManager

    // Constants
    private          val PICK_IMAGE = 100
    private          var MAX_VOL    = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // GUI getting refs
        layout          = findViewById(R.id.layout)
        imageView       = findViewById(R.id.imageView)
        bg              = findViewById(R.id.bg)
        quoteTextView   = findViewById(R.id.quoteText)
        waringTextView  = findViewById(R.id.warningText)

        // init settingManager & setting the theme
        settingManager  = SettingsManager(this)
        themeManager    = ThemeManager(this, window, supportActionBar, layout)
        imageManager    = ImageManager(this)

        // utils Object init
        mediaPlayer     =  MediaPlayer.create(this, R.raw.imgay)
        audioManager    =  getSystemService(android.content.Context.AUDIO_SERVICE) as AudioManager

        // set path and volume values
        MAX_VOL = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)    // get the max level volume for Loud A.F. mode


        // onClickListeners
        quoteTextView.setOnClickListener {
            if (settingManager.quotesOnClick)
                printText()
        }

        /*
        // ill leave this for the new easter egg !
        imageView.setOnClickListener {
            if (counter < 10)
                counter ++
            else {
                //enableGayestMode()
                //Toast.makeText(this, "GayestMode Active !", Toast.LENGTH_SHORT).show()
            }
        }
        */

        // apply current settings
        themeManager.applyTheme()   // apply current theme

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

    override fun onResume() {
        super.onResume()

        themeManager.applyTheme()

        // redraw the quote
        if (isPassLoaded) {
            if (!settingManager.quotesOnlyOnStart || quoteTextView.text == "")
                printText()

            // Sounds Only on Startup
            if (!settingManager.soundOnlyOnStart)
                playSound()
        }
    }

    private fun playSound() {
        // check volume level
        val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        if (currentVolume <= 5 && !settingManager.loudAF && !settingManager.soundNever)
            Toast.makeText(this, getString(R.string.lowaudio_warning), Toast.LENGTH_SHORT).show()

        // play sound
        if (!settingManager.soundNever) {
            if(settingManager.loudAF)
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, MAX_VOL, 0) // set max vol

            mediaPlayer.start()
        }
    }

    // update the Quotes TextView with a random quote
    private fun printText() {
        // update the TextView with a random quote
        quoteTextView.text = if (settingManager.noQuotes) "" else RandomGenerator.getRandomQuote()

        // update the TextViews' visibility
        waringTextView.visibility = View.INVISIBLE
        quoteTextView.visibility  = View.VISIBLE

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
                DialogMaker.printDialog(
                    this,
                    R.string.dialogTitle_deleteqr,
                    R.string.dialogMessage_deleteqr,
                    {
                        // try to delete the QR from the Private Storage
                        if (imageManager.delete()) {
                            // print success toast
                            Toast.makeText(
                                this,
                                getString(R.string.qrdelete_success),
                                Toast.LENGTH_LONG
                            ).show()

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
                        }
                        else
                            Toast.makeText(
                                this,
                                getString(R.string.qrdelete_error),
                                Toast.LENGTH_LONG
                            ).show()
                    },
                    {}
                )

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

    // handles the return values of the other Activities
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // update the ImageView with the selected pic (if the operation is successfully)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            if (data != null) {
                // get data uri
                imageUri = data.data!!

                // init QR Scanner
                val scanner = MLScanner(this)

                // perform scan and execute success function
                scanner.scan(imageUri) {
                    if (it.isEmpty()) {
                        DialogMaker.printDialog(
                            this,
                            "QR Warning",
                            "It seems this image does not contain any QRCodes ..."
                        )

                        // update ImageView image
                        imageView.setImageURI(imageUri)

                        // save the QR Code in the Private Storage
                        imageManager.save(imageUri)
                    }
                    else {
                        val bb = it[0].boundingBox              // takes the first qr detected
                        val bitmap = imageManager.URItoBitmap(imageUri)
                        var result: Bitmap = bitmap             // at the end it will contains the original bitmap or
                                                                // the cropped image if a boundingbox exists

                        // if a boundingbox exists, crop the qr
                        if (bb != null) {
                            val croppedBmp: Bitmap = Bitmap.createBitmap(
                                bitmap,
                                bb.left,
                                bb.top,
                                bb.width(),
                                bb.height()
                            )

                            // set result with cropped bitmap
                            result = croppedBmp
                        }

                        // update ImageView image
                        imageView.setImageBitmap(result)

                        // save the QR Code in the Private Storage
                        imageManager.save(result)
                    }

                    // set the Quotes TextView with a random quote
                    printText()

                    // says that the qr is loaded
                    isPassLoaded = true

                    // showing success Toast
                    Toast.makeText(this, getString(R.string.qrupload_success), Toast.LENGTH_LONG).show()
                }

            } else
                Toast.makeText(this, getString(R.string.qrupload_error), Toast.LENGTH_LONG).show()
        }
    }

    // ------------------ QR MANAGEMENT SECTION ------------------ //

    // try to load the pass from the Private Storage (if exists)
    // Return True if correctly loaded, else False
    private fun loadPass(): Boolean {
        // check if uri given
        val uri = imageManager.load()
        if (uri != null) {
            // load the image and update the quotes TextView text
            imageView.setImageURI(uri)
            printText()

            return true
        }

        return false
    }
}

