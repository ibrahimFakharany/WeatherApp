package com.example.weatherapp.base.presenter

import com.example.weatherapp.base.view.MvpView

interface MvpPresenter<in V : MvpView> {
    fun onAttach(view: V)

    fun onResume()

    fun onDetach()
}