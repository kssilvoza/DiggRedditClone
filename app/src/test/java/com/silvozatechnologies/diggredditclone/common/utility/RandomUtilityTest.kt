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
        var isAlphaNumeric = true
        for (i in 0 until randomAlphanumericString.length) {
            val char = randomAlphanumericString[i]
            if (!Character.isLetter(char) && !Character.isDigit(char)) {
                isAlphaNumeric = false
                break
            }
        }
        assert(isAlphaNumeric)
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
}