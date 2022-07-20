package com.example.ktmvvm.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider


/**
 * File BaseActivity
 * Created on 2022/4/5 14:33
 * Description:
 * Activity 基类
 * 指定 ViewModel 类型 [VM] & 指定 DataBinding 类型 [DB]
 */
abstract class BaseActivity<VM: BaseViewModel, DB: ViewDataBinding>: BaseUIActivity() {


    /** DataBinding 对象*/
    protected lateinit var mBinding: DB


    /** 获取ViewModel */
    protected val mModel: VM by lazy {
        ViewModelProvider(this).get(onViewModel())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化DataBinding
        mBinding = DataBindingUtil.setContentView(this,onLayoutId())
        //生命周期管理
        mBinding.lifecycleOwner = this
        //绑定ViewModel
//        mBinding.setVariable(BR.mModel,mModel)

        mModel.lifecycleOwner = this
        mModel.storeOwner = this

        onLogic(savedInstanceState)
    }

    /** 实现[VM] javaClass */
    protected abstract fun onViewModel(): Class<VM>

    /** ui layout id */
    protected abstract fun onLayoutId(): Int

    /** 业务逻辑 */
    protected abstract fun onLogic(savedInstanceState: Bundle?)


}