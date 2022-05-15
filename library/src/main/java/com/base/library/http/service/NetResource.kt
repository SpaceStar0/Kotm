package com.base.library.http.service

import androidx.lifecycle.LiveData
import com.base.library.http.handle.ExceptionHandle
import com.base.library.http.model.BaseModel
import com.base.library.http.model.Code
import com.base.library.http.model.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

/**
 * File NetResource
 * Created by yux on 2022/5/15 13:05
 * Description:
 *
 * 需要实现的请求结果处理
 */
abstract class NetResource<T, S> {

    open fun liveData(): LiveData<Resource<T>> {
        return androidx.lifecycle.liveData(Dispatchers.IO) {
            val api = HttpClient.createService(onService(),onBaseUrl())
            try {
                val response = requestResource(api)
                if (response.isSuccessful) {
                    val body = response.body()
                    val data = body?.data
                    //与服务器返回成功对应数据
                    if (body?.errorCode == Code.success) {
                        emit(Resource.success(data, body.errorMsg))
                    } else {
                        //code返回错误
                        emit(Resource.fail(body?.errorMsg!!, null))
                    }
                } else {
                    //直接请求失败
//                    val errorBody= response.errorBody()?.string()
                    val code = response.code()
                    val msg = response.message()
                    emit(Resource.fail(ExceptionHandle.handleException(code, msg), null))
                }
            } catch (e: Exception) {
                //解析、其他相关配置异常
                emit(Resource.fail(ExceptionHandle.handleException(e), null))
            }
        }
    }

    //url
    open fun onBaseUrl(): String? = ""

    abstract fun onService(): Class<S>

    abstract suspend fun requestResource(api: S): Response<BaseModel<T>>
}