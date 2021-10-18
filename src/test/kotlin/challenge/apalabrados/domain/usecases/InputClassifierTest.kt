package challenge.apalabrados.domain.usecases

import challenge.apalabrados.domain.models.Characters
import challenge.apalabrados.domain.models.Classification
import challenge.apalabrados.domain.models.Numbers
import challenge.apalabrados.domain.models.Text
import challenge.apalabrados.domain.repositories.ClassifierRepository
import java.math.BigInteger
import kotlin.test.Test
import kotlin.test.assertEquals

class InputClassifierTest {

    private fun createRepositoryMock(): ClassifierRepository {
        return object : ClassifierRepository {
            override fun insertNumbers(classification: Classification): Classification = classification
            override fun insertCharacters(classification: Classification): Classification = classification
            override fun insertText(classification: Classification): Classification = classification
        }
    }

    @Test
    fun testNumbers(){
        val classifier = InputClassifier(createRepositoryMock())
        assertEquals(
            classifier.apply("1234"),
            Classification(numbers= Numbers(number="1234".toBigInteger(), sum= BigInteger.ZERO))
        )
    }

    @Test
    fun testText(){
        val classifier = InputClassifier(createRepositoryMock())
        assertEquals(
            classifier.apply("HelloWorld"),
            Classification(text=Text(text="HelloWorld", start="H", end="d"))
        )
    }

    @Test
    fun testCharacter(){
        val classifier = InputClassifier(createRepositoryMock())
        assertEquals(
            classifier.apply("Hello#1"),
            Classification(characters=Characters(character="#"))
        )
        assertEquals(
            classifier.apply("Hello#1;23$%"),
            Classification(characters=Characters(character="#;$%"))
        )
    }
}
