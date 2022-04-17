package com.example.ktmvvm.https.interceptor
//import com.blankj.utilcode.util.GsonUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * File ResponseInterceptor
 * Created by yux
 * Description: Cookie拦截
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
//            RoKV.putString("Set-Cookie", GsonUtils.toJson(cookies))//本地持久化，过期问题此处没处理
        }
        return response
    }
}