package com.yousef.mvpflightinfo.ui.splash.interactor

import com.yousef.mvpflightinfo.data.model.TokenPOJO
import com.yousef.mvpflightinfo.ui.base.interactor.MVPInteractor
import io.reactivex.Single

interface SplashMVPInteractor: MVPInteractor {
    fun requestAccessTokenPost(): Single<TokenPOJO?>
    fun setAccessToken(accessToken: String)
}