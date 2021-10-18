package challenge.apalabrados.integration.modules

import challenge.apalabrados.config.Properties
import challenge.apalabrados.integration.properties.PropertiesTest
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ConfigTestModule {

    @Singleton
    @Provides
    fun providesProperties(): Properties = PropertiesTest()

}
