package com.yousef.mvpflightinfo.data.preferences


interface PreferencesHelper {
    fun clear()
    var accessToken: String?
    var origin: String?
    var destination: String?
    var orgLat: String?
    var orgLng: String?
    var dstLat: String?
    var dstLng: String?
}