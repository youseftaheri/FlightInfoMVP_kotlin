package com.yousef.mvpflightinfo.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.yousef.mvpflightinfo.R

object Utils {
    /**
     * Check internet availabilty
     *
     * @param mContext Context of activity or fragment
     * @return Returns true is internet connected and false if no internet connected
     */
    fun isNetworkConnected(mContext: Context): Boolean {
        val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm?.activeNetworkInfo != null
    }

    /**
     * Goto next Activity with animation
     *
     * @param mContext Context of the Activity.
     */
    fun gotoNextActivityAnimation(mContext: Context) {
        (mContext as Activity).overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
    }

    /**
     * Show Log
     *
     * @param message Message that want to show into Log
     */
    fun showLog(message: String) {
        Log.e("Log Message", "" + message)
    }

    /**
     * Show message dialog
     *
     * @param mContext Context of activity o fragment
     * @param message  Message that shows on Dialog
     * @param listener Set action that you want to performon OK click
     * @return dialog
     */
    fun showMessageDialog(mContext: Context, message: String?, listener: DialogInterface.OnClickListener?): AlertDialog.Builder {
        val dialog = AlertDialog.Builder(mContext)
        dialog.setMessage(message)
        dialog.setCancelable(false)
        dialog.setNegativeButton(mContext.getString(R.string.ok), listener)
        try {
            dialog.show()
        } catch (ignored: Exception) {
        }
        return dialog
    }
}