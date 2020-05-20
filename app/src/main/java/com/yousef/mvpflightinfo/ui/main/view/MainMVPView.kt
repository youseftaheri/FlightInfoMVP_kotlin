package com.yousef.mvpflightinfo.ui.main.view

import com.yousef.mvpflightinfo.data.model.AirportsPOJO
import com.yousef.mvpflightinfo.ui.base.view.MVPView

interface MainMVPView: MVPView {
    fun showData(data: AirportsPOJO)
}