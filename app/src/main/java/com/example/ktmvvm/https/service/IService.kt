package com.example.ktmvvm.https.service

import com.example.ktmvvm.https.model.BaseModel
import retrofit2.Response
import retrofit2.http.GET

/**
 * File IService
 * Created
 * Description: 请求接口
 */
interface IService {


    @GET("wxarticle/chapters/json")
    suspend fun getArticleList(): Response<BaseModel<Any>>

}