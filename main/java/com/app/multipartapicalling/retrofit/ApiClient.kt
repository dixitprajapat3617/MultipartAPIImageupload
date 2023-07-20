package com.app.androidlocalhostfirebase.model.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {


    private var retrofit: Retrofit? = null

    fun getInstance(accessToken: String): ApiService {
        if (retrofit == null) {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(TokenInterceptor(accessToken))

            retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.131.36/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }
        return retrofit!!.create(ApiService::class.java)
    }

    private class TokenInterceptor(private val accessToken: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val modifiedRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer $accessToken")
                .build()
            return chain.proceed(modifiedRequest)
        }
    }
}
