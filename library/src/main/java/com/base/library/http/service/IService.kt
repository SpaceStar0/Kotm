package com.base.library.http.service

import com.base.library.http.model.BaseModel
import retrofit2.Response
import retrofit2.http.GET

/**
 * File IService
 * Created on 2022/5/15 13:00
 * Description:
 *
 * 接口请求
 */
interface IService {

    @GET("wxarticle/chapters/json")
    suspend fun getArticle(): Response<BaseModel<Any>>
}