package com.example.ktmvvm

import android.os.Bundle
import android.view.View
import com.example.ktmvvm.base.BaseFragment
import com.example.ktmvvm.base.BaseViewModel
import com.example.ktmvvm.databinding.ActivityMainBinding

/**
 * File HomeFragment
 * Created by on 2022/4/5 17:05
 * Description:
 */
class HomeFragment: BaseFragment<BaseViewModel, ActivityMainBinding>() {
    override fun onViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun onLayoutId(): Int =R.layout.activity_main

    override fun onLogic(view: View, savedInstanceState: Bundle?) {
    }
}