package pt.iade.ei.cobuy.network.api.auth

import okhttp3.Interceptor
import okhttp3.Response
import pt.iade.ei.cobuy.storage.utils.TokenManager

class TokenInterceptor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenManager.getToken()

        val request = if (token != null) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }

        return chain.proceed(request)
    }
}