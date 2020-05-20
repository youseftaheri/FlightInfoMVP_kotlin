package com.yousef.mvpflightinfo.ui.schedules.interactor

import android.content.Context
import com.yousef.mvpflightinfo.data.AppDataManager
import com.yousef.mvpflightinfo.data.model.LuftSchedulesPOJO
import com.yousef.mvpflightinfo.data.network.ApiHelper
import com.yousef.mvpflightinfo.data.preferences.PreferencesHelper
import com.yousef.mvpflightinfo.ui.base.interactor.BaseInteractor
import io.reactivex.Single
import javax.inject.Inject

class SchedulesInteractor
@Inject
constructor(private val mContext: Context, private val appDataManager: AppDataManager,
            preferenceHelper: PreferencesHelper, apiHelper: ApiHelper) : BaseInteractor(
        preferenceHelper, apiHelper), SchedulesMVPInteractor {

    override fun getAccessToken(): String {
        return appDataManager.accessToken!!
    }

    override fun requestLuftSchedules(token: String?, date: String?): Single<LuftSchedulesPOJO?> {
        return appDataManager.requestLuftSchedules(token, date)
    }

    override fun getOrigin(): String {
        return appDataManager.origin!!
    }

    override fun getDestination(): String {
        return appDataManager.destination!!
    }
}