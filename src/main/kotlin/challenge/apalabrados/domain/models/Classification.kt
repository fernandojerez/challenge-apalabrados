package challenge.apalabrados.domain.models

import com.linecorp.armeria.server.annotation.Description

/**
 * Result of classification can be: numbers (only numbers), characters (special characters) or text (alphanumeric text excluding numbers)
 */
data class Classification (
    val numbers: Numbers? = null,
    val characters: Characters? = null,
    val text: Text? = null
)
