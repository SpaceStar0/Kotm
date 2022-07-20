package com.base.library.http.interceptor

import com.google.gson.reflect.TypeToken
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * File ParamsIpt
 * Created on 2022/5/15 12:53
 * Description:
 *
 * 添加请求头
 */
class ParamsIpt : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var request: Request? = chain.request()
        val builder = request!!.newBuilder()

        val stringBuilder: StringBuilder = StringBuilder()
        val type = object : TypeToken<ArrayList<String>>() {}.type
        builder.header("test", "")
        request = builder.build()
        return chain.proceed(request)
    }
}