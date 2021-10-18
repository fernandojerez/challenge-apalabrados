package challenge.apalabrados.config

interface Properties {
    fun jdbcUrl(): String
    fun serverPort(): Int
}
