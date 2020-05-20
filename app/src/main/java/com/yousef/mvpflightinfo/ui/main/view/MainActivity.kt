package com.yousef.mvpflightinfo.ui.main.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.android.material.snackbar.Snackbar
import com.yousef.mvpflightinfo.R
import com.yousef.mvpflightinfo.data.model.AirportsPOJO
import com.yousef.mvpflightinfo.ui.base.view.BaseActivity
import com.yousef.mvpflightinfo.ui.main.interactor.MainMVPInteractor
import com.yousef.mvpflightinfo.ui.main.presenter.MainMVPPresenter
import com.yousef.mvpflightinfo.ui.schedules.view.SchedulesActivity
import com.yousef.mvpflightinfo.utils.Utils
import java.util.ArrayList
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMVPView {
    @JvmField
    @BindView(R.id.etOrigin)
    var etOrigin: EditText? = null

    @JvmField
    @BindView(R.id.etDestination)
    var etDestination: EditText? = null

    @JvmField
    @BindView(R.id.root)
    var coordinatorLayout: CoordinatorLayout? = null
    var airportNameStr: MutableList<String>? = null
    var airports: List<AirportsPOJO.Airport?>? = null
    var airportIndex = 0

    @Inject
    lateinit var presenter: MainMVPPresenter<MainMVPView, MainMVPInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUnBinder(ButterKnife.bind(this))
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        unBinde()
        super.onDestroy()
    }

    @OnClick(R.id.btShowSchedules)
    fun btShowSchedulesClick() {
        if (etOrigin!!.text.toString().isEmpty())
            showError(getString(R.string.enter_origin))
        else if (etDestination!!.text.toString().isEmpty())
            showError(getString(R.string.enter_destination))
        else {
            airportIndex = find(airportNameStr, etOrigin!!.text.toString())
            if (airportIndex > -1)
                presenter!!.setOrigin(airports!![airportIndex]!!.AirportCode!!)
            else
                presenter!!.setOrigin(etOrigin!!.text.toString())
            airportIndex = find(airportNameStr, etDestination!!.text.toString())
            if (airportIndex > -1)
                presenter!!.setDestination(airports!![airportIndex]!!.AirportCode!!)
            else
                presenter!!.setDestination(etDestination!!.text.toString())
            startActivity(Intent(applicationContext, SchedulesActivity::class.java))
            Utils.gotoNextActivityAnimation(this@MainActivity)
        }
    }

    /**
     * Snackbar shows error
     */
    fun showError(msg: String?) {
        val snackbar = Snackbar
                .make(coordinatorLayout!!, msg!!, Snackbar.LENGTH_LONG)
        val sbView = snackbar.view
        val textView = sbView.findViewById<TextView>(R.id.snackbar_text)
        textView.setTextColor(Color.YELLOW)
        snackbar.show()
    }

    @OnClick(R.id.ivClearOrigin)
    fun ivClearOriginClick() {
        etOrigin!!.setText("")
    }

    @OnClick(R.id.ivClearDestination)
    fun ivClearDestinationClick() {
        etDestination!!.setText("")
    }

    override fun showData(data: AirportsPOJO) {
        airports = data.airportResource!!.Airports!!.Airport
        setAutoStrings()
    }

    fun setAutoStrings() {
        airportNameStr = ArrayList()
        for (airport in airports!!) {
            (airportNameStr as ArrayList<String>).add(airport!!.Names!!.Name!!.fullName + " - " + airport.CityCode + " - " + airport.CountryCode)
        }
        val tvOrigin = findViewById<AutoCompleteTextView>(R.id.etOrigin)
        val originAdp = ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, airportNameStr as ArrayList<String>)
        tvOrigin.threshold = 1
        tvOrigin.setAdapter(originAdp)
        val tvDestination = findViewById<AutoCompleteTextView>(R.id.etDestination)
        val destinationAdp = ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, airportNameStr as ArrayList<String>)
        tvDestination.threshold = 1
        tvDestination.setAdapter(destinationAdp)
    }

    fun find(a: List<String>?, target: String): Int {
        for (i in a!!.indices) if (target == a[i]) return i
        return -1
    }
}