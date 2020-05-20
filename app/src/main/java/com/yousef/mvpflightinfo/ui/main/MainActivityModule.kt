package com.yousef.mvpflightinfo.ui.main

import com.yousef.mvpflightinfo.ui.main.interactor.MainInteractor
import com.yousef.mvpflightinfo.ui.main.interactor.MainMVPInteractor
import com.yousef.mvpflightinfo.ui.main.presenter.MainMVPPresenter
import com.yousef.mvpflightinfo.ui.main.presenter.MainPresenter
import com.yousef.mvpflightinfo.ui.main.view.MainMVPView
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainInteractor(mainInteractor: MainInteractor): MainMVPInteractor = mainInteractor

    @Provides
    internal fun provideMainPresenter(mainPresenter: MainPresenter<MainMVPView, MainMVPInteractor>)
            : MainMVPPresenter<MainMVPView, MainMVPInteractor> = mainPresenter
}