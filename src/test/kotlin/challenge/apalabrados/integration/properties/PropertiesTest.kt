package challenge.apalabrados.integration.properties

import challenge.apalabrados.config.Properties
import dagger.Module

class PropertiesTest : Properties  {
    override fun jdbcUrl(): String = "jdbc:sqlite::memory:"
    override fun serverPort(): Int = 8080
}
