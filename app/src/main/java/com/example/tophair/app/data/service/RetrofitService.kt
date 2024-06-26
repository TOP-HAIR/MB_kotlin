package com.example.tophair.app.data.service

import com.example.tophair.app.data.api.ViaCepApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val BASE_URL_USER: String = "https://tophair.zapto.org/api/"
    val BASE_URL_VIA_CEP: String = "https://viacep.com.br/ws/"

    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }
    val okHttpClientToken: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor.interceptor())
            .build()
    }

    inline fun <reified T> getApiService(serviceClass: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_USER)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(serviceClass)
    }

    inline fun <reified T> getApiServiceWithToken(serviceClass: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_USER)
            .client(okHttpClientToken)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(serviceClass)
    }

    fun getViaCepApi(): ViaCepApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_VIA_CEP)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ViaCepApi::class.java)
    }
}
