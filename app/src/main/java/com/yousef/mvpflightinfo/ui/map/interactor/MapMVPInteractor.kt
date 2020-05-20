package com.yousef.mvpflightinfo.ui.map.interactor

import com.yousef.mvpflightinfo.data.model.AirportLatLongPOJO
import com.yousef.mvpflightinfo.ui.base.interactor.MVPInteractor
import io.reactivex.Single

interface MapMVPInteractor: MVPInteractor {
    fun getAccessToken(): String?
    fun getOrgLat(): String?
    fun getOrgLng(): String?
    fun getDstLat(): String?
    fun getDstLng(): String?
    fun getOrigin(): String?
    fun getDestination(): String?
    fun setOrgLat(orgLat: String)
    fun setOrgLng(orgLng: String)
    fun setDstLat(dstLat: String)
    fun setDstLng(dstLng: String)
    fun requestLatLong(token: String?, airport: String?): Single<AirportLatLongPOJO?>
}
