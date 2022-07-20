package com.example.ktmvvm.https.model


/**
 * File Resource
 * Created
 * Description:
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