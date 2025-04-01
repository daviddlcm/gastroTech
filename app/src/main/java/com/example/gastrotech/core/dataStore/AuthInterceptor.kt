package com.example.gastrotech.core.dataStore

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenProvider: suspend () -> String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { tokenProvider() }
        val request = chain.request().newBuilder()

        if (!token.isNullOrEmpty()) {
            request.addHeader("token", token)
        }
        return chain.proceed(request.build())
    }
}