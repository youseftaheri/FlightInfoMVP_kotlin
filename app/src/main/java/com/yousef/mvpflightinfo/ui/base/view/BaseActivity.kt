package com.yousef.mvpflightinfo.ui.base.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.Unbinder
import com.yousef.mvpflightinfo.utils.CommonUtils
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity(), MVPView {
    private var mUnBinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
    }

    override fun hideProgress() {
        CommonUtils.hideLoadingDialog(this)
    }

    override fun showProgress() {
        hideProgress()
        CommonUtils.showLoadingDialog(this)
    }

    private fun performDI() = AndroidInjection.inject(this)

    open fun setUnBinder(unBinder: Unbinder?) {
        mUnBinder = unBinder
    }

    open fun unBinde() {
        if (mUnBinder != null)
            mUnBinder!!.unbind()
    }
}