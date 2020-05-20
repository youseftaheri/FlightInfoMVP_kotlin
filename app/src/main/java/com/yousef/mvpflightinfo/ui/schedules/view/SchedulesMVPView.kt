package com.yousef.mvpflightinfo.ui.schedules.view

import com.yousef.mvpflightinfo.data.model.AirportsPOJO
import com.yousef.mvpflightinfo.data.model.LuftSchedulesPOJO
import com.yousef.mvpflightinfo.ui.base.view.MVPView

interface SchedulesMVPView: MVPView {
    fun showData(data: LuftSchedulesPOJO)
}