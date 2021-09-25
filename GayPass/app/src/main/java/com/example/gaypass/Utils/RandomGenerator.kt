package com.example.gaypass.utils

class RandomGenerator {
    companion object {
        private val QUOTES = arrayListOf<String>(
            "I'M GAAAY !!",
            "BOOMER REMOVER",
            "I HAVE A CRIPPLING DEPRESSION",
            "MONGOLOID SON",
            "I'M AUTISTIC BOY",
            "NO HOMO",
            "UR GAY",
            "HEY BOSSSS",
            "GAY WHO SCAN",
            "WHO SAID NATURE WAS PERFECT?",
            "JOIN YOUR FELLOW UNICORNS!",
            "SCAN ME ALL NAUGHTY BOY",
            "MLMLMLMLMLMLML",
            "GOOD BOYS LET ME IN",
            "PROUD FATHER OF AN ADOPTED BOY",
            "ASSHOLE PASS INCLUDED",
            "SCAN ONE TAKE TWO",
            "PICK THE SOAP UP",
            "WHO'S YOUR DADDY?",
            "PREJUDICE IS A GOOD PLACE TO START",
            "DISCRIMINATION IS NEVER TOO MUCH",
            "ENBY PUSSY",
            "GENDERFLUID COCKTAIL",
            "NONBINARY PASS",
            "USE ME!"
        )

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
            "\uD83C\uDF6D",                                             // lollipop
            "\uD83E\uDD20",                                             // cowboy
            "\uD83D\uDCA6",                                             //  sweat droplets
            "\uD83D\uDC81",                                             //  woman tipping hand
            "\uD83D\uDC77",                                             // man construction worker
            "\uD83E\uDD34",                                             // prince
            "\uD83D\uDC78",                                             // princess
            "\uD83E\uDDB8",                                             // superhero
            "\uD83E\uDDB9",                                             // supervillan
            "\uD83E\uDDDA",                                             // fairy
            "\uD83E\uDDDC",                                             // merperson
            "\uD83D\uDC6F",                                             // woman bunny
            "\uD83E\uDD38",                                             // person doing cartwheel
            "\uD83D\uDC6C",                                             // 2 man holding hands
            "\uD83D\uDC6D",                                             // 2 women holding hands
            "\uD83D\uDC37",                                             // pig face
            "\uD83E\uDDA9",                                             // flamingo
            "\uD83E\uDD8B",                                             // butterfly
            "\uD83C\uDF46",                                             // aubergine
            "\uD83E\uDD52",                                             // cucumber
            "\uD83E\uDD56",                                             // baguette
            "\uD83C\uDF2D",                                             // hot dog
            "\uD83C\uDFBA",                                             // trumpet
            "\u26A7",                                                   // Trasgender symbol
            "\uD83C\uDDEB\uD83C\uDDF7"                                  // franch flag
        )

        fun getRandomQuote(): String {
            val rand_index = (0 until QUOTES.size).random()

            return QUOTES[rand_index]
        }

        fun getRandomEmojy(): String {
            val rand_index = (0 until EMOJYS.size).random()

            return EMOJYS[rand_index]
        }
    }
}