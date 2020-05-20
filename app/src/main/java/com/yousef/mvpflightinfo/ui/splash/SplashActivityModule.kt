package com.yousef.mvpflightinfo.ui.splash

import com.yousef.mvpflightinfo.ui.splash.interactor.SplashInteractor
import com.yousef.mvpflightinfo.ui.splash.interactor.SplashMVPInteractor
import com.yousef.mvpflightinfo.ui.splash.presenter.SplashMVPPresenter
import com.yousef.mvpflightinfo.ui.splash.presenter.SplashPresenter
import com.yousef.mvpflightinfo.ui.splash.view.SplashMVPView
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

    @Provides
    internal fun provideSplashInteractor(splashInteractor: SplashInteractor): SplashMVPInteractor = splashInteractor

    @Provides
    internal fun provideSplashPresenter(splashPresenter: SplashPresenter<SplashMVPView, SplashMVPInteractor>)
            : SplashMVPPresenter<SplashMVPView, SplashMVPInteractor> = splashPresenter
}