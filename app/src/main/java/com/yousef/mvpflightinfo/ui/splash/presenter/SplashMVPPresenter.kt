package com.yousef.mvpflightinfo.ui.splash.presenter

import com.yousef.mvpflightinfo.ui.base.presenter.MVPPresenter
import com.yousef.mvpflightinfo.ui.splash.interactor.SplashMVPInteractor
import com.yousef.mvpflightinfo.ui.splash.view.SplashMVPView

interface SplashMVPPresenter<V : SplashMVPView, I : SplashMVPInteractor> : MVPPresenter<V, I>{
//    fun requestAccessTokenPost()
}