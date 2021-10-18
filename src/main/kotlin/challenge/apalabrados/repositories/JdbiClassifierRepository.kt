package challenge.apalabrados.repositories

import challenge.apalabrados.domain.models.Classification
import challenge.apalabrados.domain.models.Numbers
import challenge.apalabrados.domain.repositories.ClassifierRepository
import org.jdbi.v3.core.Jdbi
import java.lang.Exception
import java.math.BigInteger
import javax.inject.Inject

class JdbiClassifierRepository
    @Inject constructor(private val jdbi: Jdbi) : ClassifierRepository{

    override fun insertNumbers(classification: Classification): Classification {
        jdbi.open().use { conn ->
            val queries = conn.attach(ClassifierQueries::class.java)
            return conn.inTransaction<Classification, Exception> {
                val accum = queries.getNumbersSum().orElse(BigInteger.ZERO)
                val newClassification = Classification(
                    numbers = Numbers(
                        number=classification.numbers!!.number,
                        sum=accum + classification.numbers.number)
                )
                queries.insertNumbers(newClassification)
                newClassification
            }
        }
    }

    override fun insertCharacters(classification: Classification): Classification {
        jdbi.open().use { conn ->
            val queries = conn.attach(ClassifierQueries::class.java)
            classification.characters!!.character.forEach {
                queries.insertCharacter(it.toString())
            }
            return classification
        }
    }

    override fun insertText(classification: Classification): Classification {
        jdbi.open().use { conn ->
            val queries = conn.attach(ClassifierQueries::class.java)
            queries.insertText(classification)
            return classification
        }
    }


}
