package com.example.ktmvvm.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner

/**
 * File BaseViewModel
 * Created by yux on 2022/4/5 15:10
 * Description:
 * MVVM ViewModel 基类
 */
abstract class BaseViewModel: ViewModel() {

    /**
     * 需要初始化
     */
    lateinit var lifecycleOwner: LifecycleOwner
    lateinit var storeOwner: ViewModelStoreOwner
}