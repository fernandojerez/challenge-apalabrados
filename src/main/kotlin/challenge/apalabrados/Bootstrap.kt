package challenge.apalabrados

import challenge.apalabrados.config.DaggerApplication

fun main(){
    val app = DaggerApplication.create()
    val server = app.getServer()
    server.start()
}
