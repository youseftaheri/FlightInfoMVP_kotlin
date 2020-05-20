package com.yousef.mvpflightinfo.ui.schedules.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.yousef.mvpflightinfo.R
import com.yousef.mvpflightinfo.data.model.LuftSchedulesPOJO.Schedule
import com.yousef.mvpflightinfo.ui.map.view.MapActivity
import com.yousef.mvpflightinfo.utils.Utils
import javax.inject.Inject
import javax.inject.Named

class SchedulesAdapter
//@Inject
//@JvmSuppressWildcards
constructor(private val mContext: Context,
            private val list: MutableList<Schedule?>) : RecyclerView.Adapter<SchedulesAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_schedule, parent, false)
        return Holder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list[position]
        holder.tvDepartureDate!!.text = item!!.Flight!!.Departure!!.ScheduledTimeLocal!!.DateTime!!.replace("T", "  ")
        holder.tvArrivalDate!!.text = item.Flight!!.Arrival!!.ScheduledTimeLocal!!.DateTime!!.replace("T", "  ")
        holder.tvFlightNumber!!.text = "FlightNumber: " + item.Flight!!.MarketingCarrier!!.FlightNumber
        holder.tvViewMap!!.setOnClickListener { v: View? ->
            mContext.startActivity(Intent(mContext, MapActivity::class.java))
            Utils.gotoNextActivityAnimation(mContext)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItems(mlist: List<Schedule?>?) {
        list.addAll(mlist!!)
        notifyDataSetChanged()
    }

    interface Callback {
        fun onRepoEmptyViewRetryClick()
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        @kotlin.jvm.JvmField
        @BindView(R.id.tvFlightNumber)
        var tvFlightNumber: TextView? = null

        @kotlin.jvm.JvmField
        @BindView(R.id.tvDepartureDate)
        var tvDepartureDate: TextView? = null

        @kotlin.jvm.JvmField
        @BindView(R.id.tvArrivalDate)
        var tvArrivalDate: TextView? = null

        @kotlin.jvm.JvmField
        @BindView(R.id.tvViewMap)
        var tvViewMap: TextView? = null

        @kotlin.jvm.JvmField
        @BindView(R.id.requestCard)
        var requestCard: CardView? = null

        init {
            ButterKnife.bind(this, itemView!!)
        }
    }

}