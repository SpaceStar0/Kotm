package com.example.ktmvvm

import android.os.Bundle
import com.example.ktmvvm.base.BaseActivity
import com.example.ktmvvm.base.BaseViewModel
import com.example.ktmvvm.databinding.ActivityMainBinding

class MainActivity : BaseActivity<HomeViewModel, ActivityMainBinding>() {

    override fun onViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun onLayoutId(): Int =R.layout.activity_main

    override fun onLogic(savedInstanceState: Bundle?) {
        mBinding.viewModel = mModel
    }
}