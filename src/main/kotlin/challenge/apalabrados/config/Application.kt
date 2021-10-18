package challenge.apalabrados.config

import challenge.apalabrados.config.modules.ConfigModule
import challenge.apalabrados.config.modules.DataStoreModule
import challenge.apalabrados.config.properties.AppProperties
import dagger.Component
import dagger.Provides
import javax.inject.Singleton

@Component(modules = [DataStoreModule::class, ConfigModule::class])
@Singleton
interface Application {
    fun getServer(): Server;
}
