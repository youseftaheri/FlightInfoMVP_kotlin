package com.yousef.mvpflightinfo.ui.schedules.view

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.yousef.mvpflightinfo.R
import com.yousef.mvpflightinfo.data.model.LuftSchedulesPOJO
import com.yousef.mvpflightinfo.ui.base.view.BaseActivity
import com.yousef.mvpflightinfo.ui.main.view.MainActivity
import com.yousef.mvpflightinfo.ui.schedules.interactor.SchedulesMVPInteractor
import com.yousef.mvpflightinfo.ui.schedules.presenter.SchedulesMVPPresenter
import com.yousef.mvpflightinfo.ui.schedules.view.SchedulesAdapter
import com.yousef.mvpflightinfo.utils.Utils
import java.util.*
import javax.inject.Inject

class SchedulesActivity : BaseActivity(), SchedulesMVPView {
    @JvmField
    @BindView(R.id.recyclerView)
    var recyclerView: RecyclerView? = null

    @JvmField
    @BindView(R.id.swipeRefresh)
    var swipeRefresh: SwipeRefreshLayout? = null

    @JvmField
    @BindView(R.id.tvOrigin)
    var tvOrigin: TextView? = null

    @JvmField
    @BindView(R.id.tvDestination)
    var tvDestination: TextView? = null

    @JvmField
    @Inject
    var schedulesAdapter: SchedulesAdapter? = null
    var schedulesList: MutableList<LuftSchedulesPOJO.Schedule?> = ArrayList()

    @Inject
    lateinit var presenter: SchedulesMVPPresenter<SchedulesMVPView, SchedulesMVPInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedules)
        setUnBinder(ButterKnife.bind(this))
        presenter.onAttach(this)
        iniUI()
    }

    override fun onDestroy() {
        presenter.onDetach()
        unBinde()
        super.onDestroy()
    }

    fun iniUI() {
        tvOrigin!!.text = presenter!!.getOrigin()
        tvDestination!!.text = presenter!!.getDestination()
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        schedulesAdapter = SchedulesAdapter(this, schedulesList)
        recyclerView!!.adapter = schedulesAdapter
        swipeRefresh!!.setOnRefreshListener {
            swipeRefresh!!.isRefreshing = false
            setUp()
        }
        setUp()
    }

    fun setUp() {
        presenter!!.loadData()
    }

    @OnClick(R.id.btBack)
    fun btBackClick() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        Utils.gotoNextActivityAnimation(this@SchedulesActivity)
    }

    override fun showData(data: LuftSchedulesPOJO) {
        schedulesList.clear()
        val schedules = data.scheduleResource!!.Schedules
        Collections.sort(schedules)
        schedulesList.addAll(schedules!!)
        if (data.scheduleResource!!.Schedules!!.size == 0) {
            Utils.showMessageDialog(this, getString(R.string.empty_schedule), null)
            btBackClick()
        } else {
            schedulesAdapter!!.notifyDataSetChanged()
        }
    }



}