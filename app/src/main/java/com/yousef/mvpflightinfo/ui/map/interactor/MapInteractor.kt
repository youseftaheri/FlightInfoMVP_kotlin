package com.yousef.mvpflightinfo.ui.map.interactor

import android.content.Context
import com.yousef.mvpflightinfo.data.AppDataManager
import com.yousef.mvpflightinfo.data.model.AirportLatLongPOJO
import com.yousef.mvpflightinfo.data.network.ApiHelper
import com.yousef.mvpflightinfo.data.preferences.PreferencesHelper
import com.yousef.mvpflightinfo.ui.base.interactor.BaseInteractor
import io.reactivex.Single
import javax.inject.Inject

class MapInteractor
@Inject
constructor(private val mContext: Context, private val appDataManager: AppDataManager,
            preferenceHelper: PreferencesHelper, apiHelper: ApiHelper) : BaseInteractor(
        preferenceHelper, apiHelper), MapMVPInteractor {

    override fun getAccessToken(): String? {
        return appDataManager.accessToken
    }

    override fun requestLatLong(token: String?, airport: String?): Single<AirportLatLongPOJO?> {
        return appDataManager.requestLatLong(token, airport)
    }

    override fun getOrigin(): String? {
        return appDataManager.origin
    }

    override fun getDestination(): String? {
        return appDataManager.destination
    }

    override fun getOrgLat(): String? {
        return appDataManager.orgLat
    }

    override fun getOrgLng(): String? {
        return appDataManager.orgLng
    }

    override fun getDstLat(): String? {
        return appDataManager.dstLat
    }

    override fun getDstLng(): String? {
        return appDataManager.dstLng
    }

    override fun setOrgLat(orgLat: String) {
        appDataManager.orgLat = orgLat
    }

    override fun setOrgLng(orgLng: String) {
        appDataManager.orgLng = orgLng
    }

    override fun setDstLat(dstLat: String) {
        appDataManager.dstLat = dstLat
    }

    override fun setDstLng(dstLng: String) {
        appDataManager.dstLng = dstLng
    }

}