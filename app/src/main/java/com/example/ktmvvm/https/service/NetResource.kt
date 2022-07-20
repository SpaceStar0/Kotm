package com.example.ktmvvm.https.service
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.ktmvvm.https.handle.ExceptionHandle
import com.example.ktmvvm.https.model.BaseModel
import com.example.ktmvvm.https.model.Code
import com.example.ktmvvm.https.model.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

/**
 * File NetResource
 * Created
 * Description:
 */
abstract class NetResource<T> {

    open fun liveData(): LiveData<Resource<T>>{
        return liveData(Dispatchers.IO){
            val api = HttpClient.createService(IService::class.java)
            try {
                val response = requestResource(api)
                if (response.isSuccessful){
                    val body = response.body()
                    val data = body?.data
                    if (body?.errorCode == Code.success){
                        emit(Resource.success(data,body.errorMsg))
                    }else{
                        emit(Resource.fail(body?.errorMsg!!,null))
                    }
                }else{
//                    val errorBody= response.errorBody()?.string()
                    val code =response.code()
                    val msg=response.message()
                    emit(Resource.fail(ExceptionHandle.handleException(code,msg),null))
                }
            }catch (e: Exception){
                emit(Resource.fail(ExceptionHandle.handleException(e),null))
            }
        }
    }

    abstract suspend fun requestResource(api: IService): Response<BaseModel<T>>
}