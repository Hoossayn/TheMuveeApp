package com.example.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.data.api.API_KEY
import com.example.data.api.BASE_URL
import com.example.data.api.MovieApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    companion object {
        private const val CLIENT_TIME_OUT = 120L
    }

    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        chuckInterceptor: ChuckerInterceptor,
        @Named("authInterceptor") authInterceptor: Interceptor
    ): OkHttpClient {
        val okHttpBuilder = RetrofitUrlManager.getInstance().with(OkHttpClient.Builder())
        okHttpBuilder
            .addInterceptor(chuckInterceptor)
            .addInterceptor(authInterceptor)
            .connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        return okHttpBuilder.build()
    }

    @Provides
    @Named("authInterceptor")
    @Singleton
    fun providesAuthInterceptor() : Interceptor {
        return Interceptor {
            var request = it.request()
            val url = request.url().newBuilder().addQueryParameter("api_key", API_KEY).build()
            request = request.newBuilder().url(url).build()
            it.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideMoviesApi(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): MovieApi {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
        return retrofitBuilder.build().create(MovieApi::class.java)
    }

    @Singleton
    @Provides
    fun provideChuckInterceptor(@ApplicationContext context: Context) =
        ChuckerInterceptor(context = context)
}
