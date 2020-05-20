package com.yousef.mvpflightinfo.ui.splash.view

import com.yousef.mvpflightinfo.data.model.TokenPOJO
import com.yousef.mvpflightinfo.ui.base.view.MVPView

interface SplashMVPView : MVPView {
    fun showError(msg: String?)
    fun showData(data: TokenPOJO)
}