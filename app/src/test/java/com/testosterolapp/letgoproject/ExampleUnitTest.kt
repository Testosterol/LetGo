package com.testosterolapp.letgoproject

import com.testosterolapp.letgoproject.serverCommunication.Url
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testIsUrlCorrect(){
        val url = Url.URL_DATA
        assertEquals(url, "https://android-hiring.web.app/superheroes.json")
    }
}