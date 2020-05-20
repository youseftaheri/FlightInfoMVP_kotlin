package com.yousef.mvpflightinfo.ui.base.interactor

import com.yousef.mvpflightinfo.data.network.ApiHelper
import com.yousef.mvpflightinfo.data.preferences.PreferencesHelper

open class BaseInteractor() : MVPInteractor {

    protected lateinit var preferenceHelper: PreferencesHelper
    protected lateinit var apiHelper: ApiHelper

    constructor(preferenceHelper: PreferencesHelper, apiHelper: ApiHelper) : this() {
        this.preferenceHelper = preferenceHelper
        this.apiHelper = apiHelper
    }
}