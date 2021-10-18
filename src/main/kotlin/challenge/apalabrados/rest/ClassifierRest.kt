package challenge.apalabrados.rest

import challenge.apalabrados.domain.models.Classification
import challenge.apalabrados.domain.usecases.InputClassifier
import com.linecorp.armeria.server.annotation.*
import javax.inject.Inject

@Description("Classification service")
class ClassifierRest @Inject constructor(
    private val inputClassifier: InputClassifier) {

    @Get("/classify")
    @ProducesJson
    @Blocking
    @Description("Take a test an classify it into three categories: numbers, text or characters")
    fun classify(@Param("text") @Description("The text to classify") text:String): Classification {
        return inputClassifier.apply(text)
    }

}
