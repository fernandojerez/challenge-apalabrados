package challenge.apalabrados.config

import challenge.apalabrados.rest.ClassifierRest
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.linecorp.armeria.common.HttpMethod
import com.linecorp.armeria.common.JacksonObjectMapperProvider
import com.linecorp.armeria.server.Server
import com.linecorp.armeria.server.annotation.JacksonResponseConverterFunction
import com.linecorp.armeria.server.cors.CorsService
import com.linecorp.armeria.server.docs.DocService
import javax.inject.Inject

class Server @Inject constructor(
    private var properties: Properties,
    private var classifier: ClassifierRest
    ) {

    fun start() {
        val cors = CorsService.builderForAnyOrigin()
            .allowRequestMethods(HttpMethod.GET, HttpMethod.OPTIONS, HttpMethod.POST)
            .newDecorator();

        val objectMapper = ObjectMapper()
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)

        Server.builder().http(properties.serverPort())
            .annotatedService(
                classifier,
                cors,
                JacksonResponseConverterFunction(objectMapper))
            .serviceUnder("/docs", DocService())
            .build()
            .start()
            .join()
    }

}
