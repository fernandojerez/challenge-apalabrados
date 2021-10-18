package challenge.apalabrados.repositories

import challenge.apalabrados.domain.models.Classification
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.customizer.BindMap
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import org.jdbi.v3.sqlobject.transaction.Transaction
import java.math.BigInteger
import java.util.*

interface ClassifierQueries {

    @SqlUpdate("INSERT INTO caracteres(caracter) VALUES (:character)")
    @Transaction
    fun insertCharacter(@Bind("character") character: String)

    @SqlUpdate("INSERT INTO numeros(numero,acumulado) VALUES (:classification.numbers.number, :classification.numbers.sum)")
    fun insertNumbers(@BindBean("classification") classification: Classification)

    @SqlUpdate("INSERT INTO texto(texto, inicial, final) VALUES (:classification.text.text, :classification.text.start, :classification.text.end)")
    @Transaction
    fun insertText(@BindBean("classification") classification: Classification)

    @SqlQuery("SELECT acumulado FROM numeros order by acumulado DESC LIMIT 1")
    fun getNumbersSum(): Optional<BigInteger>
}
