package com.yousef.mvpflightinfo.ui.main.interactor

import com.yousef.mvpflightinfo.data.model.AirportsPOJO
import com.yousef.mvpflightinfo.ui.base.interactor.MVPInteractor
import io.reactivex.Single

interface MainMVPInteractor: MVPInteractor {
    fun getAccessToken(): String
    fun requestAirports(token: String?): Single<AirportsPOJO?>
    fun setOrigin(origin: String)
    fun setDestination(destination: String)
}
