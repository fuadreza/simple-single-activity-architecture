package io.github.fuadreza.simplesingleactivityarchitecture.utils

import androidx.annotation.LayoutRes

interface IBaseView {
    @LayoutRes
    fun getLayoutResource(): Int
    fun initViews(){}
    fun initObservers(){}

}