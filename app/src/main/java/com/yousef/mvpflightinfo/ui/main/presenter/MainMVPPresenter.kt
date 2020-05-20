package com.yousef.mvpflightinfo.ui.main.presenter

import com.yousef.mvpflightinfo.ui.base.presenter.MVPPresenter
import com.yousef.mvpflightinfo.ui.main.interactor.MainMVPInteractor
import com.yousef.mvpflightinfo.ui.main.view.MainMVPView

interface MainMVPPresenter <V : MainMVPView, I : MainMVPInteractor> : MVPPresenter<V, I> {
    fun setOrigin(origin: String)
    fun setDestination(destination: String)
}