package com.example.gaypass.managers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ImageManager(private val context: Context) {
    private val DATA_PATH: String = "${context.filesDir.absoluteFile}/gaypass.png"

    fun load(): Uri? {
        // if the file exists return his uri
        if (File(DATA_PATH).exists())
            return Uri.parse(DATA_PATH)

        return null
    }

    // try to delete the QRCode file
    // if success return True, else False
    fun delete(): Boolean {
        // delete the QR from the Private Storage
        val file = File(DATA_PATH)
        val deleted: Boolean = file.delete()

        return deleted
    }

    fun save(uri: Uri) {
        val inputStream = context.contentResolver.openInputStream(uri)
        val output = FileOutputStream(File(DATA_PATH))
        inputStream?.copyTo(output, 4 * 1024)
    }

    fun save(bitmap: Bitmap) {
        try {
            FileOutputStream(DATA_PATH).use { out ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out) // bmp is your Bitmap instance
            }
        } catch (e: IOException) {
            e.printStackTrace()

            Toast.makeText(context, "Can not save file :/", Toast.LENGTH_SHORT).show()
        }
    }

     fun URItoBitmap(uri: Uri): Bitmap {
        val bitmapInputStream = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(bitmapInputStream)
        bitmapInputStream!!.close()

        return bitmap
    }
}