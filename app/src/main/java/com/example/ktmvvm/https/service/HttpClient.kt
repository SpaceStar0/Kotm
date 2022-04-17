package com.example.ktmvvm.https.service
import GsonDefaultAdapterFactory
import android.util.Log
import com.example.ktmvvm.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * File HttpClient
 * Created by yux
 * Description: retrofit配置
 */
object HttpClient {

    private const val URL = "https://www.wanandroid.com/"

    private val okHttpClient: OkHttpClient by lazy { OkHttpClient() }

    private val retrofit: Retrofit by lazy {
        val builder = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder()
                    .registerTypeAdapterFactory(GsonDefaultAdapterFactory())
                    .create()
            ))

        //日志打印
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                if (BuildConfig.DEBUG){
                    val tag = "HttpTAG"
                    var msg = message
                    val maxLength: Int = 2001 - tag.length
                    //大于4000时
                    while (msg.length > maxLength) {
                        Log.d(tag, msg.substring(0, maxLength))
                        msg = msg.substring(maxLength)
                    }
                    //剩余部分
                    Log.d(tag, msg)
                }
            }
        })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client: OkHttpClient = okHttpClient.newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
//            .addInterceptor(com.example.ktmvvm.https.interceptor.ResponseInterceptor())  //拦截cookie
//            .addInterceptor(ParamsIpt()) //添加请求头
            .addInterceptor(loggingInterceptor)
            .build()
        builder.client(client).build()
    }

    fun <T> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}