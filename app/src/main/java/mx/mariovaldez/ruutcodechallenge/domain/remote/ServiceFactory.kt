package mx.mariovaldez.ruutcodechallenge.domain.remote

internal interface ServiceFactory {

    fun <T> createApiService(serviceClass: Class<T>): T
}
