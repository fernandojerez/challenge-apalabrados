package challenge.apalabrados.integration

import java.math.BigInteger
import kotlin.test.Test
import kotlin.test.assertEquals

class ClassifierRestTest {

    @Test
    fun testNumbers(){
        val service = DaggerTestApplication.create().classifierRest()
        val firstResult = service.classify("123456")
        assertEquals(
            firstResult.numbers!!.number,
            BigInteger.valueOf(123_456))
        assertEquals(
            firstResult.numbers!!.sum,
            BigInteger.valueOf(123_456))
        val secondResult = service.classify("100000")
        assertEquals(
            secondResult.numbers!!.number,
            BigInteger.valueOf(100_000))
        assertEquals(
            secondResult.numbers!!.sum,
            BigInteger.valueOf(223_456))
    }

    @Test
    fun testCharacters() {
        val app = DaggerTestApplication.create()
        val service = app.classifierRest()
        service.classify("Hello#12;Hola%")
        val store = app.store()
        store.open().use {
            assertEquals(it.createQuery("SELECT caracter FROM caracteres")
                .mapToMap()
                .list(),
            listOf(mapOf("caracter" to "#"),
                mapOf("caracter" to ";"),
                mapOf("caracter" to "%")))
        }
    }

    @Test
    fun testText(){
        val app = DaggerTestApplication.create()
        val service = app.classifierRest()
        service.classify("HelloWorld")
        val store = app.store()
        store.open().use {
            assertEquals(it.createQuery("SELECT texto, inicial, final  FROM texto")
                .mapToMap()
                .list(),
                listOf(mapOf("texto" to "HelloWorld", "inicial" to "H", "final" to "d")))
        }
    }

}
