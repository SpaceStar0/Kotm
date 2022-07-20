package com.example.ktmvvm.https.handle

import android.net.ParseException
import com.google.gson.JsonParseException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

/**
 * File ExceptionHandle
 * Created
 * Description: 异常
 */
object ExceptionHandle {

    private const val UNAUTHORIZED = 401
    private const val FORBIDDEN = 403
    private const val NOT_FOUND = 404
    private const val REQUEST_TIMEOUT = 408
    private const val INTERNAL_SERVER_ERROR = 500
    private const val BAD_GATEWAY = 502
    private const val SERVICE_UNAVAILABLE = 503
    private const val GATEWAY_TIMEOUT = 504

    fun handleException(code: Int, msg: String): String {
        return when (code) {
            UNAUTHORIZED -> {
                "此请求未经授权认证！"
            }
            FORBIDDEN -> {
                "服务器拒绝此请求！"
            }
            NOT_FOUND -> {
                "服务器未找到此请求！"
            }
            REQUEST_TIMEOUT -> {
                "服务器请求超时！"
            }
            GATEWAY_TIMEOUT -> {
                "服务器网关超时！"
            }
            INTERNAL_SERVER_ERROR -> {
                "服务器遇到错误，无法完成请求！"
            }
            BAD_GATEWAY -> {
                "服务器网关错误！"
            }
            SERVICE_UNAVAILABLE -> {
                "服务器不可用！"
            }
            else -> {
                if (msg.isNotEmpty()) {
                    return msg
                }
                return "service未知错误！"
            }
        }
    }

    fun handleException(e: Exception): String {
        return when (e) {
            is JsonParseException -> "数据解析异常！"
            is JSONException -> "数据解析异常！"
            is ParseException -> "数据解析异常！"
            is ConnectException -> "网络连接异常！"
            is SSLHandshakeException -> "证书验证失败！"
            is ConnectTimeoutException -> "连接超时！"
            is SocketTimeoutException -> "连接超时！"
            is UnknownHostException -> "请检查网络连接！"
            else -> "未知错误！"
        }
    }
}