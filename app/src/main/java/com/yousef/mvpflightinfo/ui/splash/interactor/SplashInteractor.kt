package com.yousef.mvpflightinfo.ui.splash.interactor

import android.content.Context
import com.yousef.mvpflightinfo.data.AppDataManager
import com.yousef.mvpflightinfo.data.model.TokenPOJO
import com.yousef.mvpflightinfo.data.network.ApiHelper
import com.yousef.mvpflightinfo.data.preferences.PreferencesHelper
import com.yousef.mvpflightinfo.ui.base.interactor.BaseInteractor
import io.reactivex.Single
import javax.inject.Inject

class SplashInteractor
@Inject
constructor(private val mContext: Context, private val appDataManager: AppDataManager,
            preferenceHelper: PreferencesHelper, apiHelper: ApiHelper) : BaseInteractor(
        preferenceHelper, apiHelper), SplashMVPInteractor {

    override fun requestAccessTokenPost(): Single<TokenPOJO?> {
        return appDataManager.requestAccessTokenPost()
    }

    override fun setAccessToken(accessToken: String) {
        appDataManager.accessToken = accessToken
    }
}