package com.example.ktmvvm.https.interceptor

import com.example.ktmvvm.https.model.Code
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject

/**
 * File BodyInterceptor
 * Created by yux
 * Description: 处理返回结果，成功和失败类型不统一
 */
class BodyInterceptor : ResponseBodyInterceptor() {
    override fun intercept(response: Response, url: String, body: String): Response {
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(body)
            if (!jsonObject.getString("code").equals(Code.success)) {
                jsonObject.put("data", null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val contentType = response.body?.contentType()
        val responseBody = jsonObject.toString().toResponseBody(contentType)
        return response.newBuilder().body(responseBody).build() // 重新生成响应对象
    }
}