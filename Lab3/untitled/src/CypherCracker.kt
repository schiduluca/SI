abstract class CypherCracker(language: Language) {

    protected var language = language

    abstract fun decrypt(string : String)

}