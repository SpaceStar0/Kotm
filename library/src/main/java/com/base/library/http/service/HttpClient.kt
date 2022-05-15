package com.base.library.http.service

import android.util.Log
import com.base.library.http.factory.GsonDefaultAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * File HttpClient
 * Created by yux on 2022/5/15 13:02
 * Description:
 *
 * retrofit
 */
object HttpClient {

    private const val URL = "https://www.wanandroid.com/"

    private val okHttpClient: OkHttpClient by lazy { OkHttpClient() }

    private fun retrofit(baseUrl: String? = ""): Retrofit{
        val retrofits: Retrofit by lazy{
            val builder = Retrofit.Builder()
                .baseUrl(if (baseUrl != null && baseUrl != "") baseUrl else URL)
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder()
                            .registerTypeAdapterFactory(GsonDefaultAdapterFactory())
                            .create()
                    ))

            //日志打印
            val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
//                if (BuildConfig.DEBUG){
                    val tag = "RetrofitTAG"
                    var msg = message
                    val maxLength: Int = 2001 - tag.length
                    //大于4000时
                    while (msg.length > maxLength) {
                        Log.d(tag, msg.substring(0, maxLength))
                        msg = msg.substring(maxLength)
                    }
                    //剩余部分
                    Log.d(tag, msg)
//                }
                }
            })
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client: OkHttpClient = okHttpClient.newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build()
            builder.client(client).build()
        }
        return retrofits
    }

    fun <T> createService(clazz: Class<T>, baseUrl: String? = null): T {
        return retrofit(baseUrl).create(clazz)
    }
}