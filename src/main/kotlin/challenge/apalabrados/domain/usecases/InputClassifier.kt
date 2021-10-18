package challenge.apalabrados.domain.usecases

import challenge.apalabrados.domain.models.Characters
import challenge.apalabrados.domain.models.Classification
import challenge.apalabrados.domain.models.Numbers
import challenge.apalabrados.domain.models.Text
import challenge.apalabrados.domain.repositories.ClassifierRepository
import java.math.BigInteger
import javax.inject.Inject


class InputClassifier @Inject constructor(private val classifierRepository: ClassifierRepository) {

    fun apply(text: String): Classification {
        val number = text.toBigIntegerOrNull()
        if (number != null) {
            return classifierRepository.insertNumbers(Classification(numbers = Numbers(number = number, sum = BigInteger.ZERO)))
        }
        val noCharacter =
            text.filter { !(it.isDigit() || it.isUpperCase() || it.isLowerCase() || it == ' ') }
        if (noCharacter.isEmpty()) {
            return classifierRepository.insertText(Classification(
                text = Text(
                    text = text,
                    start = text[0].toString(),
                    end = text[text.length - 1].toString()
                )
            ))
        }
        return classifierRepository.insertCharacters(Classification(characters = Characters(character = noCharacter)))
    }

}
