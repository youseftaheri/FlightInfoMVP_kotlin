package com.yousef.mvpflightinfo.ui.schedules.interactor

import com.yousef.mvpflightinfo.data.model.LuftSchedulesPOJO
import com.yousef.mvpflightinfo.ui.base.interactor.MVPInteractor
import io.reactivex.Single

interface SchedulesMVPInteractor: MVPInteractor {
    fun getAccessToken(): String
    fun requestLuftSchedules(token: String?, date: String?): Single<LuftSchedulesPOJO?>
    fun getOrigin(): String
    fun getDestination(): String
}
