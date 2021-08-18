package com.example.gaypass

import android.content.Intent
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
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream


class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var quoteTextView: TextView
    private lateinit var waringTextView: TextView

    private lateinit var imageUri: Uri
    private val quotes = RandomQuotes()
    private var isPassLoaded: Boolean = false
    private val PICK_IMAGE = 100
    private lateinit var DATA_PATH: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // change the Activity's title
        // TODO: find a better way to do it ( in the manifest )
        title = getString(R.string.title_name)

        DATA_PATH = "${filesDir.absoluteFile}/gaypass.png"

        imageView = findViewById(R.id.imageView)
        quoteTextView = findViewById(R.id.quoteText)
        waringTextView = findViewById(R.id.warningText)

        // try to load the qr if previously stored
        isPassLoaded = loadPass()
        if(isPassLoaded) {
            printText()

        }
        else {
            // set invisible the quotes TextView
            quoteTextView.visibility = View.INVISIBLE

        }
    }

    override fun onResume() {
        super.onResume()

        // redraw the quote
        if (isPassLoaded)
            printText()
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
                    Toast.makeText(this, "Successfully QR deleted !", Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(this, "Unable to delete the QR :/", Toast.LENGTH_LONG).show()

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
            else -> super.onOptionsItemSelected(item)
        }
    }

    // ------------------ ACTIVITY RESULT SECTION ------------------ //

    // handles the return values of the other Activityes
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // update the ImageView with the selected pic (if the operation is successfully)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data!!.data!!    // TODO: checck if a better way exists
            imageView.setImageURI(imageUri)

            // set the Quotes TextView with a random quote
            printText()

            // save the QR Code in the Private Storage
            saveUriToFile(imageUri)

            // says that the qr is loaded
            isPassLoaded = true

            Toast.makeText(this, "QR uploaded successfully !", Toast.LENGTH_LONG).show()

        }
    }

    // ------------------ QR MANAGEMENT SECTION ------------------ //

    // update the Quotes TextView with a random quote
    private fun printText() {
        // update the TextView with a random quote
        quoteTextView.text = quotes.getRandom()

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

