package com.silvozatechnologies.diggredditclone.common.utility

import org.junit.Test

class RandomUtilityTest {
    @Test
    fun generateRandomAlphanumericString_shouldReturnRighLength() {
        val length = 20
        val randomAlphanumericString = generateRandomAlphanumericString(length = length)
        assert(randomAlphanumericString.length == length)
    }

    @Test
    fun generateRandomAlphanumericString_shouldBeAlphanumeric() {
        val randomAlphanumericString = generateRandomAlphanumericString(length = 20)
        assert(isAlphaNumeric(randomAlphanumericString))
    }

    @Test
    fun generateRandomAlphanumericString_shouldBeRandom() {
        val length = 20
        val randomAlphanumericString1 = generateRandomAlphanumericString(length = length)
        val randomAlphanumericString2 = generateRandomAlphanumericString(length = length)
        val randomAlphanumericString3 = generateRandomAlphanumericString(length = length)
        assert(randomAlphanumericString1 != randomAlphanumericString2 &&
                randomAlphanumericString2 != randomAlphanumericString3 &&
                randomAlphanumericString3 != randomAlphanumericString1)
    }

    private fun isAlphaNumeric(string: String) : Boolean {
        var isAlphaNumeric = true
        for (i in 0 until string.length) {
            val char = string[i]
            if (!Character.isLetter(char) && !Character.isDigit(char)) {
                isAlphaNumeric = false
                break
            }
        }
        return isAlphaNumeric
    }
}