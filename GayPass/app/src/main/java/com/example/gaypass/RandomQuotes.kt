package com.example.gaypass

class RandomQuotes {
    // TODO: add new Quotes
    private val QUOTES = arrayListOf<String>(
        "I'M GAAAY !!",
        "BOOMER REMOVER",
        "I have a crippling depression",
        "Mongoloid Son",
        "I'M AUTISTIC BOY",
        "NO HOMO",
        "UR GAY",
        "HEY BOSSSS"
    )

    fun getRandom(): String {
        var rand_index = (0 until QUOTES.size).random()

        return QUOTES[rand_index]
    }
}