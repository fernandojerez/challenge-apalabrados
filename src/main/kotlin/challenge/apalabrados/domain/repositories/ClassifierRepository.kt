package challenge.apalabrados.domain.repositories

import challenge.apalabrados.domain.models.Classification

interface ClassifierRepository {

    fun insertNumbers(classification: Classification): Classification

    fun insertCharacters(classification: Classification): Classification

    fun insertText(classification: Classification): Classification

}
