package pt.iade.ei.cobuy.network.api

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pt.iade.ei.cobuy.App
import pt.iade.ei.cobuy.network.api.auth.TokenInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private lateinit var appContext: Context

    fun initialize(context: Context) {
        appContext = context.applicationContext
    }


    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val backendClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(App.instance.tokenManager)) // Auth
            .addInterceptor(logging) // Logs
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private const val BACKEND_URL = "http://10.0.2.2:8082/"

    val backendRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BACKEND_URL)
            .client(backendClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val savedPlacesApi: SavedPlacesApi by lazy {
        backendRetrofit.create(SavedPlacesApi::class.java)
    }

    val supermarketApi: SupermarketApi by lazy {
        backendRetrofit.create(SupermarketApi::class.java)
    }


    private val googleClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(logging) // Logs apenas
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private const val GOOGLE_URL = "https://maps.googleapis.com/maps/api/"

    private val googleRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(GOOGLE_URL)
            .client(googleClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val googleApi: GoogleApi by lazy {
        googleRetrofit.create(GoogleApi::class.java)
    }
}