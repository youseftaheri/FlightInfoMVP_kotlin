package com.yousef.mvpflightinfo.data.network

import com.yousef.mvpflightinfo.data.model.AirportLatLongPOJO
import com.yousef.mvpflightinfo.data.model.AirportsPOJO
import com.yousef.mvpflightinfo.data.model.LuftSchedulesPOJO
import com.yousef.mvpflightinfo.data.model.TokenPOJO
import io.reactivex.Single

interface ApiHelper {
    fun requestAccessTokenPost(): Single<TokenPOJO?>
    fun requestLuftSchedules(token: String?, date: String?): Single<LuftSchedulesPOJO?>
    fun requestAirports(token: String?): Single<AirportsPOJO?>
    fun requestLatLong(token: String?, airport: String?): Single<AirportLatLongPOJO?>
}