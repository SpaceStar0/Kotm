package com.base.library.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner

/**
 * File BaseViewModel
 * Created on 2022/5/15 12:33
 * Description: viewModel基类
 */
abstract class BaseViewModel : ViewModel() {

    /**
     * 需要初始化
     */
    lateinit var lifecycleOwner: LifecycleOwner
    lateinit var storeOwner: ViewModelStoreOwner
}