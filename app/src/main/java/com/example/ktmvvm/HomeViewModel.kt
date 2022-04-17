package com.example.ktmvvm

import androidx.lifecycle.MutableLiveData
import com.example.ktmvvm.base.BaseViewModel
import com.example.ktmvvm.https.model.BaseModel
import com.example.ktmvvm.https.model.Status
import com.example.ktmvvm.https.service.IService
import com.example.ktmvvm.https.service.NetResource
import retrofit2.Response

/**
 * File HomeViewModel
 * Created by yux on 2022/4/17 11:01
 * Description:
 */
class HomeViewModel: BaseViewModel() {

    val txt: MutableLiveData<String> = MutableLiveData("hello world")

    fun changeClick(){
        getList()
    }

    private fun getArticleList() = object : NetResource<Any>(){
        override suspend fun requestResource(api: IService): Response<BaseModel<Any>> {
            return api.getArticleList()
        }
    }.liveData()

    fun getList(){
        getArticleList().observe(lifecycleOwner){
            if (it.status == Status.SUCCESS){
                txt.value="改变值"
            }
        }
    }
}