package challenge.apalabrados.integration

import challenge.apalabrados.config.modules.DataStoreModule
import challenge.apalabrados.integration.modules.ConfigTestModule
import challenge.apalabrados.rest.ClassifierRest
import dagger.Component
import org.jdbi.v3.core.Jdbi
import javax.inject.Singleton

@Singleton
@Component(modules = [DataStoreModule::class, ConfigTestModule::class])
interface TestApplication {
    fun classifierRest(): ClassifierRest
    fun store(): Jdbi
}
