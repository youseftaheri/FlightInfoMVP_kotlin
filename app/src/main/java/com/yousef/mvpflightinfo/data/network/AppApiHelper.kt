package com.yousef.mvpflightinfo.data.network

import android.content.Context
import com.yousef.mvpflightinfo.data.model.AirportLatLongPOJO
import com.yousef.mvpflightinfo.data.model.AirportsPOJO
import com.yousef.mvpflightinfo.data.model.LuftSchedulesPOJO
import com.yousef.mvpflightinfo.data.model.TokenPOJO
import com.yousef.mvpflightinfo.data.preferences.PreferencesHelper
import com.yousef.mvpflightinfo.utils.LocalUtils
import io.reactivex.Single
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppApiHelper @Inject constructor(private val apis: APIInterface,
                                       private val prefrenceHepler: PreferencesHelper,
                                       private val mContext: Context) : ApiHelper {
    override fun requestAccessTokenPost(): Single<TokenPOJO?> {
        val map = HashMap<String?, String?>()
        map["client_id"] = LocalUtils.client_id
        map["client_secret"] = LocalUtils.client_secret
        map["grant_type"] = "client_credentials"
        return apis.requestAccessTokenPost(map)
    }

    override fun requestLuftSchedules(token: String?, date: String?): Single<LuftSchedulesPOJO?>{
        return apis.requestLuftSchedules(token, prefrenceHepler.origin, prefrenceHepler.destination, date)
    }

    override fun requestAirports(token: String?): Single<AirportsPOJO?>{
        return apis.requestAirports(token)
    }

    override fun requestLatLong(token: String?, airport: String?): Single<AirportLatLongPOJO?>{
        return apis.requestLatLong(token, airport)
    }
}