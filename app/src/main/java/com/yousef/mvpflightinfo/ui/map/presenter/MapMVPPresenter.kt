package com.yousef.mvpflightinfo.ui.map.presenter

import com.yousef.mvpflightinfo.ui.base.presenter.MVPPresenter
import com.yousef.mvpflightinfo.ui.map.interactor.MapMVPInteractor
import com.yousef.mvpflightinfo.ui.map.view.MapMVPView

interface MapMVPPresenter <V : MapMVPView, I : MapMVPInteractor> : MVPPresenter<V, I> {
    fun loadData()
    fun getOrgLat(): String?
    fun getOrgLng(): String?
    fun getDstLat(): String?
    fun getDstLng(): String?
    fun getOrigin(): String?
    fun getDestination(): String?
}