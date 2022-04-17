package com.example.ktmvvm

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * File A
 * Created by yux on 2022/4/5 17:20
 * Description:
 */
class A: LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
    }
}