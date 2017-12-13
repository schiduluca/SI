# Lab 3


## Details
Language of choice : Kotlin


## Implimentation


- Take the crypted string and remove all the spaces and other special characters

`` var replace = string.replace(Regex("\\s|:|,|\\."), "")``

- Create a map of letters appearences of the string `` Map<Int, String>``

``val map = replace.toCharArray().map { it.toUpperCase()}.groupingBy { it }.eachCount().toSortedMap()``

- Take the most used letter from a specific language and shift with the most frequent letter in the ``String``.

``var mostPopular = 'E'`` 

- Verify the result with an imported dictionary.


```
private fun validateWord(string: String) {
        val count = string.split(Regex("\\s|:"))
                .filter { wordSet.contains(it.toLowerCase()) }.count()
                closestToAnswer.put(count, string)
    }
```    

- The result with the most contained words is the correct answer.

``        return closestToAnswer.lastEntry().value``


### Application can be used with all the languages as long as you have an imported dictionary of this language.
