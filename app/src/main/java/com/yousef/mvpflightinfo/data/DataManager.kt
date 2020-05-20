package com.yousef.mvpflightinfo.data
import androidx.lifecycle.MediatorLiveData
import com.yousef.mvpflightinfo.data.network.ApiHelper
import com.yousef.mvpflightinfo.data.preferences.PreferencesHelper

interface DataManager : PreferencesHelper, ApiHelper