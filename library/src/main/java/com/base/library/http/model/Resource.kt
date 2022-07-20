package com.base.library.http.model

/**
 * File Resource
 * Created on 2022/5/15 12:58
 * Description:
 *
 * 设置数据源
 */
data class Resource<out T>(
    val status: Status,
    val code: Int,
    val message: String,
    val data: T? = null
) {

    companion object {
        fun <T> success(data: T?, msg: String): Resource<T> {
            return Resource(Status.SUCCESS, Code.success, msg, data)
        }

        fun <T> fail(msg: String, data: T? = null): Resource<T> {
            return Resource(Status.FAIL, Code.fail, msg)
        }
    }
}
