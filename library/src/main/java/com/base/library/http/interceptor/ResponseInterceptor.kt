package com.base.library.http.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * File ResponseInterceptor
 * Created by yux on 2022/5/15 12:54
 * Description:
 *
 * Cookie拦截
 */
class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)
        val cookies = mutableListOf<String>()
        response.headers("Set-Cookie").run {
            forEach {
                cookies.add(it)
            }
            //做本地存储cookie
        }
        return response
    }
}