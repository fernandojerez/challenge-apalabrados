package challenge.apalabrados.config.modules;

import challenge.apalabrados.config.Properties
import challenge.apalabrados.config.properties.AppProperties
import dagger.Module;
import dagger.Provides
import javax.inject.Singleton

@Module
object ConfigModule {

    @Singleton
    @Provides
    fun provideProperties() : Properties = AppProperties()

}
