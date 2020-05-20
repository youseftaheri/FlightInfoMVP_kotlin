package com.yousef.mvpflightinfo.utils

import android.content.Context
object CommonUtils {
    private const val TAG = "CommonUtils"
    private var pd: CustomProgressDialog? = null
    const val PREF_NAME = "flight_info_pref"

    fun showLoadingDialog(context: Context) {
        pd = CustomProgressDialog(context)
        pd!!.show()
    }

    fun hideLoadingDialog(context: Context?) {
        if (pd != null && pd!!.isShowing) pd!!.dismiss()
    }
}