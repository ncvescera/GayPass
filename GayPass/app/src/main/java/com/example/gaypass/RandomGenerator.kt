package com.example.gaypass

class RandomGenerator {
    // TODO: add new Quotes
    private val QUOTES = arrayListOf<String>(
        "I'M GAAAY !!",
        "BOOMER REMOVER",
        "I have a crippling depression",
        "Mongoloid Son",
        "I'M AUTISTIC BOY",
        "NO HOMO",
        "UR GAY",
        "HEY BOSSSS" ,
        "GAY WHO SCAN" ,
        "WHO SAID NATURE WAS PERFECT?" ,
        "JOIN YOUR FELLOW UNICORNS!" ,
        "SCAN ME ALL NAUGHTY BOY" ,
        "MLMLMLMLMLMLML" ,
        "GOOD BOYS LET ME IN" ,
        "PROUD FATHER OF AN ADOPTED BOY" ,
        "ASSHOLE PASS INCLUDED" ,
        "SCAN ONE TAKE TWO" ,
        "PICK THE SOAP UP" ,
        "WHO'S YOUR DADDY?" ,
        "PREJUDICE IS A GOOD PLACE TO START" ,
        "DISCRIMINATION IS NEVER TOO MUCH" ,
        "ENBY PUSSY" ,
        "GENDERFLUID COCKTAIL" ,
        "NONBINARY PASS" ,
        "USE ME!"
    )

    // TODO: add more Emojys
    private val EMOJYS = arrayListOf<String>(
        "\uD83E\uDD84",                                             // unicorn
        "\uD83C\uDF38",                                             // pink flower
        "\uD83D\uDC9E",                                             // hearts
        "\uD83D\uDC85\uD83C\uDFFF",                                 // black nails
        "\uD83D\uDC68\uD83C\uDFFB\u200D\uD83D\uDE92",               // fireman
        "\uD83D\uDC90",                                             // bouquet
        "\uD83D\uDC68\u200D❤️\u200D\uD83D\uDC8B\u200D\uD83D\uDC68", // gay kiss
        "\uD83C\uDF08",                                             // rainbow
        "\uD83C\uDFF3️\u200D\uD83C\uDF08",                           // rainbowflag
        "\uD83C\uDF6D"                                              // lollipop
    )

    fun getRandomQuote(): String {
        var rand_index = (0 until QUOTES.size).random()

        return QUOTES[rand_index]
    }

    fun getRandomEmojy(): String {
        var rand_index = (0 until EMOJYS.size).random()

        return EMOJYS[rand_index]
    }
}