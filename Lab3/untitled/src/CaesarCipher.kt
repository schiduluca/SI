class CaesarCipher constructor(language: Language) : CypherCracker(language) {

    override fun decrypt(string: String) {
        val result = language.decypher(string)

        println(result)
    }

}