package com.yousef.mvpflightinfo.ui.schedules.presenter

import com.yousef.mvpflightinfo.ui.base.presenter.MVPPresenter
import com.yousef.mvpflightinfo.ui.schedules.interactor.SchedulesMVPInteractor
import com.yousef.mvpflightinfo.ui.schedules.view.SchedulesMVPView

interface SchedulesMVPPresenter <V : SchedulesMVPView, I : SchedulesMVPInteractor> : MVPPresenter<V, I> {
    fun loadData()
    fun getOrigin(): String?
    fun getDestination(): String?
}