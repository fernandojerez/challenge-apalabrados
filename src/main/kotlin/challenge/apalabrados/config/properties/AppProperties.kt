package challenge.apalabrados.config.properties

import challenge.apalabrados.config.Properties

class AppProperties : Properties {
    override fun jdbcUrl(): String = "jdbc:sqlite:apalabrados.db"
    override fun serverPort(): Int = System.getenv()["PORT"]?.toIntOrNull() ?: 8080
}
