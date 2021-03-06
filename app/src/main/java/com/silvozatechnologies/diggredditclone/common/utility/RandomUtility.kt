package com.silvozatechnologies.diggredditclone.common.utility

import java.lang.StringBuilder
import java.security.SecureRandom

private const val ALPHANUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"

private val secureRandom = SecureRandom()

/*
 * Generates random alphanumeric strings. Used in creating ids for topics
 * Logic obtain from this post:
 * https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
 */
fun generateRandomAlphanumericString(length: Int) : String {
    val stringBuilder = StringBuilder()
    for (i in 1..length) {
        stringBuilder.append(ALPHANUMERIC[secureRandom.nextInt(ALPHANUMERIC.length)])
    }
    return stringBuilder.toString()
}