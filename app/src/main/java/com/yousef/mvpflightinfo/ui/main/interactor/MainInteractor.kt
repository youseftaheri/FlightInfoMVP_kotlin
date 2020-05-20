package com.yousef.mvpflightinfo.ui.main.interactor

import android.content.Context
import com.yousef.mvpflightinfo.data.AppDataManager
import com.yousef.mvpflightinfo.data.model.AirportsPOJO
import com.yousef.mvpflightinfo.data.model.TokenPOJO
import com.yousef.mvpflightinfo.data.network.ApiHelper
import com.yousef.mvpflightinfo.data.preferences.PreferencesHelper
import com.yousef.mvpflightinfo.ui.base.interactor.BaseInteractor
import io.reactivex.Single
import javax.inject.Inject

class MainInteractor
@Inject
constructor(private val mContext: Context, private val appDataManager: AppDataManager,
            preferenceHelper: PreferencesHelper, apiHelper: ApiHelper) : BaseInteractor(
        preferenceHelper, apiHelper), MainMVPInteractor {

    override fun getAccessToken(): String {
        return appDataManager.accessToken!!
    }

    override fun requestAirports(token: String?): Single<AirportsPOJO?> {
        return appDataManager.requestAirports(token)
    }

    override fun setOrigin(origin: String) {
        appDataManager.origin = origin
    }

    override fun setDestination(destination: String) {
        appDataManager.destination = destination
    }

}