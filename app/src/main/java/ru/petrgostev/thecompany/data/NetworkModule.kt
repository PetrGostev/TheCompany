package ru.petrgostev.thecompany.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit

class NetworkModule {
    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addNetworkInterceptor(httpLoggingInterceptor)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .build()

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    private val contentType = "application/json".toMediaType()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()

    val companyApi: CompanyApi = retrofit.create()

    companion object {
        private const val BASE_URL = "https://lifehack.studio/test_task/"
        private const val READ_TIMEOUT: Long = 20
        private const val CONNECT_TIMEOUT: Long = 10

        private var instance: NetworkModule? = null

        fun getInstance(): NetworkModule {
            return instance ?: synchronized(this) {
                instance ?: NetworkModule().also {
                    instance = it
                }
            }
        }
    }
}