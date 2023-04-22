package com.example.myapplication

import com.example.myapplication.data.remote.CatsApi
import com.example.myapplication.domain.CatsRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CatsModule {
    fun provideCatsRepository(api: CatsApi = provideCatsApi()): CatsRepository {
        return DefaultCatsRepository(api)
    }

    private fun provideCatsApi(retrofit: Retrofit = provideRetrofit()): CatsApi {
        return retrofit.create(CatsApi::class.java)
    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                addNetworkInterceptor(
                    Interceptor { chain ->
                        val headers = chain.request().headers.newBuilder()
                            .add("x-api-key", "bda53789-d59e-46cd-9bc4-2936630fde39")
                            .build()
                        val request = chain.request().newBuilder().headers(headers).build()
                        chain.proceed(request)
                    }
                )
            }.build())
            .build()
    }

}
