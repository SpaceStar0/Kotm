package com.base.library.http.model

import java.io.Serializable

/**
 * File BaseModel
 * Created by yux on 2022/5/15 12:56
 * Description:
 *
 * model
 */
open class BaseModel<T> : Serializable {

    open var data: T? = null
    open var errorCode: Int = 0
    open var errorMsg: String = ""
}