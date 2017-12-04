import java.io.File
import java.io.InputStream
import java.util.*

class EnglishLanguage : Language {

    var mostPopular = 'E'
    var wordSet = HashSet<String>()
    var closestToAnswer = TreeMap<Int, String>()

    init {
        val inputStream: InputStream = File("words.txt").inputStream()
        inputStream.bufferedReader().useLines { lines -> lines.forEach { wordSet.add(it)} }
    }

    override fun decypher(string : String) : String {
        var replace = string.replace(Regex("\\s|:|,|\\."), "")

        val map = replace.toCharArray().map { it.toUpperCase()}.groupingBy { it }.eachCount().toSortedMap()

        while (map.isNotEmpty()) {
            val maxBy = map.maxBy { (_, value) -> value }
            val shift = Math.abs(maxBy?.key?.minus(mostPopular)!! - 1)
            var result = ""

            result = getResult(string, shift, result)
            validateWord(result)
            map.remove(maxBy.key)
        }
        return closestToAnswer.lastEntry().value
    }


    private fun getResult(string: String, shift: Int, result: String): String {
        var result1 = result
        string.toCharArray().map {
            var plus = it
            if (it in 'A'..'Z') {
                plus = it.plus(shift)
                if (plus.toInt() > 90) {
                    plus = plus.minus(26)
                }
            }
            plus
        }.forEach { result1 += it }
        return result1
    }


    private fun validateWord(string: String) {
        val count = string.split(Regex("\\s|:"))
                .filter { wordSet.contains(it.toLowerCase()) }.count()
                closestToAnswer.put(count, string)
    }

}