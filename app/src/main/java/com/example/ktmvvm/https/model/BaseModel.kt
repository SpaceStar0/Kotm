package com.example.ktmvvm.https.model

import java.io.Serializable

/**
 * File BaseModel
 * Created by yux
 * Description: base基
 */
open class BaseModel<T> : Serializable {

    open var data: T? = null
    open var errorCode: Int = 0
    open var errorMsg: String = ""
}