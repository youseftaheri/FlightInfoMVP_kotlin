package com.yousef.mvpflightinfo.ui.splash.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.snackbar.Snackbar
import com.yousef.mvpflightinfo.R
import com.yousef.mvpflightinfo.data.model.TokenPOJO
import com.yousef.mvpflightinfo.ui.base.view.BaseActivity
import com.yousef.mvpflightinfo.ui.main.view.MainActivity
import com.yousef.mvpflightinfo.ui.splash.interactor.SplashMVPInteractor
import com.yousef.mvpflightinfo.ui.splash.presenter.SplashMVPPresenter
import com.yousef.mvpflightinfo.utils.Utils
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashMVPView {
    @JvmField
    @BindView(R.id.root)
    var constraintLayout: ConstraintLayout? = null
    @Inject
    lateinit var presenter: SplashMVPPresenter<SplashMVPView, SplashMVPInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setUnBinder(ButterKnife.bind(this))
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        unBinde()
        super.onDestroy()
    }

    override fun showError(msg: String?) {
        val snackbar = Snackbar
                .make(constraintLayout!!, msg!!, Snackbar.LENGTH_LONG)
        val sbView = snackbar.view
        val textView = sbView.findViewById<TextView>(R.id.snackbar_text)
        textView.setTextColor(Color.YELLOW)
        snackbar.show()
    }

    override fun showData(data: TokenPOJO) {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        Utils.gotoNextActivityAnimation(this@SplashActivity)
    }
}