package com.example.mixta

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat.getString
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

const val space = "f8bqpb154z8p"
val token = BuildConfig.TOKEN

private class AuthorizationInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .apply {
                //TokenRepository.getToken()?.let { token ->
                    addHeader("Authorization", "Bearer $token")
                //}
            }
            .build()
        return chain.proceed(request)
    }
}

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://graphql.contentful.com/content/v1/spaces/$space")
    .okHttpClient(
        OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
    )
    .build()