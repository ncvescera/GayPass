package com.example.gaypass.utilities

import android.app.AlertDialog
import android.content.Context

class DialogMaker {
    companion object {
        // print Dialog with given Title, Message, Success Function and Fail Function
        fun printDialog(context: Context, title: Int, message: Int, success_function: () -> Unit, fail_function: () -> Unit) {
            context.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("OK") { dialog, id ->
                        success_function()
                    }
                    setNegativeButton("Cancel") { dialog, id ->
                            fail_function()
                    }
                    setTitle(title)
                    setMessage(message)
                }

                // Create the AlertDialog
                builder.create()
            }.show()
        }

        // overloading with String Title and Message
        fun printDialog(context: Context, title: String, message: String, success_function: () -> Unit, fail_function: () -> Unit) {
            context.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("OK") { dialog, id ->
                        success_function()
                    }
                    setNegativeButton("Cancel") { dialog, id ->
                        fail_function()
                    }
                    setTitle(title)
                    setMessage(message)
                }

                // Create the AlertDialog
                builder.create()
            }.show()
        }

        // overloading with String Title and Message
        fun printDialog(context: Context, title: String, message: String) {
            context.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("OK") { dialog, id -> }
                    setNegativeButton("Cancel") { dialog, id -> }
                    setTitle(title)
                    setMessage(message)
                }

                // Create the AlertDialog
                builder.create()
            }.show()
        }

    }

}