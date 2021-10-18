package challenge.apalabrados.config.modules

import challenge.apalabrados.config.Properties
import challenge.apalabrados.domain.repositories.ClassifierRepository
import challenge.apalabrados.repositories.JdbiClassifierRepository
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dagger.Module
import dagger.Provides
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.argument.AbstractArgumentFactory
import org.jdbi.v3.core.argument.Argument
import org.jdbi.v3.core.config.ConfigRegistry
import org.jdbi.v3.sqlobject.SqlObjectPlugin
import java.math.BigInteger
import java.sql.Types
import javax.inject.Singleton

@Module
object DataStoreModule {

    @Singleton
    @Provides
    fun provideClassifierRepository(jdbiClassifierRepository: JdbiClassifierRepository): ClassifierRepository
            = jdbiClassifierRepository

    @Singleton
    @Provides
    fun provideStore(properties: Properties): Jdbi {
        val config = HikariConfig().apply {
            this.jdbcUrl = properties.jdbcUrl()
        }
        val dataSource = HikariDataSource(config)
        val jdbi = Jdbi.create(dataSource)
        jdbi.installPlugin(SqlObjectPlugin())
        jdbi.registerColumnMapper(BigInteger::class.java) { r, columnNumber, _ -> r!!.getBigDecimal(columnNumber).toBigInteger() }
        jdbi.registerArgument(object : AbstractArgumentFactory<BigInteger>(Types.BIGINT){
            override fun build(value: BigInteger?, config: ConfigRegistry?): Argument =
                Argument { position, statement, _ -> statement.setBigDecimal(position, value?.toBigDecimal()) }
        })
        jdbi.open().use {
            it.execute("""
                CREATE TABLE IF NOT EXISTS caracteres (
                    caracter text not null
                )""".trimIndent())
            it.execute("""
                CREATE TABLE IF NOT EXISTS texto (
                    texto text not null,
                    inicial text not null,
                    final text not null
                )""".trimIndent())
            it.execute("""
                CREATE TABLE IF NOT EXISTS numeros (
                    numero BIGINT not null,
                    acumulado BIGINT not null
                )
            """.trimIndent())
            it.execute("""                
                CREATE INDEX IF NOT EXISTS numeros_acumulado ON numeros(acumulado)
            """.trimIndent())
        }
        return jdbi
    }

}
