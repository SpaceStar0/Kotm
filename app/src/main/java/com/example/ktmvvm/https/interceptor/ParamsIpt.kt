package com.example.ktmvvm.https.interceptor

//import com.blankj.utilcode.util.GsonUtils
import com.google.gson.reflect.TypeToken
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * File ParamsIpt
 * Created
 * Description: 添加请求头
 */
class ParamsIpt : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var request: Request? = chain.request()
        val builder = request!!.newBuilder()

        val stringBuilder: StringBuilder = StringBuilder()
        val type = object : TypeToken<ArrayList<String>>() {}.type
        //取出上一步中存储的Cookie  添加
//        GsonUtils.fromJson<List<String>>(RoKV.getString("Set-Cookie"), type)?.run {
//            forEach {
//                stringBuilder.append(it).append(";")
//            }
//            stringBuilder.replace(stringBuilder.length, stringBuilder.length + 1, "")//替换掉最后一个";"
//            builder.header("Cookie", stringBuilder.toString())
//        }
        request = builder.build()
        return chain.proceed(request)
    }
}