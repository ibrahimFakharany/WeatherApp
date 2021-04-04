package com.example.weatherapp.base.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.base.presenter.MvpPresenter
import javax.inject.Inject

abstract class MvpActivity<in V : MvpView, P : MvpPresenter<V>> : AppCompatActivity(), MvpView {
    @Inject
    open lateinit var presenter: P

    protected abstract fun inject()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()
        presenter.onAttach(view = this as V)
    }

    public override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    public override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

}