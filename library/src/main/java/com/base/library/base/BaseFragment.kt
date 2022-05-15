package com.base.library.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

/**
 * File BaseFragment
 * Created by yux on 2022/5/15 12:38
 * Description:
 * 指定 ViewModel 类型 [VM] & 指定 DataBinding 类型 [DB]
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseUiFragment() {

    /** DataBinding 对象*/
    protected lateinit var mBinding: DB


    /** 获取ViewModel */
    protected val mModel: VM by lazy {
        ViewModelProvider(this).get(onViewModel())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //初始化DataBinding
        mBinding = DataBindingUtil.inflate(inflater, onLayoutId(), container,false)
        //生命周期管理
        mBinding.lifecycleOwner = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mModel.lifecycleOwner = this
        mModel.storeOwner = this

        onLogic(view, savedInstanceState)
    }


    /** 实现[VM] javaClass */
    protected abstract fun onViewModel(): Class<VM>

    /** ui layout id */
    protected abstract fun onLayoutId(): Int

    /** 业务逻辑 */
    protected abstract fun onLogic(view: View, savedInstanceState: Bundle?)
}