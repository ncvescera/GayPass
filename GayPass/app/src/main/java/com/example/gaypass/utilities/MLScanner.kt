package com.example.gaypass.utilities

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.lang.Exception

class MLScanner(private val context: Context) {
    private var scanner: BarcodeScanner

    init {
        // barcode scanner option
        // setting for recognize only QR CODE
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE
            )
            .build()

        // getting scanner instance
        scanner = BarcodeScanning.getClient(options)
    }

    // return scan result
    // for now never used
    fun scan(uri: Uri): Task<MutableList<Barcode>> {
        val image = prepareImage(uri)

        return scanner.process(image)
    }

    // perform scan and execute success on success
    // if error occur, execute default log
    fun scan(uri: Uri, success: (List<Barcode>) -> Unit) {
        val image = prepareImage(uri)
        scanner.process(image)
            .addOnSuccessListener(success)
            .addOnFailureListener {
                Log.d("BARCODE", "ERROR !")
            }
    }

    // perform scan and execute success on success
    // if error occur, execute given error function
    fun scan(uri: Uri, success: (List<Barcode>) -> Unit, failure: (Exception) -> Unit) {
        val image = prepareImage(uri)
        val result = scanner.process(image)
            .addOnSuccessListener(success)
            .addOnFailureListener(failure)
    }

    // prepare image for scanning phase
    private fun prepareImage(uri: Uri): InputImage {
        return InputImage.fromFilePath(context, uri)
    }
}